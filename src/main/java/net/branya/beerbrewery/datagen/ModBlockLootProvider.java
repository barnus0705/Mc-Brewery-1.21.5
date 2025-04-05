package net.branya.beerbrewery.datagen;

import net.branya.beerbrewery.block.HopsBushBlock;
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
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
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

        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);

        this.add(ModBlocks.HOPS_BUSH.get(), block -> this.applyExplosionDecay(
                block, LootTable.lootTable().withPool(LootPool.lootPool()
                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.HOPS_BUSH.get())
                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(HopsBushBlock.AGE, 3)))
                        .add(LootItem.lootTableItem(ModItems.HOPS.get()))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F))))));

    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }

    protected LootTable.Builder createCropDrops(Block block, Item item, LootItemCondition.Builder builder) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.applyExplosionDecay(block, LootTable.lootTable()
                        .withPool(LootPool.lootPool().add(LootItem.lootTableItem(item)))).withPool(
                                LootPool.lootPool().when(builder).add(LootItem.lootTableItem(item).apply(ApplyBonusCount
                                                .addBonusBinomialDistributionCount(registrylookup
                                                .getOrThrow(Enchantments.FORTUNE), 0.5714286F, 3))
                                        )
        );
    }
}
