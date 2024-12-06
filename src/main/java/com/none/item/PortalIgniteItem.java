package com.none.item;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

/**
 * @author yuchenxue
 * @date 2024/12/03 - 21:21
 */

public class PortalIgniteItem extends Item {

    public PortalIgniteItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        PlayerEntity player = context.getPlayer();
        BlockPos blockPos = context.getBlockPos();
        BlockState blockState = world.getBlockState(blockPos);

        if (blockState.isOf(Blocks.DIAMOND_BLOCK)) {
            world.playSound(player, blockPos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, world.getRandom().nextFloat() * 0.4F + 0.8F);
            if (context.getSide() != Direction.UP) return ActionResult.FAIL;
            // 创建传送门
            world.setBlockState(blockPos, Blocks.QUARTZ_BLOCK.getDefaultState());
            BlockPos offsetPos = blockPos.offset(context.getSide());
            // 下面两层
            int[] integers = {-2, 2};
            for (int y = 0; y < 2; y++) {
                for (int i : integers) {
                    for (int j : integers) {
                        BlockPos add = offsetPos.add(i, y, j);
                        world.setBlockState(add, Blocks.QUARTZ_BLOCK.getDefaultState());
                    }
                }
            }
            // 上面一层
            BlockPos upPos = offsetPos.up(2);
            world.setBlockState(upPos, Blocks.QUARTZ_BLOCK.getDefaultState());
            for (Direction direction : Direction.values()) {
                if (direction == Direction.UP || direction == Direction.DOWN) continue;
                BlockPos surroundPos = upPos.offset(direction);
                world.setBlockState(surroundPos, Blocks.GLOWSTONE.getDefaultState());
            }
            for (int i : integers) {
                for (int j : integers) {
                    BlockPos add = upPos.add(i, 0, j);
                    world.setBlockState(add, Blocks.GLOWSTONE.getDefaultState());
                }
            }
            // other
            world.emitGameEvent(player, GameEvent.BLOCK_PLACE, blockPos);
            ItemStack itemStack = context.getStack();
            if (player instanceof ServerPlayerEntity) {
                Criteria.PLACED_BLOCK.trigger((ServerPlayerEntity)player, offsetPos, itemStack);
                itemStack.damage(1, player, (p) -> {
                    p.sendToolBreakStatus(context.getHand());
                });
            }
            return ActionResult.success(world.isClient());
        } else {
            // 点燃方块
            if (!CampfireBlock.canBeLit(blockState) && !CandleBlock.canBeLit(blockState) && !CandleCakeBlock.canBeLit(blockState)) {
                BlockPos offsetPos = blockPos.offset(context.getSide());
                if (AbstractFireBlock.canPlaceAt(world, offsetPos, context.getHorizontalPlayerFacing())) {
                    world.playSound(player, offsetPos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, world.getRandom().nextFloat() * 0.4F + 0.8F);

                    BlockState offsetState = AbstractFireBlock.getState(world, offsetPos);
                    world.setBlockState(offsetPos, offsetState, 11);
                    world.emitGameEvent(player, GameEvent.BLOCK_PLACE, blockPos);
                    ItemStack itemStack = context.getStack();
                    if (player instanceof ServerPlayerEntity) {
                        Criteria.PLACED_BLOCK.trigger((ServerPlayerEntity)player, offsetPos, itemStack);
                        itemStack.damage(1, player, (p) -> {
                            p.sendToolBreakStatus(context.getHand());
                        });
                    }

                    return ActionResult.success(world.isClient());
                } else {
                    return ActionResult.FAIL;
                }
            } else {
                world.playSound(player, blockPos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, world.getRandom().nextFloat() * 0.4F + 0.8F);
                world.setBlockState(blockPos, (BlockState)blockState.with(Properties.LIT, true), 11);
                world.emitGameEvent(player, GameEvent.BLOCK_CHANGE, blockPos);
                if (player != null) {
                    context.getStack().damage(1, player, (p) -> {
                        p.sendToolBreakStatus(context.getHand());
                    });
                }

                return ActionResult.success(world.isClient());
            }
        }
    }
}
