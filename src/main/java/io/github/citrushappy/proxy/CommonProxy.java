package io.github.citrushappy.proxy;

import io.github.citrushappy.CitrusThings;
import io.github.citrushappy.blocks.BlockEmergencyButton;
import io.github.citrushappy.blocks.BlockTattletail;
import io.github.citrushappy.entity.EntityList;
import io.github.citrushappy.items.ItemBobux;
import io.github.citrushappy.items.ItemDrip;
import io.github.citrushappy.items.ItemKnife;
import io.github.citrushappy.items.ItemTattletail;
import io.github.citrushappy.init.Items;
import io.github.citrushappy.util.Reference;
import io.github.citrushappy.init.SoundsHandler;
import net.minecraft.block.Block;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

import java.sql.Ref;

import static io.github.citrushappy.CitrusThings.getCitrusthingsItemGroup;

@Mod.EventBusSubscriber
public class CommonProxy
{
    private static IForgeRegistry<Item> itemRegistry;
    public static PotionType DRIP_POTION;
    private static BlockEmergencyButton EMERGENCY_BUTTON;

    public void preInit(FMLPreInitializationEvent event)
    {
        EntityList.init();
    }

    public void init(FMLInitializationEvent event)
    {
        DRIP_POTION = new PotionType("swiftness", new PotionEffect(MobEffects.SPEED, 10, 1));
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
        Items.KNIFE = registerItem(new ItemKnife(Item.ToolMaterial.IRON), "knife");
        Items.BOBUX = registerItem(new ItemBobux(), "bobux");
        Items.TATTLETAIL = registerItem(new ItemTattletail(), "tattletail");
        Items.DRIP = registerItem(new ItemDrip(ItemArmor.ArmorMaterial.IRON, 0, EntityEquipmentSlot.FEET), "drip");


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
