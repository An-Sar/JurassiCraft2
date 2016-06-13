package org.jurassicraft.server.handler;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import org.jurassicraft.client.gui.CleaningStationGui;
import org.jurassicraft.client.gui.CultivateGui;
import org.jurassicraft.client.gui.CultivateProcessGui;
import org.jurassicraft.client.gui.DNACombinatorHybridizerGui;
import org.jurassicraft.client.gui.DNAExtractorGui;
import org.jurassicraft.client.gui.DNASequencerGui;
import org.jurassicraft.client.gui.DNASynthesizerGui;
import org.jurassicraft.client.gui.EmbryoCalcificationMachineGui;
import org.jurassicraft.client.gui.EmbryonicMachineGui;
import org.jurassicraft.client.gui.FieldGuideGui;
import org.jurassicraft.client.gui.FossilGrinderGui;
import org.jurassicraft.client.gui.IncubatorGui;
import org.jurassicraft.client.gui.OrderDinosaurGui;
import org.jurassicraft.client.gui.SelectDinoGui;
import org.jurassicraft.server.container.CleaningStationContainer;
import org.jurassicraft.server.container.CultivateContainer;
import org.jurassicraft.server.container.DNACombinatorHybridizerContainer;
import org.jurassicraft.server.container.DNAExtractorContainer;
import org.jurassicraft.server.container.DNASequencerContainer;
import org.jurassicraft.server.container.DNASynthesizerContainer;
import org.jurassicraft.server.container.EmbryoCalcificationMachineContainer;
import org.jurassicraft.server.container.EmbryonicMachineContainer;
import org.jurassicraft.server.container.FossilGrinderContainer;
import org.jurassicraft.server.container.IncubatorContainer;
import org.jurassicraft.server.entity.base.DinosaurEntity;
import org.jurassicraft.server.tile.CleaningStationTile;
import org.jurassicraft.server.tile.CultivatorTile;
import org.jurassicraft.server.tile.DNACombinatorHybridizerTile;
import org.jurassicraft.server.tile.DNAExtractorTile;
import org.jurassicraft.server.tile.DNASequencerTile;
import org.jurassicraft.server.tile.DNASynthesizerTile;
import org.jurassicraft.server.tile.EmbryoCalcificationMachineTile;
import org.jurassicraft.server.tile.EmbryonicMachineTile;
import org.jurassicraft.server.tile.FossilGrinderTile;
import org.jurassicraft.server.tile.IncubatorTile;

public class GuiHandler implements IGuiHandler
{
    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
    {
        BlockPos pos = new BlockPos(x, y, z);
        TileEntity tileEntity = world.getTileEntity(pos);

        if (tileEntity != null)
        {
            if (tileEntity instanceof CleaningStationTile && id == 0)
            {
                return new CleaningStationContainer(player.inventory, (CleaningStationTile) tileEntity);
            }
            else if (tileEntity instanceof FossilGrinderTile && id == 1)
            {
                return new FossilGrinderContainer(player.inventory, tileEntity);
            }
            else if (tileEntity instanceof DNASequencerTile && id == 2)
            {
                return new DNASequencerContainer(player.inventory, tileEntity);
            }
            else if (tileEntity instanceof EmbryonicMachineTile && id == 3)
            {
                return new EmbryonicMachineContainer(player.inventory, tileEntity);
            }
            else if (tileEntity instanceof EmbryoCalcificationMachineTile && id == 4)
            {
                return new EmbryoCalcificationMachineContainer(player.inventory, tileEntity);
            }
            else if (tileEntity instanceof DNASynthesizerTile && id == 5)
            {
                return new DNASynthesizerContainer(player.inventory, tileEntity);
            }
            else if (tileEntity instanceof IncubatorTile && id == 6)
            {
                return new IncubatorContainer(player.inventory, tileEntity);
            }
            else if (tileEntity instanceof DNACombinatorHybridizerTile && id == 7)
            {
                return new DNACombinatorHybridizerContainer(player.inventory, tileEntity);
            }
            else if (tileEntity instanceof DNAExtractorTile && id == 9)
            {
                return new DNAExtractorContainer(player.inventory, tileEntity);
            }
            else if (tileEntity instanceof CultivatorTile && id == 10)
            {
                return new CultivateContainer(player.inventory, tileEntity);
            }
        }

        return null;
    }

    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
    {
        BlockPos pos = new BlockPos(x, y, z);
        TileEntity tileEntity = world.getTileEntity(pos);

        if (tileEntity != null)
        {
            if (tileEntity instanceof CleaningStationTile && id == 0)
            {
                return new CleaningStationGui(player.inventory, (CleaningStationTile) tileEntity);
            }
            else if (tileEntity instanceof FossilGrinderTile && id == 1)
            {
                return new FossilGrinderGui(player.inventory, (FossilGrinderTile) tileEntity);
            }
            else if (tileEntity instanceof DNASequencerTile && id == 2)
            {
                return new DNASequencerGui(player.inventory, (DNASequencerTile) tileEntity);
            }
            else if (tileEntity instanceof EmbryonicMachineTile && id == 3)
            {
                return new EmbryonicMachineGui(player.inventory, (EmbryonicMachineTile) tileEntity);
            }
            else if (tileEntity instanceof EmbryoCalcificationMachineTile && id == 4)
            {
                return new EmbryoCalcificationMachineGui(player.inventory, (EmbryoCalcificationMachineTile) tileEntity);
            }
            else if (tileEntity instanceof DNASynthesizerTile && id == 5)
            {
                return new DNASynthesizerGui(player.inventory, (DNASynthesizerTile) tileEntity);
            }
            else if (tileEntity instanceof IncubatorTile && id == 6)
            {
                return new IncubatorGui(player.inventory, (IncubatorTile) tileEntity);
            }
            else if (tileEntity instanceof DNACombinatorHybridizerTile && id == 7)
            {
                return new DNACombinatorHybridizerGui(player.inventory, (DNACombinatorHybridizerTile) tileEntity);
            }
            else if (tileEntity instanceof DNAExtractorTile && id == 9)
            {
                return new DNAExtractorGui(player.inventory, (DNAExtractorTile) tileEntity);
            }
            else if (tileEntity instanceof CultivatorTile && id == 10)
            {
                if (((CultivatorTile) tileEntity).isProcessing(0))
                {
                    return new CultivateProcessGui((CultivatorTile) tileEntity);
                }
                else
                {
                    return new CultivateGui(player.inventory, (CultivatorTile) tileEntity);
                }
            }
        }

        return null;
    }

    public static void openSelectDino(BlockPos pos, EnumFacing facing, EnumHand hand)
    {
        Minecraft.getMinecraft().displayGuiScreen(new SelectDinoGui(pos, facing, hand));
    }

    public static void openOrderGui(DinosaurEntity entity)
    {
        Minecraft.getMinecraft().displayGuiScreen(new OrderDinosaurGui(entity));
    }

    public static void openFieldGuide(DinosaurEntity entity)
    {
        Minecraft.getMinecraft().displayGuiScreen(new FieldGuideGui(entity));
    }
}
