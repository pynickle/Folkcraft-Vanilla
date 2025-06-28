package com.euphony.folkcraft_vanilla.neoforge.datagen.languages;

import com.euphony.folkcraft_vanilla.FolkcraftVanilla;
import com.euphony.folkcraft_vanilla.common.init.FCBlocks;
import com.euphony.folkcraft_vanilla.common.init.FCCreativeTabs;
import com.euphony.folkcraft_vanilla.common.init.FCItems;
import com.euphony.folkcraft_vanilla.utils.FormatUtils;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.DyeColor;
import net.neoforged.neoforge.common.data.LanguageProvider;

import java.util.function.Supplier;

public class LanguageGenerator extends LanguageProvider {
    public LanguageGenerator(PackOutput output) {
        super(output, FolkcraftVanilla.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        addItemGroup(FCCreativeTabs.BLOCKS_TAB, "Blocks");
        addItemGroup(FCCreativeTabs.ITEMS_TAB, "Items");
        addItemGroup(FCCreativeTabs.DECORATION_BLOCKS_TAB, "Decoration Blocks");

        addBlock(FCBlocks.AMETHYST_PRESSURE_PLATE, "Amethyst Pressure Plate");
        addBlock(FCBlocks.COMPRESSED_SLIME_BLOCK, "Compressed Slime Block");

        addBlock(FCBlocks.GLASS_CARPET, "Glass Carpet");
        addBlock(FCBlocks.TINTED_GLASS_CARPET, "Tinted Glass Carpet");

        addItem(FCItems.SOUL_RETALIATION_TOTEM, "Soul Retaliation Totem");
        addItem(FCItems.PORTABLE_JUKEBOX, "Portable Jukebox");

        for(DyeColor dyeColor : DyeColor.values()) {
            String color = dyeColor.getName();
            add("block.%s.%s_stained_glass_carpet".formatted(FolkcraftVanilla.MOD_ID, color), FormatUtils.formatName(color) + " Stained Glass Carpet");
        }
    }

    public void addItemGroup(Supplier<? extends CreativeModeTab> tab, String name) {
        add("itemGroup.%s.%s".formatted(FolkcraftVanilla.MOD_ID, BuiltInRegistries.CREATIVE_MODE_TAB.getKey(tab.get()).getPath()), name);
    }
}
