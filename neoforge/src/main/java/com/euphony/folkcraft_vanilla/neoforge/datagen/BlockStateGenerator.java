package com.euphony.folkcraft_vanilla.neoforge.datagen;

import com.euphony.folkcraft_vanilla.FolkcraftVanilla;
import com.euphony.folkcraft_vanilla.common.init.FCBlocks;
import com.euphony.folkcraft_vanilla.utils.BlockUtils;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.BasePressurePlateBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class BlockStateGenerator extends BlockStateProvider {
    ExistingFileHelper existingFileHelper;
    public BlockStateGenerator(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, FolkcraftVanilla.MOD_ID, exFileHelper);
        this.existingFileHelper = exFileHelper;

    }

    @Override
    protected void registerStatesAndModels() {
        pressurePlateBlock(FCBlocks.AMETHYST_PRESSURE_PLATE.get(), mcLoc("block/amethyst_block"));

        compressedSlimeBlock(FCBlocks.COMPRESSED_SLIME_BLOCK.get());

        simpleBlock(FCBlocks.GLASS_CARPET.get(), models().carpet("glass_carpet", mcLoc("block/glass")));
        simpleBlock(FCBlocks.TINTED_GLASS_CARPET.get(), models().carpet("tinted_glass_carpet", mcLoc("block/tinted_glass")));

        simpleBlock(FCBlocks.WHITE_STAINED_GLASS_CARPET.get(), models().carpet("white_stained_glass_carpet", mcLoc("block/white_stained_glass")));
        simpleBlock(FCBlocks.ORANGE_STAINED_GLASS_CARPET.get(), models().carpet("orange_stained_glass_carpet", mcLoc("block/orange_stained_glass")));
        simpleBlock(FCBlocks.MAGENTA_STAINED_GLASS_CARPET.get(), models().carpet("magenta_stained_glass_carpet", mcLoc("block/magenta_stained_glass")));
        simpleBlock(FCBlocks.LIGHT_BLUE_STAINED_GLASS_CARPET.get(), models().carpet("light_blue_stained_glass_carpet", mcLoc("block/light_blue_stained_glass")));
        simpleBlock(FCBlocks.YELLOW_STAINED_GLASS_CARPET.get(), models().carpet("yellow_stained_glass_carpet", mcLoc("block/yellow_stained_glass")));
        simpleBlock(FCBlocks.LIME_STAINED_GLASS_CARPET.get(), models().carpet("lime_stained_glass_carpet", mcLoc("block/lime_stained_glass")));
        simpleBlock(FCBlocks.PINK_STAINED_GLASS_CARPET.get(), models().carpet("pink_stained_glass_carpet", mcLoc("block/pink_stained_glass")));
        simpleBlock(FCBlocks.GRAY_STAINED_GLASS_CARPET.get(), models().carpet("gray_stained_glass_carpet", mcLoc("block/gray_stained_glass")));
        simpleBlock(FCBlocks.LIGHT_GRAY_STAINED_GLASS_CARPET.get(), models().carpet("light_gray_stained_glass_carpet", mcLoc("block/light_gray_stained_glass")));
        simpleBlock(FCBlocks.CYAN_STAINED_GLASS_CARPET.get(), models().carpet("cyan_stained_glass_carpet", mcLoc("block/cyan_stained_glass")));
        simpleBlock(FCBlocks.PURPLE_STAINED_GLASS_CARPET.get(), models().carpet("purple_stained_glass_carpet", mcLoc("block/purple_stained_glass")));
        simpleBlock(FCBlocks.BLUE_STAINED_GLASS_CARPET.get(), models().carpet("blue_stained_glass_carpet", mcLoc("block/blue_stained_glass")));
        simpleBlock(FCBlocks.BROWN_STAINED_GLASS_CARPET.get(), models().carpet("brown_stained_glass_carpet", mcLoc("block/brown_stained_glass")));
        simpleBlock(FCBlocks.GREEN_STAINED_GLASS_CARPET.get(), models().carpet("green_stained_glass_carpet", mcLoc("block/green_stained_glass")));
        simpleBlock(FCBlocks.RED_STAINED_GLASS_CARPET.get(), models().carpet("red_stained_glass_carpet", mcLoc("block/red_stained_glass")));
        simpleBlock(FCBlocks.BLACK_STAINED_GLASS_CARPET.get(), models().carpet("black_stained_glass_carpet", mcLoc("block/black_stained_glass")));
    }

    public void compressedSlimeBlock(Block block) {
        simpleBlock(block, models().cubeAll(BlockUtils.name(block), blockTexture(Blocks.SLIME_BLOCK)));
    }

    public void pressurePlateBlock(BasePressurePlateBlock block, ResourceLocation texture) {
        ModelFile pressurePlate = models().pressurePlate(BlockUtils.name(block), texture);
        ModelFile pressurePlateDown = models().pressurePlateDown(BlockUtils.name(block) + "_down", texture);
        pressurePlateBlock(block, pressurePlate, pressurePlateDown);
    }

    public void pressurePlateBlock(BasePressurePlateBlock block, ModelFile pressurePlate, ModelFile pressurePlateDown) {
        getVariantBuilder(block)
                .partialState().with(PressurePlateBlock.POWERED, true).addModels(new ConfiguredModel(pressurePlateDown))
                .partialState().with(PressurePlateBlock.POWERED, false).addModels(new ConfiguredModel(pressurePlate));
    }
}
