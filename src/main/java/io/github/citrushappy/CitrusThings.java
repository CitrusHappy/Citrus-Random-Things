package io.github.citrushappy;

import io.github.citrushappy.proxy.CommonProxy;
import io.github.citrushappy.util.Reference;
import io.github.citrushappy.util.registry.ItemRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;
import software.bernie.geckolib3.GeckoLib;


@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
public class CitrusThings
{
    @SidedProxy(clientSide = Reference.CLIENT, serverSide = Reference.COMMON)
    public static CommonProxy proxy;

    @Mod.Instance
    public static CitrusThings instance;

    public static Logger logger;
    public static CreativeTabs citrusthingsItemGroup;
    //public static final CreativeTabs itemsblockstab = new CreaturesTab("itemsblockstabcreatures");



    //creative tab
    public static CreativeTabs getCitrusthingsItemGroup()
    {
        if (citrusthingsItemGroup == null)
        {
            citrusthingsItemGroup = new CreativeTabs(CreativeTabs.getNextID(), "Citrusthings")
            {
                @Override
                public ItemStack createIcon() {
                    return new ItemStack(ItemRegistry.KNIFE);
                }
            };
        }

        return citrusthingsItemGroup;
    }






    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();

        proxy.preInit(event);

        GeckoLib.initialize();
    }


    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init(event);
    }

    @Mod.EventHandler
    public static void PostInit(FMLPostInitializationEvent event)
    {
        proxy.postInit(event);
    }
}
