package net.branya.beerbrewery.block;

import net.branya.beerbrewery.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;

public class HopsBushBlock extends SweetBerryBushBlock {
    public HopsBushBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected ItemStack getCloneItemStack(LevelReader p_312054_, BlockPos p_57257_, BlockState p_57258_, boolean p_376908_) {
        return new ItemStack(ModItems.HOPS.get());
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        int age = state.getValue(AGE); // Get the age property of the block

        if (age == 3) { // Only allow harvesting at stage 3
            // Drop exactly 1 hop item
            popResource(level, pos, new ItemStack(ModItems.HOPS.get(), 1));

            // Play the sweet berry bush picking sound effect
            level.playSound(
                    null,
                    pos,
                    SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES,
                    SoundSource.BLOCKS,
                    1.0F,
                    0.8F + level.random.nextFloat() * 0.4F
            );

            // Reset the bush to stage 1 after harvesting
            BlockState newState = state.setValue(AGE, 1);
            level.setBlock(pos, newState, 2);

            // Trigger block change game event
            level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, newState));

            return InteractionResult.SUCCESS;
        } else {
            // If the bush isn't fully grown (AGE != 3), do nothing special
            return InteractionResult.PASS;
        }
    }

}