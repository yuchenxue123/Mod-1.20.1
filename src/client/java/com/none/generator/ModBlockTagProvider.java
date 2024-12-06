package com.none.generator;

import com.none.registry.ModBlocks;
import com.none.utils.tag.ModBlockTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

/**
 * @author yuchenxue
 * @date 2024/12/04 - 13:47
 */

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {

    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(ModBlockTags.NEEDS_OPAL_TOOL);

        // 需要稿子
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(ModBlocks.OPAL_ORE)
                .add(ModBlocks.OPAL_BLOCK);

        // 需要斧头
        getOrCreateTagBuilder(BlockTags.AXE_MINEABLE);
        // 需要铲子
        getOrCreateTagBuilder(BlockTags.SHOVEL_MINEABLE);
        // 需要锄头
        getOrCreateTagBuilder(BlockTags.HOE_MINEABLE);

        // 挖掘等级
        // 石制
        getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL);
        // 铁制
        getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.OPAL_ORE)
                .add(ModBlocks.OPAL_BLOCK);
        // 钻石
        getOrCreateTagBuilder(BlockTags.NEEDS_DIAMOND_TOOL);
        // 下届合金
        getOrCreateTagBuilder(TagKey.of(
                RegistryKeys.BLOCK,
                new Identifier("fabric", "needs_tool_level_4")
        ));
    }
}
