package com.none.registry;

import com.none.MinersHeaven;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

/**
 * @author yuchenxue
 * @date 2024/12/03 - 16:04
 */

public class ModItemGroups {

    // Miners Group
    public static final RegistryKey<ItemGroup> MINERS_KEY = getKey("miners");
    public static final ItemGroup MINERS_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModItems.OPAL))
            .displayName(Text.translatable("itemGroup.miners"))
            .build();

    public static RegistryKey<ItemGroup> getKey(String name) {
        Identifier id = Identifier.of(MinersHeaven.MOD_ID, name);
       return RegistryKey.of(Registries.ITEM_GROUP.getKey(), id);
    }

    public static void register(RegistryKey<ItemGroup> key, ItemGroup group) {
        Registry.register(Registries.ITEM_GROUP, key, group);
    }

    public static void initialize() {
        MinersHeaven.LOGGER.info("Registering Mod ItemGroup");

        register(MINERS_KEY, MINERS_GROUP);
    }
}
