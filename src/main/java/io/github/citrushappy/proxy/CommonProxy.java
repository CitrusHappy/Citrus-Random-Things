package io.github.citrushappy.proxy;

import io.github.citrushappy.entity.ModEntities;
import io.github.citrushappy.items.ItemKnife;
import io.github.citrushappy.util.handlers.SoundsHandler;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class CommonProxy
{
    public void preInit(FMLPreInitializationEvent event)
    {
        ModEntities.init();
    }

    public void init(FMLInitializationEvent event)
    {
        SoundsHandler.registerSounds();
    }

    public void postInit(FMLPostInitializationEvent event)
    {

    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event)
    {
        event.getRegistry().register(new ItemKnife());
    }
}
