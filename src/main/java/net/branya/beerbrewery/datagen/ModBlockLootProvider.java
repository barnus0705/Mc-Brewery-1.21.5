package net.branya.beerbrewery.datagen;

import net.branya.beerbrewery.block.HopsPlantBlock;
import net.branya.beerbrewery.block.ModBlocks;
import net.branya.beerbrewery.item.ModItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootProvider extends BlockLootSubProvider {

    protected ModBlockLootProvider(HolderLookup.Provider pRegistries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), pRegistries);
    }

    @Override
    protected void generate() {
        // dropSelf(ModBlocks.HOPS_PLANT.get());
        //...

        LootItemCondition.Builder lootItemConditionBuilder = LootItemBlockStatePropertyCondition
                .hasBlockStateProperties(ModBlocks.HOPS_PLANT.get())
                .setProperties(StatePropertiesPredicate.Builder.properties()
                .hasProperty(HopsPlantBlock.AGE, 6));

        this.add(ModBlocks.HOPS_PLANT.get(),
                this.createCropDrops(ModBlocks.HOPS_PLANT.get(), ModItems.HOPS.get(),lootItemConditionBuilder));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }

    protected LootTable.Builder createCropDrops(Block p_249457_, Item p_248599_, LootItemCondition.Builder p_252202_) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.applyExplosionDecay(p_249457_, LootTable.lootTable()
                        .withPool(LootPool.lootPool().add(LootItem.lootTableItem(p_248599_)))).withPool(
                                LootPool.lootPool().when(p_252202_).add(LootItem.lootTableItem(p_248599_).apply(ApplyBonusCount
                                                .addBonusBinomialDistributionCount(registrylookup
                                                .getOrThrow(Enchantments.FORTUNE), 0.5714286F, 3))
                                        )
        );
    }
}
