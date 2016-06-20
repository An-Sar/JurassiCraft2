package org.jurassicraft.server.world.tree;

import net.minecraft.block.BlockLog;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import org.jurassicraft.server.block.BlockHandler;
import org.jurassicraft.server.block.tree.AncientLogBlock;
import org.jurassicraft.server.block.tree.TreeType;

import java.util.Random;

public class CalamitesTreeGenerator extends WorldGenAbstractTree
{
    public CalamitesTreeGenerator()
    {
        super(true);
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos position)
    {
        IBlockState log = BlockHandler.ANCIENT_LOGS.get(TreeType.CALAMITES).getDefaultState();
        IBlockState leaves = BlockHandler.ANCIENT_LEAVES.get(TreeType.CALAMITES).getDefaultState();

        int height = rand.nextInt(10) + 10;
        int branchIndex = 0;

        int halfDistance = height / 2;

        for (int y = 0; y < height; y++)
        {
            BlockPos logPos = position.up(y);
            world.setBlockState(logPos, log);

            boolean upperHalf = y > halfDistance;

            branchIndex++;

            if (branchIndex > (upperHalf ? 2 : 3))
            {
                branchIndex = 0;
            }

            boolean branch = upperHalf ? branchIndex >= 2 : branchIndex >= 3;

            if (branch)
            {
                for (int face = 0; face < 4; face++)
                {
                    EnumFacing facing = EnumFacing.getHorizontal(face);
                    BlockPos branchPos = logPos.offset(facing);
                    IBlockState facingLog = log.withProperty(AncientLogBlock.LOG_AXIS, BlockLog.EnumAxis.fromFacingAxis(facing.getAxis()));

                    world.setBlockState(branchPos, facingLog);
                    world.setBlockState(branchPos.up(), leaves);

                    int leaveOut = Math.max(1, (upperHalf ? -(halfDistance - y) : (halfDistance - y) + halfDistance) / 2) + (rand.nextInt(2) - 1);

                    for (int i = 0; i < leaveOut; i++)
                    {
                        BlockPos leavePos = branchPos.offset(facing, i + 1).up(i / 2 + 1);

                        world.setBlockState(leavePos, leaves);

                        if (!upperHalf)
                        {
                            if (i < leaveOut / 4 || height < 12)
                            {
                                world.setBlockState(leavePos.up(), leaves);
                            }

                            if (i < leaveOut - 2)
                            {
                                world.setBlockState(leavePos.down(), leaves);
                                world.setBlockState(leavePos.offset(facing.rotateYCCW()), leaves);
                                world.setBlockState(leavePos.offset(facing.rotateY()), leaves);
                            }
                            else if (i >= leaveOut - 2)
                            {
                                world.setBlockState(leavePos.up(), leaves);
                            }
                        }
                        else if (i >= leaveOut - 1)
                        {
                            world.setBlockState(leavePos.up(), leaves);
                            world.setBlockState(leavePos.up(1).offset(facing), leaves);
                        }
                    }

                    if (!upperHalf)
                    {
                        world.setBlockState(branchPos.offset(facing).up(), facingLog);
                        world.setBlockState(branchPos.offset(facing), leaves);
                    }
                    else
                    {
                        world.setBlockState(branchPos.offset(facing).up(2), leaves);
                    }
                }
            }
        }

        for (int i = 0; i < height / 4 + 1; i++)
        {
            world.setBlockState(position.up(height + i), leaves);
        }

        return true;
    }
}
