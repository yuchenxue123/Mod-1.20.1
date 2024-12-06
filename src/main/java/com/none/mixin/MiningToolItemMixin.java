package com.none.mixin;

import com.none.utils.ModMiningLevels;
import com.none.utils.tag.ModBlockTags;
import net.minecraft.block.BlockState;
import net.minecraft.item.MiningToolItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * @author yuchenxue
 * @date 2024/12/04 - 18:04
 */

@Mixin(MiningToolItem.class)
public abstract class MiningToolItemMixin extends ToolItemMixin {

    /**
     * @author yuchenxue
     * @reason 添加 ModMaterial
     */
    @Inject(method = "isSuitableFor", at = @At(value = "HEAD"), cancellable = true)
    public void isSuitableFor(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        int i = this.getToolMaterial().getMiningLevel();
        if (i < ModMiningLevels.OPAL && state.isIn(ModBlockTags.NEEDS_OPAL_TOOL)) {
            cir.setReturnValue(false);
        }
    }
}
