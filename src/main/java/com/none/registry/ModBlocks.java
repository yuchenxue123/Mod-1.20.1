package com.none.registry;

import com.none.MinersHeaven;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;

/**
 * @author yuchenxue
 * @date 2024/12/03 - 16:18
 */

public class ModBlocks {

    public static Block OPAL_ORE = register(
            "opal_ore",
            new ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.STONE)
                    .strength(2.5F, 6.0F), UniformIntProvider.create(2, 5)),
            ModItemGroups.MINERS_KEY
    );

    public static Block OPAL_BLOCK = register(
            "opal_block",
            new Block(FabricBlockSettings.copyOf(Blocks.STONE)
                    .strength(4.0F, 6.0F)),
            ModItemGroups.MINERS_KEY
    );

    @SafeVarargs
    public static Block register(String name, Block block, boolean shouldRegisterItem, RegistryKey<ItemGroup>... groups) {
        // 是否注册方块物品
        if (shouldRegisterItem) {
            ModItems.register(name, new BlockItem(block, new Item.Settings()), groups);
        }

        Identifier id = Identifier.of(MinersHeaven.MOD_ID, name);
        return  Registry.register(Registries.BLOCK, id, block);
    }

    @SafeVarargs
    public static Block register(String name, Block block, RegistryKey<ItemGroup>... groups) {
        return register(name, block, true, groups);
    }

    public static void initialize() {
        MinersHeaven.LOGGER.info("Registering Mod Blocks");
    }
}
