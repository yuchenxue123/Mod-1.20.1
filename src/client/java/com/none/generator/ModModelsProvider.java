package com.none.generator;

import com.none.registry.ModBlocks;
import com.none.registry.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

/**
 * @author yuchenxue
 * @date 2024/12/04 - 13:54
 */

public class ModModelsProvider extends FabricModelProvider {
    public ModModelsProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.OPAL_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.OPAL_BLOCK);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        // 普通物品
        itemModelGenerator.register(ModItems.OPAL, Models.GENERATED);
        itemModelGenerator.register(ModItems.PORTAL_IGNITE, Models.GENERATED);

        // 工具类
        itemModelGenerator.register(ModItems.OPAL_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.OPAL_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.OPAL_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.OPAL_HOE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.OPAL_SHOVEL, Models.HANDHELD);
    }
}
