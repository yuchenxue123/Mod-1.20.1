package com.none.mixin;

import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterial;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

/**
 * @author yuchenxue
 * @date 2024/12/04 - 18:12
 */

@Mixin(ToolItem.class)
public abstract class ToolItemMixin {
    @Unique
    abstract ToolMaterial getToolMaterial();
}
