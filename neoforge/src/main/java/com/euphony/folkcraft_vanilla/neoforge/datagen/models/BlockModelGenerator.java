package com.euphony.folkcraft_vanilla.neoforge.datagen.models;

import com.euphony.folkcraft_vanilla.FolkcraftVanilla;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.DyeColor;
import net.neoforged.neoforge.client.model.generators.BlockModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class BlockModelGenerator extends BlockModelProvider {
    public BlockModelGenerator(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, FolkcraftVanilla.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        pressurePlate("amethyst_pressure_plate", mcLoc("block/amethyst_block"));
        pressurePlateDown("amethyst_pressure_plate_down", mcLoc("block/amethyst_block"));

        cubeAll("compressed_slime_block", mcLoc("block/slime_block"));

        for(DyeColor dyeColor : DyeColor.values()) {
            String color = dyeColor.getName();
            carpet("%s_stained_glass_carpet".formatted(color), mcLoc("block/%s_stained_glass".formatted(color)));
        }
        carpet("glass_carpet", mcLoc("block/glass"));
        carpet("tinted_glass_carpet", mcLoc("block/tinted_glass"));
    }
}
