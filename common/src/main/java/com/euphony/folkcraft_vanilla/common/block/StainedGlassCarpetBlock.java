package com.euphony.folkcraft_vanilla.common.block;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.BeaconBeamBlock;
import net.minecraft.world.level.block.CarpetBlock;
import org.jetbrains.annotations.NotNull;

public class StainedGlassCarpetBlock extends CarpetBlock implements BeaconBeamBlock {
    private final DyeColor color;

    public StainedGlassCarpetBlock(DyeColor color, Properties properties) {
        super(properties.noOcclusion());
        this.color = color;
    }

    public static final MapCodec<StainedGlassCarpetBlock> CODEC = RecordCodecBuilder.mapCodec(
            (instance) -> instance.group(
                    DyeColor.CODEC.fieldOf("color")
                            .forGetter(StainedGlassCarpetBlock::getColor), propertiesCodec()
            ).apply(instance, StainedGlassCarpetBlock::new)
    );

    public @NotNull MapCodec<? extends CarpetBlock> codec() {
        return CODEC;
    }

    @Override
    public @NotNull DyeColor getColor() {
        return this.color;
    }
}
