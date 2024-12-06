package com.none.generator;

import com.none.registry.ModBlocks;
import com.none.registry.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;

import java.util.function.Consumer;

/**
 * @author yuchenxue
 * @date 2024/12/04 - 13:56
 */

public class ModRecipesProvider extends FabricRecipeProvider {

    public ModRecipesProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> consumer) {
        offerReversibleCompactingRecipes(consumer, RecipeCategory.MISC, ModItems.OPAL,
                RecipeCategory.BUILDING_BLOCKS, ModBlocks.OPAL_BLOCK.asItem());

        // 传送门打火石
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.PORTAL_IGNITE)
                .pattern(" # ")
                .pattern("IFI")
                .input('#', Items.DIAMOND)
                .input('I', Items.IRON_INGOT)
                .input('F', Items.FLINT_AND_STEEL)
                .criterion(hasItem(Items.DIAMOND), conditionsFromItem(Items.DIAMOND))
                .offerTo(consumer, getItemPath(ModItems.PORTAL_IGNITE));

        createSword(consumer, ModItems.OPAL, ModItems.OPAL_SWORD);
        createAxe(consumer, ModItems.OPAL, ModItems.OPAL_AXE);
        createPickaxe(consumer, ModItems.OPAL, ModItems.OPAL_PICKAXE);
        createShovel(consumer, ModItems.OPAL, ModItems.OPAL_SHOVEL);
        createHoe(consumer, ModItems.OPAL, ModItems.OPAL_HOE);
    }

    private void createSword(Consumer<RecipeJsonProvider> consumer, Item input, Item output) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, output)
                .pattern("#")
                .pattern("#")
                .pattern("S")
                .input('#', input)
                .input('S', Items.STICK)
                .criterion(hasItem(input), conditionsFromItem(input))
                .offerTo(consumer, getItemPath(output));
    }

    private void createPickaxe(Consumer<RecipeJsonProvider> consumer, Item input, Item output) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, output)
                .pattern("###")
                .pattern(" S ")
                .pattern(" S ")
                .input('#', input)
                .input('S', Items.STICK)
                .criterion(hasItem(input), conditionsFromItem(input))
                .offerTo(consumer, getItemPath(output));
    }

    private void createAxe(Consumer<RecipeJsonProvider> consumer, Item input, Item output) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, output)
                .pattern("##")
                .pattern("#S")
                .pattern(" S")
                .input('#', input)
                .input('S', Items.STICK)
                .criterion(hasItem(input), conditionsFromItem(input))
                .offerTo(consumer, getItemPath(output));
    }

    private void createShovel(Consumer<RecipeJsonProvider> consumer, Item input, Item output) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, output)
                .pattern("#")
                .pattern("S")
                .pattern("S")
                .input('#', input)
                .input('S', Items.STICK)
                .criterion(hasItem(input), conditionsFromItem(input))
                .offerTo(consumer, getItemPath(output));
    }

    private void createHoe(Consumer<RecipeJsonProvider> consumer, Item input, Item output) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, output)
                .pattern("##")
                .pattern(" S")
                .pattern(" S")
                .input('#', input)
                .input('S', Items.STICK)
                .criterion(hasItem(input), conditionsFromItem(input))
                .offerTo(consumer, getItemPath(output));
    }
}
