package net.branya.beerbrewery.item;

import net.minecraft.world.food.FoodProperties;

public class ModFoodProperties {
    public static final FoodProperties HOPS = new FoodProperties.Builder().nutrition(2).saturationModifier(0.1f).build();
    public static final FoodProperties BEER = new FoodProperties.Builder()
            .nutrition(8)
            .saturationModifier(0.1f)
            .build();
}
