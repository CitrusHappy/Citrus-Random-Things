package io.github.citrushappy;


import io.github.citrushappy.client.renderer.entity.RendererCrewMate;
import io.github.citrushappy.entity.EntityCrewMate;
import io.github.citrushappy.util.handlers.RegistryHandler;
import io.github.citrushappy.util.handlers.RenderHandler;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import software.bernie.example.registry.EntityRegistry;
import software.bernie.geckolib3.GeckoLib;


@Mod(modid = MoreModels.MODID, name = MoreModels.NAME, version = MoreModels.VERSION)
public class MoreModels
{
    @Mod.Instance
    public static MoreModels instance;

    public static final String MODID = "moremodels";
    public static final String NAME = "More Models";
    public static final String VERSION = "0.1";

    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent event)
    {
        RenderHandler.registerEntityRenders();
    }


    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        RegistryHandler.preInitRegistries(event);
        GeckoLib.initialize();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        //
    }
}
