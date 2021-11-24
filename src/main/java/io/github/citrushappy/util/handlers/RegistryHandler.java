package io.github.citrushappy.util.handlers;

import io.github.citrushappy.init.EntityInit;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class RegistryHandler
{
    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent event)
    {
        RenderHandler.registerEntityRenders();
    }

    public static void preInitRegistries(FMLPreInitializationEvent event)
    {
        EntityInit.registerEntities();
    }

    public static void initRegistries()
    {
        SoundsHandler.registerSounds();
    }
}
