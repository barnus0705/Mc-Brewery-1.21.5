package net.branya.beerbrewery.block;

import net.branya.beerbrewery.BeerBrewery;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {
    // DeferredRegister for blocks
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, BeerBrewery.MOD_ID);

    // DeferredRegister for items
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, BeerBrewery.MOD_ID);

    // Register the hops plant block
    public static final RegistryObject<Block> HOPS_PLANT = BLOCKS.register("hops_plant",
            () -> new HopsPlantBlock(
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.PLANT) // The color this block will have on maps and foliage
                            .noCollission()
                            .randomTicks()
                            .instabreak()
                            .sound(SoundType.CROP)
                            .pushReaction(PushReaction.DESTROY)
                            .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(BeerBrewery.MOD_ID, "hops_plant")))
            ));

    // Register the corresponding block item for the hops plant block
    public static final RegistryObject<Item> HOPS_PLANT_ITEM = ITEMS.register("hops_plant",
            () -> new BlockItem(HOPS_PLANT.get(), new Item.Properties().setId(ITEMS.key("hops_plant"))));

    // Method to bind the block/item registries to the event bus
    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus); // Register blocks
        ITEMS.register(eventBus);// Register items
    }
}