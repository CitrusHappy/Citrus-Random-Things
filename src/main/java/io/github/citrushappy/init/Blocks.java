package io.github.citrushappy.init;

import com.google.common.collect.Sets;
import net.minecraft.block.*;
import net.minecraft.init.Bootstrap;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;
import java.util.Set;

public class Blocks {

    private static final Set<Block> CACHE;
    public static final Block EMERGENCY_BUTTON;
    public static final Block TATTLETAIL;


    @Nullable
    private static Block getRegisteredBlock(String blockName)
    {
        Block block = Block.REGISTRY.getObject(new ResourceLocation(blockName));

        if (!CACHE.add(block))
        {
            throw new IllegalStateException("Invalid Block requested: " + blockName);
        }
        else
        {
            return block;
        }
    }

    static
    {
        if (!Bootstrap.isRegistered())
        {
            throw new RuntimeException("Accessed Blocks before Bootstrap!");
        }
        else
        {
            CACHE = Sets.<Block>newHashSet();
            EMERGENCY_BUTTON = getRegisteredBlock("emergency_button");
            TATTLETAIL = getRegisteredBlock("tattletail");
            CACHE.clear();
        }
    }

}
