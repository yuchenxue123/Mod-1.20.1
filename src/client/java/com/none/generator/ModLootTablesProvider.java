package com.none.generator;

import com.none.registry.ModBlocks;
import com.none.registry.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;

/**
 * @author yuchenxue
 * @date 2024/12/04 - 13:55
 */

public class ModLootTablesProvider extends FabricBlockLootTableProvider {

    public ModLootTablesProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.OPAL_ORE, ModItems.OPAL);
        addDrop(ModBlocks.OPAL_BLOCK);
    }
}
