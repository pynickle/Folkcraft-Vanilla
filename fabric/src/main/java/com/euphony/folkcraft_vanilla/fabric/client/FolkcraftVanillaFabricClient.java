package com.euphony.folkcraft_vanilla.fabric.client;

import com.euphony.folkcraft_vanilla.client.FolkcraftVanillaClient;
import net.fabricmc.api.ClientModInitializer;

public final class FolkcraftVanillaFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // This entrypoint is suitable for setting up client-specific logic, such as rendering.
        FolkcraftVanillaClient.init();
    }
}
