package com.euphony.folkcraft_vanilla.common.init;

import com.euphony.folkcraft_vanilla.FolkcraftVanilla;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class FCCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(FolkcraftVanilla.MOD_ID, Registries.CREATIVE_MODE_TAB);

    public static final RegistrySupplier<CreativeModeTab> BLOCKS_TAB;
    public static final RegistrySupplier<CreativeModeTab> DECORATION_BLOCKS_TAB;
    public static final RegistrySupplier<CreativeModeTab> ITEMS_TAB;

    static {
        BLOCKS_TAB = TABS.register(
                "blocks",
                () ->
                        CreativeModeTab.builder(CreativeModeTab.Row.TOP, 1)
                                .title(Component.translatable("itemGroup.fc_vanilla.blocks"))
                                .icon(() -> new ItemStack(FCBlocks.AMETHYST_PRESSURE_PLATE.get()))
                                .displayItems(
                                        (parameters, output) -> {
                                            output.accept(FCBlocks.AMETHYST_PRESSURE_PLATE.get());
                                            output.accept(FCBlocks.COMPRESSED_SLIME_BLOCK.get());
                                        })
                                .build()
        );
        DECORATION_BLOCKS_TAB = TABS.register(
                "decoration_blocks",
                () ->
                        CreativeModeTab.builder(CreativeModeTab.Row.TOP, 1)
                                .title(Component.translatable("itemGroup.fc_vanilla.decoration_blocks"))
                                .icon(() -> new ItemStack(FCBlocks.GLASS_CARPET.get()))
                                .displayItems(
                                        (parameters, output) -> {
                                            output.accept(FCBlocks.GLASS_CARPET.get());
                                            output.accept(FCBlocks.TINTED_GLASS_CARPET.get());
                                            output.accept(FCBlocks.WHITE_STAINED_GLASS_CARPET.get());
                                            output.accept(FCBlocks.ORANGE_STAINED_GLASS_CARPET.get());
                                            output.accept(FCBlocks.MAGENTA_STAINED_GLASS_CARPET.get());
                                            output.accept(FCBlocks.LIGHT_BLUE_STAINED_GLASS_CARPET.get());
                                            output.accept(FCBlocks.YELLOW_STAINED_GLASS_CARPET.get());
                                            output.accept(FCBlocks.LIME_STAINED_GLASS_CARPET.get());
                                            output.accept(FCBlocks.PINK_STAINED_GLASS_CARPET.get());
                                            output.accept(FCBlocks.GRAY_STAINED_GLASS_CARPET.get());
                                            output.accept(FCBlocks.LIGHT_GRAY_STAINED_GLASS_CARPET.get());
                                            output.accept(FCBlocks.CYAN_STAINED_GLASS_CARPET.get());
                                            output.accept(FCBlocks.PURPLE_STAINED_GLASS_CARPET.get());
                                            output.accept(FCBlocks.BLUE_STAINED_GLASS_CARPET.get());
                                            output.accept(FCBlocks.BROWN_STAINED_GLASS_CARPET.get());
                                            output.accept(FCBlocks.GREEN_STAINED_GLASS_CARPET.get());
                                            output.accept(FCBlocks.RED_STAINED_GLASS_CARPET.get());
                                            output.accept(FCBlocks.BLACK_STAINED_GLASS_CARPET.get());

                                        })
                                .build()
        );
        ITEMS_TAB = TABS.register(
                "items",
                () ->
                        CreativeModeTab.builder(CreativeModeTab.Row.TOP, 1)
                                .title(Component.translatable("itemGroup.fc_vanilla.items"))
                                .icon(() -> new ItemStack(FCItems.SOUL_RETALIATION_TOTEM))
                                .displayItems(
                                        (parameters, output) -> {
                                            output.accept(FCItems.SOUL_RETALIATION_TOTEM.get());
                                            output.accept(FCItems.PORTABLE_JUKEBOX.get());
                                        })
                                .build()
        );
    }
}
