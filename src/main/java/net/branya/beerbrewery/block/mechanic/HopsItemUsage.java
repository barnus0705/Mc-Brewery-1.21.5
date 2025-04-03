package net.branya.beerbrewery.block.mechanic;

import net.branya.beerbrewery.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class HopsItemUsage extends Item {
    public HopsItemUsage(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Player player = context.getPlayer();
        InteractionHand hand = context.getHand();

        // Get the block below where the player clicked
        Block blockBelow = level.getBlockState(pos).getBlock();

        // Check if the block below is farmland
        if (blockBelow == net.minecraft.world.level.block.Blocks.FARMLAND) {
            BlockPos plantPos = pos.above(); // Position above the farmland
            BlockState hopsPlantState = ModBlocks.HOPS_PLANT.get().defaultBlockState();

            // Ensure the position is air before planting
            if (level.getBlockState(plantPos).isAir()) {
                level.setBlock(plantPos, hopsPlantState, 3);

                // Consume one hops item from the player's hand
                if (!player.isCreative()) {
                    context.getItemInHand().shrink(1);
                }

                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.FAIL;
    }
}
