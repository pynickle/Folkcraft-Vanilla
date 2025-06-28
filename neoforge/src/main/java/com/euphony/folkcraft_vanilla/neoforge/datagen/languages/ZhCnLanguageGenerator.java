package com.euphony.folkcraft_vanilla.neoforge.datagen.languages;

import com.euphony.folkcraft_vanilla.FolkcraftVanilla;
import com.euphony.folkcraft_vanilla.common.init.FCBlocks;
import com.euphony.folkcraft_vanilla.common.init.FCCreativeTabs;
import com.euphony.folkcraft_vanilla.common.init.FCItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.neoforge.common.data.LanguageProvider;

import java.util.function.Supplier;

public class ZhCnLanguageGenerator extends LanguageProvider {
    public ZhCnLanguageGenerator(PackOutput output) {
        super(output, FolkcraftVanilla.MOD_ID, "zh_cn");
    }

    @Override
    protected void addTranslations() {
        addItemGroup(FCCreativeTabs.BLOCKS_TAB, "方块");
        addItemGroup(FCCreativeTabs.ITEMS_TAB, "物品");
        addItemGroup(FCCreativeTabs.DECORATION_BLOCKS_TAB, "装饰方块");

        addBlock(FCBlocks.AMETHYST_PRESSURE_PLATE, "紫水晶压力板");
        addBlock(FCBlocks.COMPRESSED_SLIME_BLOCK, "压缩黏液块");

        addBlock(FCBlocks.GLASS_CARPET, "玻璃地毯");
        addBlock(FCBlocks.TINTED_GLASS_CARPET, "遮光玻璃地毯");

        addItem(FCItems.SOUL_RETALIATION_TOTEM, "灵魂复仇图腾");
        addItem(FCItems.PORTABLE_JUKEBOX, "便携式唱片机");

        addBlock(FCBlocks.WHITE_STAINED_GLASS_CARPET, "白色染色玻璃地毯");
        addBlock(FCBlocks.ORANGE_STAINED_GLASS_CARPET, "橙色染色玻璃地毯");
        addBlock(FCBlocks.MAGENTA_STAINED_GLASS_CARPET, "品红色染色玻璃地毯");
        addBlock(FCBlocks.LIGHT_BLUE_STAINED_GLASS_CARPET, "淡蓝色染色玻璃地毯");
        addBlock(FCBlocks.YELLOW_STAINED_GLASS_CARPET, "黄色染色玻璃地毯");
        addBlock(FCBlocks.LIME_STAINED_GLASS_CARPET, "黄绿色染色玻璃地毯");
        addBlock(FCBlocks.PINK_STAINED_GLASS_CARPET, "粉红色染色玻璃地毯");
        addBlock(FCBlocks.GRAY_STAINED_GLASS_CARPET, "灰色染色玻璃地毯");
        addBlock(FCBlocks.LIGHT_GRAY_STAINED_GLASS_CARPET, "淡灰色染色玻璃地毯");
        addBlock(FCBlocks.CYAN_STAINED_GLASS_CARPET, "青色染色玻璃地毯");
        addBlock(FCBlocks.PURPLE_STAINED_GLASS_CARPET, "紫色染色玻璃地毯");
        addBlock(FCBlocks.BLUE_STAINED_GLASS_CARPET, "蓝色染色玻璃地毯");
        addBlock(FCBlocks.BROWN_STAINED_GLASS_CARPET, "棕色染色玻璃地毯");
        addBlock(FCBlocks.GREEN_STAINED_GLASS_CARPET, "绿色染色玻璃地毯");
        addBlock(FCBlocks.RED_STAINED_GLASS_CARPET, "红色染色玻璃地毯");
        addBlock(FCBlocks.BLACK_STAINED_GLASS_CARPET, "黑色染色玻璃地毯");
    }

    public void addItemGroup(Supplier<? extends CreativeModeTab> tab, String name) {
        add("itemGroup.%s.%s".formatted(FolkcraftVanilla.MOD_ID, BuiltInRegistries.CREATIVE_MODE_TAB.getKey(tab.get()).getPath()), name);
    }
}
