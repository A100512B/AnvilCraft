package dev.dubhe.anvilcraft.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class MeltGemCauldron extends AbstractCauldronBlock {

    public MeltGemCauldron(Properties properties) {
        super(properties, CauldronInteraction.EMPTY);
    }

    @Override
    protected double getContentHeight(BlockState state) {
        return 0.9375;
    }

    @Override
    public boolean isFull(BlockState state) {
        return true;
    }

    @Override
    public int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos) {
        return 3;
    }

    @Override
    public @NotNull ItemStack getCloneItemStack(
            @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull BlockState state
    ) {
        return new ItemStack(Items.CAULDRON);
    }
}
