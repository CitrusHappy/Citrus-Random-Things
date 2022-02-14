package io.github.citrushappy.client.gui;

import io.github.citrushappy.items.ItemTattletail;
import io.github.citrushappy.util.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(modid = Reference.MOD_ID, value = Side.CLIENT)
public class GuiHandler {

    private GuiHandler()
    {

    }

    private static GuiTattletail GUI_TATTLETAIL = new GuiTattletail();

    @SubscribeEvent
    public static void onRenderGameOverlayEventPost(RenderGameOverlayEvent.Post event)
    {
        EntityPlayer player = Minecraft.getMinecraft().player;

        if (event.getType() == RenderGameOverlayEvent.ElementType.ALL)
        {
            ItemStack mainhand = player.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND);
            ItemStack offhand = player.getItemStackFromSlot(EntityEquipmentSlot.OFFHAND);

            if(offhand.getItem() instanceof ItemTattletail || mainhand.getItem() instanceof ItemTattletail)
            {
                GuiHandler.GUI_TATTLETAIL.render();
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onRenderGameOverlayEventPre(RenderGameOverlayEvent.Pre event)
    {
        EntityPlayer player = Minecraft.getMinecraft().player;
    }
}
