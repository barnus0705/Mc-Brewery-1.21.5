package net.branya.beerbrewery.effect;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import javax.annotation.Nullable;

public class FoodEffect extends MobEffect {

    public FoodEffect(MobEffectCategory pCategory, int pColor) {
        // Call the parent class constructor (MobEffect)
        // Beneficial effect, with a color code (visual representation for the potion)
        super(pCategory, pColor);
    }

    @Override
    public void applyInstantenousEffect(ServerLevel pLevel, @Nullable Entity pSource, @Nullable Entity pIndirectSource, LivingEntity pEntity, int pAmplifier, double pEffectMultiplier) {
        // Ensure the entity is a Player
        if (pEntity instanceof Player player) {
            // Restore 8 food points to the player
            player.getFoodData().eat(8, 0.8F);

            // Apply nausea effect to the player (200 ticks duration, amplifier 0)
            if (!player.hasEffect(MobEffects.NAUSEA)) {
                player.addEffect(new MobEffectInstance(
                        MobEffects.NAUSEA,
                        200, // Effect duration in ticks
                                    0    // Effect amplifier
                ));
            }
        }
    }

    @Override
    public boolean isInstantenous() {
        return true; // This effect should act instantaneously
    }
}