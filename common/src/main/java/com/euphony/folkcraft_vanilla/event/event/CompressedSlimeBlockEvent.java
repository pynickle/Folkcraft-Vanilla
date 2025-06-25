package com.euphony.folkcraft_vanilla.event.event;

import com.euphony.folkcraft_vanilla.common.init.FCBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class CompressedSlimeBlockEvent {
    public static void fallingLand(Level level, BlockPos blockPos, BlockState fallState, BlockState landOn, FallingBlockEntity fallingBlockEntity) {
        if(level.isClientSide) return;

        BlockPos pos1 = blockPos.below();
        BlockState blockState1 = level.getBlockState(pos1);

        BlockPos pos2 = blockPos.below(2);
        BlockState blockState2 = level.getBlockState(pos2);

        if(fallState.is(BlockTags.ANVIL)
                && blockState1.is(Blocks.SLIME_BLOCK)
                && blockState2.is(Blocks.SLIME_BLOCK)) {
            level.setBlockAndUpdate(pos1, Blocks.AIR.defaultBlockState());
            level.setBlockAndUpdate(pos2, FCBlocks.COMPRESSED_SLIME_BLOCK.get().defaultBlockState());
        }
    }
}
