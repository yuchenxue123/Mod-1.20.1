package com.none.utils.tag;

import com.none.MinersHeaven;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

/**
 * @author yuchenxue
 * @date 2024/12/04 - 18:14
 */

public class ModBlockTags {
    public static final TagKey<Block> NEEDS_OPAL_TOOL = register("needs_opal_tool");

    public static TagKey<Block> register(String name) {
        Identifier id = Identifier.of(MinersHeaven.MOD_ID, name);
        return TagKey.of(RegistryKeys.BLOCK, id);
    }
}
