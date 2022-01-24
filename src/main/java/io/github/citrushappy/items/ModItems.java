package io.github.citrushappy.items;

import net.minecraft.item.ItemSword;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModItems
{
    @GameRegistry.ObjectHolder("citrusthings:knife")
    public static ItemKnife knife;


    @SideOnly(Side.CLIENT)
    public static void initModels()
    {
        knife.initModel();
    }
}
