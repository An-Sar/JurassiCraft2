package org.jurassicraft.client.render.block;

import net.ilexiconn.llibrary.LLibrary;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import org.jurassicraft.JurassiCraft;
import org.jurassicraft.client.model.ResetControlTabulaModel;
import org.jurassicraft.server.block.BlockHandler;
import org.jurassicraft.server.block.machine.FeederBlock;
import org.jurassicraft.server.tabula.TabulaModelHelper;
import org.jurassicraft.server.tile.FeederTile;
import org.lwjgl.opengl.GL11;

public class FeederSpecialRenderer extends TileEntitySpecialRenderer<FeederTile>
{
    private Minecraft mc = Minecraft.getMinecraft();

    private ResetControlTabulaModel model;
    private ResourceLocation texture;

    public FeederSpecialRenderer()
    {
        try
        {
            this.model = new ResetControlTabulaModel(TabulaModelHelper.loadTabulaModel("/assets/jurassicraft/models/block/feeder.tbl"));
            this.model.setResetEachFrame(false);
            this.texture = new ResourceLocation(JurassiCraft.MODID, "textures/blocks/feeder.png");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void renderTileEntityAt(FeederTile tile, double x, double y, double z, float partialTicks, int destroyStage)
    {
        IBlockState blockState = tile.getWorld().getBlockState(tile.getPos());

        if (blockState.getBlock() == BlockHandler.FEEDER)
        {
            GlStateManager.pushMatrix();
            GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            GlStateManager.enableBlend();
            GlStateManager.disableCull();

            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.translate(x + 0.5, y + 0.5, z + 0.5);

            EnumFacing facing = blockState.getValue(FeederBlock.FACING);

            float rotation = facing.getAxisDirection() == EnumFacing.AxisDirection.NEGATIVE ? -90.0F : 90.0F;

            EnumFacing.Axis axis = facing.getAxis();

            if (axis == EnumFacing.Axis.Y)
            {
                GlStateManager.rotate(rotation - 90.0F, 0.0F, 0.0F, 1.0F);
            }
            else if (axis == EnumFacing.Axis.X)
            {
                GlStateManager.rotate(rotation, 0.0F, 1.0F, 0.0F);
                GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
            }
            else if (axis == EnumFacing.Axis.Z)
            {
                GlStateManager.rotate(rotation, 0.0F, 1.0F, 0.0F);
                GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
            }

            GlStateManager.translate(0.0F, 1.0F, 0.0F);

            double scale = 1.0;
            GlStateManager.scale(scale, -scale, scale);

            mc.getTextureManager().bindTexture(texture);

            float openAnimation = Math.max(0.0F, Math.min(20.0F, tile.openAnimation + LLibrary.PROXY.getPartialTicks() * (tile.openAnimation - tile.prevOpenAnimation)));

            model.getCube("Lid 1").rotateAngleX = (float) Math.toRadians((openAnimation / 20.0F) * 99.13F);
            model.getCube("Lid 2").rotateAngleX = (float) Math.toRadians((openAnimation / 20.0F) * -99.13F);

            model.render(null, 0, 0, 0, 0, 0, 0.0625F);

            GlStateManager.disableBlend();
            GlStateManager.enableCull();
            GlStateManager.popMatrix();
        }
    }
}
