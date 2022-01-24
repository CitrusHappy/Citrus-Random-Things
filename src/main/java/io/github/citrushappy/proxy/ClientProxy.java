package io.github.citrushappy.proxy;

import io.github.citrushappy.entity.ModEntities;
import io.github.citrushappy.items.ModItems;
import io.github.citrushappy.util.Reference;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientProxy extends CommonProxy
{
    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);

        //OBJLoader.INSTANCE.addDomain(Reference.MOD_ID);
        //ModelLoaderRegistry.registerLoader(new BakedModelLoader()); <-- blocks models

        ModEntities.initModels();
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
    }

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        //ModBlocks.initModels();
        ModItems.initModels();
    }

}
