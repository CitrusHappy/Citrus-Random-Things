package io.github.citrushappy.blocks;

import io.github.citrushappy.MoreModels;
import io.github.citrushappy.init.ItemInit;
import io.github.citrushappy.util.Reference;
import net.minecraft.block.BlockButton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockEmergencyButton extends BlockButton {


    public BlockEmergencyButton(String name) {
        super(false);
        this.setRegistryName(new ResourceLocation(Reference.MOD_ID));

        //setCreativeTab(MoreModels.creativeTab);
        //BlockInit.BLOCKS.add(this);
        //ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(getRegistryName()));
    }


    @Override
    protected void playClickSound(@Nullable EntityPlayer player, World worldIn, BlockPos pos) {

    }

    @Override
    protected void playReleaseSound(World worldIn, BlockPos pos) {

    }
}
