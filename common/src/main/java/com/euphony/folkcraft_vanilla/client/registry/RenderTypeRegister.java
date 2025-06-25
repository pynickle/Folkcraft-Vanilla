package com.euphony.folkcraft_vanilla.client.registry;

import com.euphony.folkcraft_vanilla.common.init.FCBlocks;
import dev.architectury.registry.client.rendering.RenderTypeRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class RenderTypeRegister {
    public static void registerRenderType() {
        RenderTypeRegistry.register(net.minecraft.client.renderer.RenderType.translucent(),
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
                FCBlocks.BLACK_STAINED_GLASS_CARPET.get()
        );
        RenderTypeRegistry.register(net.minecraft.client.renderer.RenderType.cutoutMipped(),
                FCBlocks.GLASS_CARPET.get()
        );
    }
}
