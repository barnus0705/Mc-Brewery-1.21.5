package net.branya.beerbrewery.effect;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import javax.annotation.Nullable;

public class RadlerEffect extends FoodEffect{
    public RadlerEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

    @Override
    public void applyInstantenousEffect(ServerLevel pLevel, @Nullable Entity pSource, @Nullable Entity pIndirectSource, LivingEntity pEntity, int pAmplifier, double pEffectMultiplier) {
        // Ensure the entity is a Player
        if (pEntity instanceof Player player) {
            // Restore 8 food points to the player
            player.getFoodData().eat(8, 0.8F);

            // Apply nausea effect to the player (200 ticks duration, amplifier 0)
            // Apply nausea and speed effects if not already applied
            if (!player.hasEffect(MobEffects.NAUSEA)) {
                player.addEffect(new MobEffectInstance(MobEffects.NAUSEA, 200, 0));
            }

            if (!player.hasEffect(MobEffects.SPEED)) {
                player.addEffect(new MobEffectInstance(MobEffects.SPEED, 200, 0));
            }
        }
    }
}
