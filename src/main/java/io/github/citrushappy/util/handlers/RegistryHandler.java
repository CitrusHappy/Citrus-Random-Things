package io.github.citrushappy.util.handlers;

import io.github.citrushappy.init.EntityInit;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class RegistryHandler
{
    public static void preInitRegistries(FMLPreInitializationEvent event)
    {
        EntityInit.registerEntities();
        //RenderHandler.registerEntityRenders();
        //SoundsHandler.registerSounds();
    }
}
