package org.jurassicraft.server.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.tileentity.TileEntity;
import org.jurassicraft.server.block.entity.DNAExtractorBlockEntity;
import org.jurassicraft.server.container.slot.DNAExtractionSlot;
import org.jurassicraft.server.container.slot.StorageSlot;

public class DNAExtractorContainer extends MachineContainer {
    private DNAExtractorBlockEntity extractor;

    public DNAExtractorContainer(InventoryPlayer playerInventory, TileEntity tileEntity) {
        super((IInventory) tileEntity);
        this.extractor = (DNAExtractorBlockEntity) tileEntity;
        this.addSlotToContainer(new StorageSlot(this.extractor, 1, 55, 47, false));
        this.addSlotToContainer(new DNAExtractionSlot(this.extractor, 0, 55, 26));
        this.addSlotToContainer(new StorageSlot(this.extractor, 2, 108, 28, true));
        this.addSlotToContainer(new StorageSlot(this.extractor, 3, 126, 28, true));
        this.addSlotToContainer(new StorageSlot(this.extractor, 4, 108, 46, true));
        this.addSlotToContainer(new StorageSlot(this.extractor, 5, 126, 46, true));

        int i;

        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }

    @Override
    public void onContainerClosed(EntityPlayer player) {
        super.onContainerClosed(player);

        if (!player.worldObj.isRemote) {
            this.extractor.closeInventory(player);
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return this.extractor.isUseableByPlayer(player);
    }
}
