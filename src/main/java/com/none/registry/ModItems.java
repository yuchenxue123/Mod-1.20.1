package com.none.registry;

import com.none.MinersHeaven;
import com.none.item.PortalIgniteItem;
import com.none.utils.material.ModToolMaterial;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

/**
 * @author yuchenxue
 * @date 2024/12/03 - 15:39
 */

public class ModItems {

    public static final Item OPAL = register(
            "opal",
            new Item(new FabricItemSettings()),
            ModItemGroups.MINERS_KEY
    );

    public static final Item PORTAL_IGNITE = register(
            "portal_ignite",
            new PortalIgniteItem(new FabricItemSettings().maxDamage(128)),
            ModItemGroups.MINERS_KEY
    );

    public static final Item OPAL_SWORD = register(
            "opal_sword",
            new SwordItem(ModToolMaterial.OPAL, 7, -2f, new FabricItemSettings()),
            ModItemGroups.MINERS_KEY
    );
    public static final Item OPAL_AXE = register(
            "opal_axe",
            new AxeItem(ModToolMaterial.OPAL, 5, -1f, new FabricItemSettings()),
            ModItemGroups.MINERS_KEY
    );
    public static final Item OPAL_PICKAXE = register(
            "opal_pickaxe",
            new PickaxeItem(ModToolMaterial.OPAL, 2, -1f, new FabricItemSettings()),
            ModItemGroups.MINERS_KEY
    );
    public static final Item OPAL_HOE = register(
            "opal_hoe",
            new HoeItem(ModToolMaterial.OPAL, 0, -1f, new FabricItemSettings()),
            ModItemGroups.MINERS_KEY
    );
    public static final Item OPAL_SHOVEL = register(
            "opal_shovel",
            new ShovelItem(ModToolMaterial.OPAL, 1, -1f, new FabricItemSettings()),
            ModItemGroups.MINERS_KEY
    );

    @SafeVarargs
    public static Item register(String name, Item item, RegistryKey<ItemGroup>... groups) {
        Identifier id = Identifier.of(MinersHeaven.MOD_ID, name);
        Item registerItem = Registry.register(Registries.ITEM, id, item);

        for (RegistryKey<ItemGroup> group : groups) {
            ItemGroupEvents.modifyEntriesEvent(group)
                    .register((itemGroup) -> itemGroup.add(registerItem));
        }

        return registerItem;
    }

    public static void initialize() {
        MinersHeaven.LOGGER.info("Registering Mod Items");
    }
}
