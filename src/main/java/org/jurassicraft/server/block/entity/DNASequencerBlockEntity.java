package org.jurassicraft.server.block.entity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import org.jurassicraft.JurassiCraft;
import org.jurassicraft.server.api.SequencableItem;
import org.jurassicraft.server.container.DNASequencerContainer;
import org.jurassicraft.server.item.ItemHandler;

import java.util.Random;

public class DNASequencerBlockEntity extends MachineBaseBlockEntity {
    private static final int[] INPUTS = new int[] { 0, 1, 2, 3, 4, 5 };
    private static final int[] INPUTS_PROCESS_1 = new int[] { 0, 1 };
    private static final int[] INPUTS_PROCESS_2 = new int[] { 2, 3 };
    private static final int[] INPUTS_PROCESS_3 = new int[] { 4, 5 };

    private static final int[] OUTPUTS = new int[] { 6, 7, 8 };

    private ItemStack[] slots = new ItemStack[9];

    @Override
    protected int getProcess(int slot) {
        return Math.min(5, (int) Math.floor(slot / 2));
    }

    @Override
    protected boolean canProcess(int process) {
        int tissue = process * 2;

        ItemStack input = this.slots[tissue];
        ItemStack storage = this.slots[tissue + 1];

        SequencableItem sequencableItem = SequencableItem.getSequencableItem(input);

        if (sequencableItem != null && sequencableItem.isSequencable(input)) {
            if (storage != null && storage.getItem() == ItemHandler.STORAGE_DISC) {
                if (this.slots[process + 6] == null) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    protected void processItem(int process) {
        Random rand = new Random();

        int tissue = process * 2;

        ItemStack sequencableStack = this.slots[tissue];

        this.mergeStack(process + 6, SequencableItem.getSequencableItem(sequencableStack).getSequenceOutput(sequencableStack, rand));

        this.decreaseStackSize(tissue);
        this.decreaseStackSize(tissue + 1);
    }

    @Override
    protected int getMainOutput(int process) {
        return (process * 2) + 1;
    }

    @Override
    protected int getStackProcessTime(ItemStack stack) {
        return 2000;
    }

    @Override
    protected int getProcessCount() {
        return 3;
    }

    @Override
    protected int[] getInputs() {
        return INPUTS;
    }

    @Override
    protected int[] getInputs(int process) {
        if (process == 0) {
            return INPUTS_PROCESS_1;
        } else if (process == 1) {
            return INPUTS_PROCESS_2;
        }

        return INPUTS_PROCESS_3;
    }

    @Override
    protected int[] getOutputs() {
        return OUTPUTS;
    }

    @Override
    protected ItemStack[] getSlots() {
        return this.slots;
    }

    @Override
    protected void setSlots(ItemStack[] slots) {
        this.slots = slots;
    }

    @Override
    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
        return new DNASequencerContainer(playerInventory, this);
    }

    @Override
    public String getGuiID() {
        return JurassiCraft.MODID + ":dna_sequencer";
    }

    @Override
    public String getName() {
        return this.hasCustomName() ? this.customName : "container.dna_sequencer";
    }
}
