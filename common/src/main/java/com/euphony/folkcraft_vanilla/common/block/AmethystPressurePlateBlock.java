package com.euphony.folkcraft_vanilla.common.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BasePressurePlateBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.jetbrains.annotations.NotNull;

public class AmethystPressurePlateBlock extends BasePressurePlateBlock {
    public static final MapCodec<AmethystPressurePlateBlock> CODEC = simpleCodec(AmethystPressurePlateBlock::new);
    public static final BooleanProperty POWERED;

    public AmethystPressurePlateBlock(Properties properties) {
        super(properties, BlockSetType.IRON);
        this.registerDefaultState(this.stateDefinition.any().setValue(POWERED, false));
    }

    @Override
    protected @NotNull MapCodec<? extends BasePressurePlateBlock> codec() {
        return CODEC;
    }

    @Override
    protected int getSignalStrength(Level level, BlockPos blockPos) {
        Class<? extends Entity> class_ = Player.class;
        return getEntityCount(level, TOUCH_AABB.move(blockPos), class_) > 0 ? 15 : 0;
    }

    @Override
    protected int getSignalForState(BlockState blockState) {
        return blockState.getValue(POWERED) ? 15 : 0;
    }

    @Override
    protected BlockState setSignalForState(BlockState blockState, int i) {
        return blockState.setValue(POWERED, i > 0);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(POWERED);
    }

    static {
        POWERED = BlockStateProperties.POWERED;
    }
}
