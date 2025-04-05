package net.branya.beerbrewery.block.mechanic;

import net.branya.beerbrewery.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class HopsBushUsage extends Item {
    public HopsBushUsage(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Player player = context.getPlayer();

        // Get the block below where the player clicked
        Block blockBelow = level.getBlockState(pos).getBlock();

        // Check if the block below is grass block or dirt
        if (blockBelow == Blocks.GRASS_BLOCK || blockBelow == Blocks.DIRT) {
            BlockPos plantPos = pos.above(); // Position above the grass or dirt
            BlockState hopsBushState = ModBlocks.HOPS_BUSH.get().defaultBlockState(); // Use the custom "HopsBushBlock"

            // Ensure the position above is air before planting
            if (level.getBlockState(plantPos).isAir()) {
                level.setBlock(plantPos, hopsBushState, 3); // Place the HopsBushBlock

                // Consume one hops item from the player's hand if the player is not in creative
                if (!player.isCreative()) {
                    context.getItemInHand().shrink(1);
                }

                return InteractionResult.SUCCESS;
            }
        }

        return InteractionResult.FAIL;
    }
}