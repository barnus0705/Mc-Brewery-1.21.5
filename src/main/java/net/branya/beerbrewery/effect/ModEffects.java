package net.branya.beerbrewery.effect;

import net.branya.beerbrewery.BeerBrewery;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECT =
            DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, BeerBrewery.MOD_ID);

    public static final RegistryObject<MobEffect> FOOD_EFFECT = MOB_EFFECT.register("food_effect",
            () -> new FoodEffect(MobEffectCategory.BENEFICIAL, 0xF9A602));

    public static void register(IEventBus eventBus) {
        MOB_EFFECT.register(eventBus);
    }
}
