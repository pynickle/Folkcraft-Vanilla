package com.euphony.folkcraft_vanilla.neoforge.datagen.tags;

import com.euphony.folkcraft_vanilla.FolkcraftVanilla;
import com.euphony.folkcraft_vanilla.common.init.FCBlocks;
import com.euphony.folkcraft_vanilla.utils.Utils;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class BlockTagGenerator extends BlockTagsProvider {
    public BlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, FolkcraftVanilla.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(FCBlocks.AMETHYST_PRESSURE_PLATE.get(),
                FCBlocks.GLASS_CARPET.get(),
                FCBlocks.TINTED_GLASS_CARPET.get(),
                FCBlocks.WHITE_STAINED_GLASS_CARPET.get(),
                FCBlocks.ORANGE_STAINED_GLASS_CARPET.get(),
                FCBlocks.MAGENTA_STAINED_GLASS_CARPET.get(),
                FCBlocks.LIGHT_BLUE_STAINED_GLASS_CARPET.get(),
                FCBlocks.YELLOW_STAINED_GLASS_CARPET.get(),
                FCBlocks.LIME_STAINED_GLASS_CARPET.get(),
                FCBlocks.PINK_STAINED_GLASS_CARPET.get(),
                FCBlocks.GRAY_STAINED_GLASS_CARPET.get(),
                FCBlocks.LIGHT_GRAY_STAINED_GLASS_CARPET.get(),
                FCBlocks.CYAN_STAINED_GLASS_CARPET.get(),
                FCBlocks.PURPLE_STAINED_GLASS_CARPET.get(),
                FCBlocks.BLUE_STAINED_GLASS_CARPET.get(),
                FCBlocks.BROWN_STAINED_GLASS_CARPET.get(),
                FCBlocks.GREEN_STAINED_GLASS_CARPET.get(),
                FCBlocks.RED_STAINED_GLASS_CARPET.get(),
                FCBlocks.BLACK_STAINED_GLASS_CARPET.get());
    }

    public static TagKey<Block> create(String tagName) {
        return BlockTags.create(Utils.prefix(tagName));
    }

    public static TagKey<Block> create(String namespace, String tagName) {
        return BlockTags.create(ResourceLocation.fromNamespaceAndPath(namespace, tagName));
    }

    public static TagKey<Block> createVanillaTag(String tagName) {
        return BlockTags.create(ResourceLocation.withDefaultNamespace(tagName));
    }

    public static TagKey<Block> createCTag(String tagName) {
        return BlockTags.create(ResourceLocation.fromNamespaceAndPath("c", tagName));
    }

    @SafeVarargs
    public final <T extends Block> void add(TagKey<Block> tag, T... blocks) {
        tag(tag).add(blocks);
    }
}
