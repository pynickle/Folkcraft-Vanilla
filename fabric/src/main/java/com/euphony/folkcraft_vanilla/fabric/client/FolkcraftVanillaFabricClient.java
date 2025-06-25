package com.euphony.folkcraft_vanilla.fabric.client;

import com.euphony.folkcraft_vanilla.FolkcraftVanillaClient;
import net.fabricmc.api.ClientModInitializer;

public final class FolkcraftVanillaFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        FolkcraftVanillaClient.init();
    }
}
