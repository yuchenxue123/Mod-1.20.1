package com.none;

import com.none.registry.ModBlocks;
import com.none.registry.ModItemGroups;
import com.none.registry.ModItems;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MinersHeaven implements ModInitializer {
	public static final String MOD_ID = "miners-heaven";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing MinersHeaven!!");

		// 因为物品在加载时会添加到物品栏分类，所以这里先初始化物品栏分类
		ModItemGroups.initialize();

		ModItems.initialize();
		ModBlocks.initialize();
	}
}