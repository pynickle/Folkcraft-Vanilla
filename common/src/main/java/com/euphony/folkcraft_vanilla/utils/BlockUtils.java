package com.euphony.folkcraft_vanilla.utils;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

public class BlockUtils {
    public static String name(Block block) {
        return key(block).getPath();
    }

    public static ResourceLocation key(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block);
    }
}
