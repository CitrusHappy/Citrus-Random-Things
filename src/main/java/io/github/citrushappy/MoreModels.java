package io.github.citrushappy;

import io.github.citrushappy.util.Reference;
import io.github.citrushappy.util.handlers.RegistryHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import software.bernie.geckolib3.GeckoLib;


@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
public class MoreModels
{
    @Mod.Instance
    public static MoreModels instance;

    //public static final CreativeTabs itemsblockstab = new CreaturesTab("itemsblockstabcreatures");

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        RegistryHandler.preInitRegistries(event);
        GeckoLib.initialize();
    }


    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        RegistryHandler.initRegistries();
    }

    @Mod.EventHandler
    public static void PostInit(FMLPostInitializationEvent event){

    }
}
