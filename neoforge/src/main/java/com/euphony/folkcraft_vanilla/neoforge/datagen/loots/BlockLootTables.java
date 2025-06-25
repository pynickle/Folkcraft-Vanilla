package com.euphony.folkcraft_vanilla.neoforge.datagen.loots;

import com.euphony.folkcraft_vanilla.FolkcraftVanilla;
import com.euphony.folkcraft_vanilla.common.init.FCBlocks;
import com.google.common.collect.ImmutableSet;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

public class BlockLootTables extends BlockLootSubProvider {
    public BlockLootTables(HolderLookup.Provider registries) {
        super(ImmutableSet.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return BuiltInRegistries.BLOCK.stream()
                .filter(block -> BuiltInRegistries.BLOCK.getKey(block).getNamespace().equals(FolkcraftVanilla.MOD_ID)).toList();
    }

    @Override
    protected void generate() {
        dropSelf(FCBlocks.AMETHYST_PRESSURE_PLATE.get());
        dropSelf(FCBlocks.COMPRESSED_SLIME_BLOCK.get());

        dropSelf(FCBlocks.GLASS_CARPET.get());
        dropSelf(FCBlocks.TINTED_GLASS_CARPET.get());

        dropSelf(FCBlocks.WHITE_STAINED_GLASS_CARPET.get());
        dropSelf(FCBlocks.ORANGE_STAINED_GLASS_CARPET.get());
        dropSelf(FCBlocks.MAGENTA_STAINED_GLASS_CARPET.get());
        dropSelf(FCBlocks.LIGHT_BLUE_STAINED_GLASS_CARPET.get());
        dropSelf(FCBlocks.YELLOW_STAINED_GLASS_CARPET.get());
        dropSelf(FCBlocks.LIME_STAINED_GLASS_CARPET.get());
        dropSelf(FCBlocks.PINK_STAINED_GLASS_CARPET.get());
        dropSelf(FCBlocks.GRAY_STAINED_GLASS_CARPET.get());
        dropSelf(FCBlocks.LIGHT_GRAY_STAINED_GLASS_CARPET.get());
        dropSelf(FCBlocks.CYAN_STAINED_GLASS_CARPET.get());
        dropSelf(FCBlocks.PURPLE_STAINED_GLASS_CARPET.get());
        dropSelf(FCBlocks.BLUE_STAINED_GLASS_CARPET.get());
        dropSelf(FCBlocks.BROWN_STAINED_GLASS_CARPET.get());
        dropSelf(FCBlocks.GREEN_STAINED_GLASS_CARPET.get());
        dropSelf(FCBlocks.RED_STAINED_GLASS_CARPET.get());
        dropSelf(FCBlocks.BLACK_STAINED_GLASS_CARPET.get());
    }
}
