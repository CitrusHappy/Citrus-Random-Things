package io.github.citrushappy.proxy;

import io.github.citrushappy.client.renderer.item.RendererTattletail;
import io.github.citrushappy.entity.EntityList;
import io.github.citrushappy.init.Items;
import io.github.citrushappy.util.Reference;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
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

        EntityList.initModels();
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
        ModelLoader.setCustomModelResourceLocation(Items.TATTLETAIL, 0, new ModelResourceLocation(Reference.MOD_ID + ":tattletail", "inventory"));
        ModelLoader.setCustomModelResourceLocation(Items.KNIFE, 0, new ModelResourceLocation(Reference.MOD_ID + ":knife", "inventory"));
        ModelLoader.setCustomModelResourceLocation(Items.DRIP, 0, new ModelResourceLocation(Reference.MOD_ID + ":drip", "inventory"));
        ModelLoader.setCustomModelResourceLocation(Items.BOBUX, 0, new ModelResourceLocation(Reference.MOD_ID + ":bobux", "inventory"));

        Items.TATTLETAIL.setTileEntityItemStackRenderer(new RendererTattletail());
    }
}
