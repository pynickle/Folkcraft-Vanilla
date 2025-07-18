package com.euphony.folkcraft_vanilla.neoforge.datagen.recipes;

import com.euphony.folkcraft_vanilla.common.init.FCBlocks;
import com.euphony.folkcraft_vanilla.common.init.FCItems;
import com.euphony.folkcraft_vanilla.utils.Utils;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class RecipeGenerator extends RecipeProvider {
    public RecipeGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(@NotNull RecipeOutput recipeOutput) {
        pressurePlate(recipeOutput, FCBlocks.AMETHYST_PRESSURE_PLATE.get().asItem(), Blocks.AMETHYST_BLOCK);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, FCItems.SOUL_RETALIATION_TOTEM.get().asItem())
                .pattern("ASA")
                .pattern("ETE")
                .pattern(" B ")
                .define('A', Items.ECHO_SHARD)
                .define('S', Items.GHAST_TEAR)
                .define('E', Items.CRYING_OBSIDIAN)
                .define('T', Items.TOTEM_OF_UNDYING)
                .define('B', Items.NETHERITE_SCRAP)
                .unlockedBy("has_item", has(Items.TOTEM_OF_UNDYING))
                .save(recipeOutput, createKey("soul_retaliation_totem"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, FCItems.PORTABLE_JUKEBOX.get().asItem())
                .pattern("SDS")
                .pattern("SLS")
                .pattern("PPP")
                .define('S', Items.STRING)
                .define('L', Items.LEATHER)
                .define('D', Items.DIAMOND)
                .define('P', ItemTags.PLANKS)
                .unlockedBy("has_item", has(Items.DIAMOND))
                .save(recipeOutput, createKey("portable_jukebox"));

        buildGlassCarpetRecipes(recipeOutput);

        /*
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, FCItems.GHAST_SNACK_PACK.get())
                .pattern("S")
                .pattern("B")
                .pattern("P")
                .define('S', Items.STRING)
                .define('B', Items.SNOW_BLOCK)
                .define('P', Items.PAPER)
                .unlockedBy("has_item", has(Items.STRING))
                .save(recipeOutput, createKey("ghast_snack_pack"));
        */
    }

    protected void buildGlassCarpetRecipes(RecipeOutput recipeOutput) {
        carpet(recipeOutput, FCBlocks.GLASS_CARPET.get(), Items.GLASS);
        carpet(recipeOutput, FCBlocks.TINTED_GLASS_CARPET.get(), Items.TINTED_GLASS);
        carpet(recipeOutput, FCBlocks.WHITE_STAINED_GLASS_CARPET.get(), Items.WHITE_STAINED_GLASS);
        carpet(recipeOutput, FCBlocks.ORANGE_STAINED_GLASS_CARPET.get(), Items.ORANGE_STAINED_GLASS);
        carpet(recipeOutput, FCBlocks.MAGENTA_STAINED_GLASS_CARPET.get(), Items.MAGENTA_STAINED_GLASS);
        carpet(recipeOutput, FCBlocks.LIGHT_BLUE_STAINED_GLASS_CARPET.get(), Items.LIGHT_BLUE_STAINED_GLASS);
        carpet(recipeOutput, FCBlocks.YELLOW_STAINED_GLASS_CARPET.get(), Items.YELLOW_STAINED_GLASS);
        carpet(recipeOutput, FCBlocks.LIME_STAINED_GLASS_CARPET.get(), Items.LIME_STAINED_GLASS);
        carpet(recipeOutput, FCBlocks.PINK_STAINED_GLASS_CARPET.get(), Items.PINK_STAINED_GLASS);
        carpet(recipeOutput, FCBlocks.GRAY_STAINED_GLASS_CARPET.get(), Items.GRAY_STAINED_GLASS);
        carpet(recipeOutput, FCBlocks.LIGHT_GRAY_STAINED_GLASS_CARPET.get(), Items.LIGHT_GRAY_STAINED_GLASS);
        carpet(recipeOutput, FCBlocks.CYAN_STAINED_GLASS_CARPET.get(), Items.CYAN_STAINED_GLASS);
        carpet(recipeOutput, FCBlocks.PURPLE_STAINED_GLASS_CARPET.get(), Items.PURPLE_STAINED_GLASS);
        carpet(recipeOutput, FCBlocks.BLUE_STAINED_GLASS_CARPET.get(), Items.BLUE_STAINED_GLASS);
        carpet(recipeOutput, FCBlocks.BROWN_STAINED_GLASS_CARPET.get(), Items.BROWN_STAINED_GLASS);
        carpet(recipeOutput, FCBlocks.GREEN_STAINED_GLASS_CARPET.get(), Items.GREEN_STAINED_GLASS);
        carpet(recipeOutput, FCBlocks.RED_STAINED_GLASS_CARPET.get(), Items.RED_STAINED_GLASS);
        carpet(recipeOutput, FCBlocks.BLACK_STAINED_GLASS_CARPET.get(), Items.BLACK_STAINED_GLASS);
    }

    protected ResourceLocation createKey(String name) {
        return Utils.prefix(name);
    }
}