package com.euphony.folkcraft_vanilla.neoforge.client;

import com.euphony.folkcraft_vanilla.FolkcraftVanilla;
import com.euphony.folkcraft_vanilla.FolkcraftVanillaClient;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(modid = FolkcraftVanilla.MOD_ID, value = Dist.CLIENT)
public class FolkcraftVanillaNeoForgeClient {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        FolkcraftVanillaClient.init();
    }
}
