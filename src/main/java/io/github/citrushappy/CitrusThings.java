package io.github.citrushappy;

import io.github.citrushappy.util.Reference;
import io.github.citrushappy.util.handlers.RegistryHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;
import software.bernie.geckolib3.GeckoLib;


@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
public class CitrusThings
{
    @Mod.Instance
    public static CitrusThings instance;

    public static Logger logger;

    //public static final CreativeTabs itemsblockstab = new CreaturesTab("itemsblockstabcreatures");

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        RegistryHandler.preInitRegistries(event);
        GeckoLib.initialize();
    }


    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        RegistryHandler.initRegistries();
    }

    @Mod.EventHandler
    public static void PostInit(FMLPostInitializationEvent event){

    }
}
