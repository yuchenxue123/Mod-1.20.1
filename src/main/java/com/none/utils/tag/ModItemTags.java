package com.none.utils.tag;

import com.none.MinersHeaven;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

/**
 * @author yuchenxue
 * @date 2024/12/04 - 18:15
 */

public class ModItemTags {

    public static TagKey<Item> register(String name) {
        Identifier id = Identifier.of(MinersHeaven.MOD_ID, name);
        return TagKey.of(RegistryKeys.ITEM, id);
    }
}
