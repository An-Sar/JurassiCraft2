package org.jurassicraft.server.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class MachineBaseTile extends TileEntityLockable implements ITickable, ISidedInventory
{
    protected String customName;

    protected int[] processTime = new int[getProcessCount()];
    protected int[] totalProcessTime = new int[getProcessCount()];

    @SideOnly(Side.CLIENT)
    public static boolean isProcessing(IInventory inventory, int index)
    {
        return inventory.getField(index) > 0;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);

        NBTTagList itemList = compound.getTagList("Items", 10);
        ItemStack[] slots = new ItemStack[getSlots().length];

        for (int i = 0; i < itemList.tagCount(); ++i)
        {
            NBTTagCompound item = itemList.getCompoundTagAt(i);

            byte slot = item.getByte("Slot");

            if (slot >= 0 && slot < slots.length)
            {
                slots[slot] = ItemStack.loadItemStackFromNBT(item);
            }
        }

        for (int i = 0; i < getProcessCount(); i++)
        {
            processTime[i] = compound.getShort("ProcessTime" + i);
            totalProcessTime[i] = compound.getShort("ProcessTimeTotal" + i);
        }

        if (compound.hasKey("CustomName", 8))
        {
            this.customName = compound.getString("CustomName");
        }

        setSlots(slots);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        compound = super.writeToNBT(compound);

        for (int i = 0; i < getProcessCount(); i++)
        {
            compound.setShort("ProcessTime" + i, (short) this.processTime[i]);
            compound.setShort("ProcessTimeTotal" + i, (short) this.totalProcessTime[i]);
        }

        ItemStack[] slots = getSlots();

        NBTTagList itemList = new NBTTagList();

        for (int slot = 0; slot < getSizeInventory(); ++slot)
        {
            if (slots[slot] != null)
            {
                NBTTagCompound itemTag = new NBTTagCompound();
                itemTag.setByte("Slot", (byte) slot);

                slots[slot].writeToNBT(itemTag);
                itemList.appendTag(itemTag);
            }
        }

        compound.setTag("Items", itemList);

        if (this.hasCustomName())
        {
            compound.setString("CustomName", this.customName);
        }

        return compound;
    }

    @Override
    public ItemStack getStackInSlot(int index)
    {
        return getSlots()[index];
    }

    @Override
    public ItemStack decrStackSize(int index, int count)
    {
        ItemStack[] slots = getSlots();

        if (slots[index] != null)
        {
            ItemStack stack;

            if (slots[index].stackSize <= count)
            {
                stack = slots[index];
                slots[index] = null;
                return stack;
            }
            else
            {
                stack = slots[index].splitStack(count);

                if (slots[index].stackSize == 0)
                {
                    slots[index] = null;
                }

                return stack;
            }
        }
        else
        {
            return null;
        }
    }

    @Override
    public ItemStack removeStackFromSlot(int index)
    {
        ItemStack[] slots = getSlots();

        if (slots[index] != null)
        {
            ItemStack slot = slots[index];
            slots[index] = null;
            return slot;
        }
        else
        {
            return null;
        }
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack)
    {
        ItemStack[] slots = getSlots();

        boolean stacksEqual = stack != null && stack.isItemEqual(slots[index]) && ItemStack.areItemStackTagsEqual(stack, slots[index]);
        slots[index] = stack;

        if (stack != null && stack.stackSize > this.getInventoryStackLimit())
        {
            stack.stackSize = this.getInventoryStackLimit();
        }

        if (!stacksEqual)
        {
            int process = getProcess(index);

            if (process < getProcessCount())
            {
                if (!canProcess(process))
                {
                    this.totalProcessTime[process] = this.getStackProcessTime(stack);
                    this.processTime[process] = 0;
                    this.markDirty();
                }
            }
        }
    }

    private boolean isInput(int slot)
    {
        int[] inputs = getInputs();

        for (int input : inputs)
        {
            if (input == slot)
            {
                return true;
            }
        }

        return false;
    }

    private boolean isOutput(int slot)
    {
        int[] outputs = getOutputs();

        for (int output : outputs)
        {
            if (output == slot)
            {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean hasCustomName()
    {
        return this.customName != null && this.customName.length() > 0;
    }

    public void setCustomInventoryName(String customName)
    {
        this.customName = customName;
    }

    @Override
    public int getSizeInventory()
    {
        return this.getSlots().length;
    }

    public boolean isProcessing(int index)
    {
        return this.processTime[index] > 0;
    }

    @Override
    public void update()
    {
        ItemStack[] slots = getSlots();

        for (int process = 0; process < getProcessCount(); process++)
        {
            boolean flag = this.isProcessing(process);
            boolean sync = false;

            if (!this.worldObj.isRemote)
            {
                boolean hasInput = false;

                for (int input : getInputs(process))
                {
                    if (slots[input] != null)
                    {
                        hasInput = true;
                        break;
                    }
                }

                if (hasInput && this.canProcess(process))
                {
                    this.processTime[process]++;

                    if (this.processTime[process] >= this.totalProcessTime[process])
                    {
                        this.processTime[process] = 0;
                        this.totalProcessTime[process] = this.getStackProcessTime(slots[getInputs()[0]]);
                        this.processItem(process);
                    }

                    sync = true;
                }
                else if (this.isProcessing(process))
                {
                    if (this.shouldResetProgress())
                    {
                        this.processTime[process] = 0;
                    }
                    else if (this.processTime[process] > 0)
                    {
                        this.processTime[process]--;
                    }

                    sync = true;
                }

                if (flag != this.isProcessing(process))
                {
                    sync = true;
                }

                if (sync)
                {
                    this.markDirty();
                }
            }
        }
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player)
    {
        return this.worldObj.getTileEntity(this.pos) == this && player.getDistanceSq((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D, (double) this.pos.getZ() + 0.5D) <= 64.0D;
    }

    @Override
    public void openInventory(EntityPlayer player)
    {
    }

    @Override
    public void closeInventory(EntityPlayer player)
    {
    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack)
    {
        return !isOutput(index);
    }

    @Override
    public int[] getSlotsForFace(EnumFacing side)
    {
        return side == EnumFacing.DOWN ? getOutputs() : getInputs();
    }

    @Override
    public boolean canInsertItem(int index, ItemStack stack, EnumFacing direction)
    {
        return this.isItemValidForSlot(index, stack);
    }

    protected abstract int getProcess(int slot);

    protected abstract boolean canProcess(int process);

    protected abstract void processItem(int process);

    protected abstract int getMainOutput(int process);

    protected abstract int getStackProcessTime(ItemStack stack);

    protected abstract int getProcessCount();

    protected abstract int[] getInputs();

    protected abstract int[] getInputs(int process);

    protected abstract int[] getOutputs();

    protected abstract ItemStack[] getSlots();

    protected abstract void setSlots(ItemStack[] slots);

    public boolean hasOutputSlot(ItemStack output)
    {
        return getOutputSlot(output) != -1;
    }

    public int getOutputSlot(ItemStack output)
    {
        ItemStack[] slots = getSlots();

        int[] outputs = getOutputs();

        for (int slot : outputs)
        {
            ItemStack stack = slots[slot];

            if (stack == null || ((ItemStack.areItemStackTagsEqual(stack, output) && stack.stackSize + output.stackSize <= stack.getMaxStackSize()) && stack.getItem() == output.getItem() && stack.getItemDamage() == output.getItemDamage()))
            {
                return slot;
            }
        }

        return -1;
    }

    @Override
    public int getField(int id)
    {
        int processCount = getProcessCount();

        if (id < processCount)
        {
            return processTime[id];
        }
        else if (id < processCount * 2)
        {
            return totalProcessTime[id - processCount];
        }

        return 0;
    }

    @Override
    public void setField(int id, int value)
    {
        int processCount = getProcessCount();

        if (id < processCount)
        {
            processTime[id] = value;
        }
        else if (id < processCount * 2)
        {
            totalProcessTime[id - processCount] = value;
        }
    }

    @Override
    public int getFieldCount()
    {
        return getProcessCount() * 2;
    }

    @Override
    public void clear()
    {
        ItemStack[] slots = getSlots();

        for (int i = 0; i < slots.length; ++i)
        {
            slots[i] = null;
        }
    }

    @Override
    public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction)
    {
        return true;
    }

    protected void mergeStack(int slot, ItemStack stack)
    {
        ItemStack[] slots = getSlots();

        if (slots[slot] == null)
        {
            slots[slot] = stack;
        }
        else if (slots[slot].getItem() == stack.getItem() && ItemStack.areItemStackTagsEqual(slots[slot], stack))
        {
            slots[slot].stackSize += stack.stackSize;
        }
    }

    protected void decreaseStackSize(int slot)
    {
        ItemStack[] slots = getSlots();

        slots[slot].stackSize--;

        if (slots[slot].stackSize <= 0)
        {
            slots[slot] = null;
        }
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }

    @Override
    public SPacketUpdateTileEntity getUpdatePacket()
    {
        return new SPacketUpdateTileEntity(this.pos, 0, this.getUpdateTag());
    }

    @Override
    public NBTTagCompound getUpdateTag()
    {
        return this.writeToNBT(new NBTTagCompound());
    }

    @Override
    public void onDataPacket(NetworkManager networkManager, SPacketUpdateTileEntity packet)
    {
        this.readFromNBT(packet.getNbtCompound());
    }

    protected boolean shouldResetProgress()
    {
        return true;
    }
}
