package com.euphony.folkcraft_vanilla.common.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.CarpetBlock;
import net.minecraft.world.level.block.state.BlockState;

public class TintedGlassCarpetBlock extends CarpetBlock {
    public TintedGlassCarpetBlock(Properties properties) {
        super(properties);
    }

    public static final MapCodec<TintedGlassCarpetBlock> CODEC = simpleCodec(TintedGlassCarpetBlock::new);

    @Override
    public MapCodec<? extends CarpetBlock> codec() {
        return CODEC;
    }

    @Override
    protected boolean propagatesSkylightDown(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return false;
    }

    @Override
    protected int getLightBlock(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return blockGetter.getMaxLightLevel();
    }

    @Override
    protected float getShadeBrightness(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return 1.0F;
    }

    protected boolean skipRendering(BlockState blockState, BlockState blockState2, Direction direction) {
        return blockState2.is(this) || super.skipRendering(blockState, blockState2, direction);
    }
}
