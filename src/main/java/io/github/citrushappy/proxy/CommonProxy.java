package io.github.citrushappy.proxy;

import io.github.citrushappy.CitrusThings;
import io.github.citrushappy.entity.ModEntities;
import io.github.citrushappy.items.ItemDrip;
import io.github.citrushappy.items.ItemKnife;
import io.github.citrushappy.items.ItemTattletail;
import io.github.citrushappy.util.registry.ItemRegistry;
import io.github.citrushappy.util.Reference;
import io.github.citrushappy.util.handlers.SoundsHandler;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import static io.github.citrushappy.CitrusThings.getCitrusthingsItemGroup;

@Mod.EventBusSubscriber
public class CommonProxy
{
    private static IForgeRegistry<Item> itemRegistry;

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
    public static void onRegisterItems(RegistryEvent.Register<Item> event)
    {
        itemRegistry = event.getRegistry();

        //registers the item and sets the inventory sprite/model
        ItemRegistry.KNIFE = registerItem(new ItemKnife(Item.ToolMaterial.IRON), "knife");
        ItemRegistry.TATTLETAIL = registerItem(new ItemTattletail(), "tattletail");
        ItemRegistry.DRIP = registerItem(new ItemDrip(ItemArmor.ArmorMaterial.IRON, 0, EntityEquipmentSlot.FEET), "drip");
    }

    public static <T extends Item> T registerItem(T item, String name)
    {
        item.setCreativeTab(getCitrusthingsItemGroup());
        registerItem(item, new ResourceLocation(Reference.MOD_ID, name));
        return item;
    }

    public static <T extends Item> T registerItem(T item, ResourceLocation name)
    {
        itemRegistry.register(item.setRegistryName(name).setTranslationKey(name.toString().replace(":", ".")));
        return item;
    }
}
