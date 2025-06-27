package com.euphony.folkcraft_vanilla.neoforge.datagen.models;

import com.euphony.folkcraft_vanilla.FolkcraftVanilla;
import com.euphony.folkcraft_vanilla.common.init.FCBlocks;
import com.euphony.folkcraft_vanilla.common.init.FCItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ItemModelGenerator extends ItemModelProvider {
    ExistingFileHelper existingFileHelper;

    public ItemModelGenerator(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, FolkcraftVanilla.MOD_ID, existingFileHelper);
        this.existingFileHelper = existingFileHelper;
    }

    @Override
    protected void registerModels() {
        simpleBlockItem(FCBlocks.AMETHYST_PRESSURE_PLATE.get());
        simpleBlockItem(FCBlocks.COMPRESSED_SLIME_BLOCK.get());

        simpleBlockItem(FCBlocks.GLASS_CARPET.get());
        simpleBlockItem(FCBlocks.TINTED_GLASS_CARPET.get());

        basicItem(FCItems.SOUL_RETALIATION_TOTEM.get());

        simpleBlockItem(FCBlocks.WHITE_STAINED_GLASS_CARPET.get());
        simpleBlockItem(FCBlocks.ORANGE_STAINED_GLASS_CARPET.get());
        simpleBlockItem(FCBlocks.MAGENTA_STAINED_GLASS_CARPET.get());
        simpleBlockItem(FCBlocks.LIGHT_BLUE_STAINED_GLASS_CARPET.get());
        simpleBlockItem(FCBlocks.YELLOW_STAINED_GLASS_CARPET.get());
        simpleBlockItem(FCBlocks.LIME_STAINED_GLASS_CARPET.get());
        simpleBlockItem(FCBlocks.PINK_STAINED_GLASS_CARPET.get());
        simpleBlockItem(FCBlocks.GRAY_STAINED_GLASS_CARPET.get());
        simpleBlockItem(FCBlocks.LIGHT_GRAY_STAINED_GLASS_CARPET.get());
        simpleBlockItem(FCBlocks.CYAN_STAINED_GLASS_CARPET.get());
        simpleBlockItem(FCBlocks.PURPLE_STAINED_GLASS_CARPET.get());
        simpleBlockItem(FCBlocks.BLUE_STAINED_GLASS_CARPET.get());
        simpleBlockItem(FCBlocks.BROWN_STAINED_GLASS_CARPET.get());
        simpleBlockItem(FCBlocks.GREEN_STAINED_GLASS_CARPET.get());
        simpleBlockItem(FCBlocks.RED_STAINED_GLASS_CARPET.get());
        simpleBlockItem(FCBlocks.BLACK_STAINED_GLASS_CARPET.get());

        // basicItem(FCItems.GHAST_SNACK_PACK.get());
    }
}
