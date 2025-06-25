package com.euphony.folkcraft_vanilla.common.init;

import com.euphony.folkcraft_vanilla.FolkcraftVanilla;
import com.euphony.folkcraft_vanilla.common.block.AmethystPressurePlateBlock;
import com.euphony.folkcraft_vanilla.common.block.CompressedSlimeBlock;
import com.euphony.folkcraft_vanilla.common.block.StainedGlassCarpetBlock;
import com.euphony.folkcraft_vanilla.common.block.TintedGlassCarpetBlock;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CarpetBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Function;

public class FCBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(FolkcraftVanilla.MOD_ID, Registries.BLOCK);

    public static final RegistrySupplier<AmethystPressurePlateBlock> AMETHYST_PRESSURE_PLATE = registerWithItem("amethyst_pressure_plate",
            AmethystPressurePlateBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE));

    public static final RegistrySupplier<CompressedSlimeBlock> COMPRESSED_SLIME_BLOCK = registerWithItem("compressed_slime_block",
            CompressedSlimeBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SLIME_BLOCK));

    public static final RegistrySupplier<CarpetBlock> GLASS_CARPET = registerWithItem("glass_carpet",
           CarpetBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS).noOcclusion());
    public static final RegistrySupplier<TintedGlassCarpetBlock> TINTED_GLASS_CARPET = registerWithItem("tinted_glass_carpet",
            TintedGlassCarpetBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.TINTED_GLASS).noOcclusion());

    public static final RegistrySupplier<StainedGlassCarpetBlock> WHITE_STAINED_GLASS_CARPET = registerWithItem("white_stained_glass_carpet",
            (p) -> new StainedGlassCarpetBlock(DyeColor.WHITE, p), Blocks.WHITE_STAINED_GLASS);
    public static final RegistrySupplier<StainedGlassCarpetBlock> ORANGE_STAINED_GLASS_CARPET = registerWithItem("orange_stained_glass_carpet",
            (p) -> new StainedGlassCarpetBlock(DyeColor.ORANGE, p), Blocks.ORANGE_STAINED_GLASS);
    public static final RegistrySupplier<StainedGlassCarpetBlock> MAGENTA_STAINED_GLASS_CARPET = registerWithItem("magenta_stained_glass_carpet",
            (p) -> new StainedGlassCarpetBlock(DyeColor.MAGENTA, p), Blocks.MAGENTA_STAINED_GLASS);
    public static final RegistrySupplier<StainedGlassCarpetBlock> LIGHT_BLUE_STAINED_GLASS_CARPET = registerWithItem("light_blue_stained_glass_carpet",
            (p) -> new StainedGlassCarpetBlock(DyeColor.LIGHT_BLUE, p), Blocks.LIGHT_BLUE_STAINED_GLASS);
    public static final RegistrySupplier<StainedGlassCarpetBlock> YELLOW_STAINED_GLASS_CARPET = registerWithItem("yellow_stained_glass_carpet",
            (p) -> new StainedGlassCarpetBlock(DyeColor.YELLOW, p), Blocks.YELLOW_STAINED_GLASS);
    public static final RegistrySupplier<StainedGlassCarpetBlock> LIME_STAINED_GLASS_CARPET = registerWithItem("lime_stained_glass_carpet",
            (p) -> new StainedGlassCarpetBlock(DyeColor.LIME, p), Blocks.LIME_STAINED_GLASS);
    public static final RegistrySupplier<StainedGlassCarpetBlock> PINK_STAINED_GLASS_CARPET = registerWithItem("pink_stained_glass_carpet",
            (p) -> new StainedGlassCarpetBlock(DyeColor.PINK, p), Blocks.PINK_STAINED_GLASS);
    public static final RegistrySupplier<StainedGlassCarpetBlock> GRAY_STAINED_GLASS_CARPET = registerWithItem("gray_stained_glass_carpet",
            (p) -> new StainedGlassCarpetBlock(DyeColor.GRAY, p), Blocks.GRAY_STAINED_GLASS);
    public static final RegistrySupplier<StainedGlassCarpetBlock> LIGHT_GRAY_STAINED_GLASS_CARPET = registerWithItem("light_gray_stained_glass_carpet",
            (p) -> new StainedGlassCarpetBlock(DyeColor.LIGHT_GRAY, p), Blocks.LIGHT_GRAY_STAINED_GLASS);
    public static final RegistrySupplier<StainedGlassCarpetBlock> CYAN_STAINED_GLASS_CARPET = registerWithItem("cyan_stained_glass_carpet",
            (p) -> new StainedGlassCarpetBlock(DyeColor.CYAN, p), Blocks.CYAN_STAINED_GLASS);
    public static final RegistrySupplier<StainedGlassCarpetBlock> PURPLE_STAINED_GLASS_CARPET = registerWithItem("purple_stained_glass_carpet",
            (p) -> new StainedGlassCarpetBlock(DyeColor.PURPLE, p), Blocks.PURPLE_STAINED_GLASS);
    public static final RegistrySupplier<StainedGlassCarpetBlock> BLUE_STAINED_GLASS_CARPET = registerWithItem("blue_stained_glass_carpet",
            (p) -> new StainedGlassCarpetBlock(DyeColor.BLUE, p), Blocks.BLUE_STAINED_GLASS);
    public static final RegistrySupplier<StainedGlassCarpetBlock> BROWN_STAINED_GLASS_CARPET = registerWithItem("brown_stained_glass_carpet",
            (p) -> new StainedGlassCarpetBlock(DyeColor.BROWN, p), Blocks.BROWN_STAINED_GLASS);
    public static final RegistrySupplier<StainedGlassCarpetBlock> GREEN_STAINED_GLASS_CARPET = registerWithItem("green_stained_glass_carpet",
            (p) -> new StainedGlassCarpetBlock(DyeColor.GREEN, p), Blocks.GREEN_STAINED_GLASS);
    public static final RegistrySupplier<StainedGlassCarpetBlock> RED_STAINED_GLASS_CARPET = registerWithItem("red_stained_glass_carpet",
            (p) -> new StainedGlassCarpetBlock(DyeColor.RED, p), Blocks.RED_STAINED_GLASS);
    public static final RegistrySupplier<StainedGlassCarpetBlock> BLACK_STAINED_GLASS_CARPET = registerWithItem("black_stained_glass_carpet",
            (p) -> new StainedGlassCarpetBlock(DyeColor.BLACK, p), Blocks.BLACK_STAINED_GLASS);

    public static <T extends Block> RegistrySupplier<T> register(String name, Function<BlockBehaviour.Properties, T> func, Block block) {
        return BLOCKS.register(name, () -> func.apply(BlockBehaviour.Properties.ofFullCopy(block)));
    }

    public static <T extends Block> RegistrySupplier<T> registerWithItem(String name, Function<BlockBehaviour.Properties, T> func, Block block) {
        RegistrySupplier<T> ret = register(name, func, block);
        FCItems.registerBlockItem(name, ret);
        return ret;
    }

    public static <T extends Block> RegistrySupplier<T> register(String name, Function<BlockBehaviour.Properties, T> func, BlockBehaviour.Properties properties) {
        return BLOCKS.register(name, () -> func.apply(properties));
    }

    public static <T extends Block> RegistrySupplier<T> registerWithItem(String name, Function<BlockBehaviour.Properties, T> func, BlockBehaviour.Properties properties) {
        RegistrySupplier<T> ret = register(name, func, properties);
        FCItems.registerBlockItem(name, ret);
        return ret;
    }
}
