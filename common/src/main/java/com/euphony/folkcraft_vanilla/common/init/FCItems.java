package com.euphony.folkcraft_vanilla.common.init;

import com.euphony.folkcraft_vanilla.FolkcraftVanilla;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.function.Function;

public class FCItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(FolkcraftVanilla.MOD_ID, Registries.ITEM);

    // public static final RegistrySupplier<Item> GHAST_SNACK_PACK = register("ghast_snack_pack", GhastSnackPackItem::new);

    public static <T extends Item> RegistrySupplier<T> register(String name, Function<Item.Properties, T> func, Item.Properties properties) {
        return ITEMS.register(name, () -> func.apply(properties));
    }

    public static <T extends Item> RegistrySupplier<T> register(String name, Function<Item.Properties, T> func) {
        return ITEMS.register(name, () -> func.apply(new Item.Properties()));
    }

    public static RegistrySupplier<Item> register(String name, Item.Properties properties) {
        return ITEMS.register(name, () -> new Item(properties));
    }

    public static void registerBlockItem(String name, RegistrySupplier<? extends Block> block) {
        ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }
}
