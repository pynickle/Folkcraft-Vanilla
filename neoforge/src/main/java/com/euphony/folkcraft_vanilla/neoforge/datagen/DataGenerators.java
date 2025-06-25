package com.euphony.folkcraft_vanilla.neoforge.datagen;

import com.euphony.folkcraft_vanilla.FolkcraftVanilla;
import com.euphony.folkcraft_vanilla.neoforge.datagen.models.BlockModelGenerator;
import com.euphony.folkcraft_vanilla.neoforge.datagen.models.ItemModelGenerator;
import com.euphony.folkcraft_vanilla.neoforge.datagen.recipes.RecipeGenerator;
import com.euphony.folkcraft_vanilla.neoforge.datagen.tag.BlockTagGenerator;
import com.euphony.folkcraft_vanilla.neoforge.datagen.tag.ItemTagGenerator;
import net.minecraft.DetectedVersion;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.metadata.PackMetadataGenerator;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.metadata.pack.PackMetadataSection;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = FolkcraftVanilla.MOD_ID)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        DatapackBuiltinEntriesProvider datapackProvider = new RegistryDataGenerator(output, event.getLookupProvider());
        CompletableFuture<HolderLookup.Provider> lookupProvider = datapackProvider.getRegistryProvider();

        generator.addProvider(true, new BlockModelGenerator(output, existingFileHelper));
        generator.addProvider(true, new ItemModelGenerator(output, existingFileHelper));
        generator.addProvider(true, new BlockStateGenerator(output, existingFileHelper));

        generator.addProvider(true, datapackProvider);

        BlockTagGenerator blockTagGenerator = new BlockTagGenerator(output, lookupProvider, existingFileHelper);
        generator.addProvider(true, blockTagGenerator);
        generator.addProvider(true, new ItemTagGenerator(output, lookupProvider, blockTagGenerator.contentsGetter(), existingFileHelper));

        generator.addProvider(true, new LootGenerator(output, lookupProvider));
        generator.addProvider(true, new RecipeGenerator(output, lookupProvider));
        generator.addProvider(true, new LanguageGenerator(output));

        generator.addProvider(true, new PackMetadataGenerator(output).add(PackMetadataSection.TYPE, new PackMetadataSection(
                Component.literal("Resources for Folkcraft Vanilla"),
                DetectedVersion.BUILT_IN.getPackVersion(PackType.SERVER_DATA),
                Optional.empty())));
    }
}
