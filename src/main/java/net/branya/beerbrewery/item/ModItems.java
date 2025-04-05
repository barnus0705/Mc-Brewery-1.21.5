package net.branya.beerbrewery.item;

import net.branya.beerbrewery.BeerBrewery;
import net.branya.beerbrewery.block.mechanic.HopsBushUsage;
import net.branya.beerbrewery.block.mechanic.HopsItemUsage;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, BeerBrewery.MOD_ID);

    public static final RegistryObject<Item> HOPS = ITEMS.register("hops",
            () -> new HopsBushUsage(new Item.Properties().food(ModFoodProperties.HOPS).setId(ITEMS.key("hops"))));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
