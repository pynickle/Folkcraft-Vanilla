package com.euphony.folkcraft_vanilla.utils;

import com.euphony.folkcraft_vanilla.FolkcraftVanilla;
import dev.architectury.platform.Platform;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

import java.util.Locale;

public class Utils {
    public static String getModDisplayName(String modId) {
        return Platform.getMod(modId).getName();
    }

    public static ResourceLocation prefix(String name) {
        return ResourceLocation.fromNamespaceAndPath(FolkcraftVanilla.MOD_ID, name.toLowerCase(Locale.ROOT));
    }

    public static <T> ResourceKey<T> key(ResourceKey<? extends Registry<T>> registry, String path) {
        return ResourceKey.create(registry, prefix(path));
    }
}
