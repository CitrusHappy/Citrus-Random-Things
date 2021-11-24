package io.github.citrushappy.init;

import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BlockInit {

    public static Block emergencyButton;

    public static void registerBlocks()
    {
        //registerBlock(new );
    }

    @SubscribeEvent
    public static void registerBlock(RegistryEvent.Register<Block> event) {
        //event.getRegistry().registerAll(BlockInit.BLOCKS.toArray(new Block[0]));
    }


}
