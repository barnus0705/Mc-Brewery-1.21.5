package net.branya.beerbrewery.potion;

import net.branya.beerbrewery.BeerBrewery;
import net.branya.beerbrewery.effect.ModEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModPotions {

    // Deferred Register for Minecraft Potions, bound to the Mod ID
    public static final DeferredRegister<Potion> POTIONS =
            DeferredRegister.create(ForgeRegistries.POTIONS, BeerBrewery.MOD_ID);

    // Registry Object for a custom "Beer" potion
    public static final RegistryObject<Potion> BEER = POTIONS.register("beer",
            () -> new Potion("beer", new MobEffectInstance(ModEffects.FOOD_EFFECT.getHolder().get(), 200, 0)));
    public static final RegistryObject<Potion> RADLER = POTIONS.register("radler",
            () -> new Potion("radler", new MobEffectInstance(ModEffects.RADLER_EFFCET.getHolder().get(), 200, 0)));

    public static void register(IEventBus eventBus) {
        POTIONS.register(eventBus);
    }
}