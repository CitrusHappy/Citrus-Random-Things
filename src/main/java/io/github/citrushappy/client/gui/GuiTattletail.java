package io.github.citrushappy.client.gui;


import io.github.citrushappy.items.ItemTattletail;
import io.github.citrushappy.util.CitrusThingsConfig;
import io.github.citrushappy.util.GuiHelper;
import io.github.citrushappy.util.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL14;

@SideOnly(Side.CLIENT)
public class GuiTattletail extends Gui {

    private static final ResourceLocation BATTERY_BAR = new ResourceLocation(Reference.MOD_ID, "textures/gui/tattletail/battery_bar.png");
    private static final ResourceLocation FEED_BAR = new ResourceLocation(Reference.MOD_ID, "textures/gui/tattletail/feed_bar.png");
    private static final ResourceLocation GROOM_BAR = new ResourceLocation(Reference.MOD_ID, "textures/gui/tattletail/groom_bar.png");


    private static final int spacing = 14;

    private float partialTicks = 0.0F;
    private float prevPartialTicks = 0.0F;


    public void render() {
        this.updatePartialTicks();
        Minecraft mc = Minecraft.getMinecraft();

        ItemStack mainhand = mc.player.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND);
        ItemStack offhand = mc.player.getItemStackFromSlot(EntityEquipmentSlot.OFFHAND);

        ItemTattletail tattletail;

        if(mainhand.getItem() instanceof ItemTattletail)
        {
            tattletail = ((ItemTattletail) mainhand.getItem());
        }
        else if(offhand.getItem() instanceof ItemTattletail)
        {
            tattletail = ((ItemTattletail) offhand.getItem());
        }
        else
        {
            tattletail = null;
        }



        TextureManager textureManager = mc.getTextureManager();
        ScaledResolution scaled = new ScaledResolution(mc);
        FontRenderer fontRenderer = mc.fontRenderer;
        CitrusThingsConfig config = CitrusThingsConfig.getInstance();

        //ICapabilityDivingAttributes idiving = mc.player.getCapability(CapabilityDivingAttributesProvider.DIVING_ATTRIBUTES, null);

        int x = GuiHelper.getAnchorX(102, config.client.guiTattleTailConfig);
        int y = GuiHelper.getAnchorY(21, config.client.guiTattleTailConfig);


        float battery = tattletail.batteryMeter;
        float feed = tattletail.feedMeter;
        float groom = tattletail.groomMeter;




        boolean blend = GL11.glGetBoolean(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_BLEND);
        GL14.glBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_ONE, GL11.GL_ZERO);

        textureManager.bindTexture(GuiTattletail.BATTERY_BAR);
        GuiHelper.drawTexture(x , y + spacing, (1.0D - battery/100), 0.0D, 70.0D * battery/100, 7.0D, battery/100, 1.0D);

        textureManager.bindTexture(GuiTattletail.FEED_BAR);
        GuiHelper.drawTexture(x, y + 2*spacing, (1.0D - feed/100), 0.0D, 70.0D * feed/100, 7.0D, feed/100, 1.0D);

        textureManager.bindTexture(GuiTattletail.GROOM_BAR);
        GuiHelper.drawTexture(x, y + 3*spacing, (1.0D - groom/100), 0.0D, 70.0D * groom/100, 7.0D, groom/100, 1.0D);


        if (!blend) {
            GL11.glDisable(GL11.GL_BLEND);
        }

        this.drawCenteredString(fontRenderer, Integer.toString((int)battery), x + 50, y + spacing, Integer.parseInt("FFFFFF", 16));
        this.drawCenteredString(fontRenderer, "BATT.", x - 20, y + spacing, Integer.parseInt("FFFFFF", 16));

        this.drawCenteredString(fontRenderer, Integer.toString((int)feed), x + 50, y + 2*spacing, Integer.parseInt("FFFFFF", 16));
        this.drawCenteredString(fontRenderer, "FEED", x - 19, y + 2*spacing, Integer.parseInt("FFFFFF", 16));

        this.drawCenteredString(fontRenderer, Integer.toString((int)groom), x + 50, y + 3*spacing, Integer.parseInt("FFFFFF", 16));
        this.drawCenteredString(fontRenderer, "GROOM", x - 21, y + 3*spacing, Integer.parseInt("FFFFFF", 16));
    }

    public void updatePartialTicks() {
        Minecraft mc = Minecraft.getMinecraft();
        float f = mc.getRenderPartialTicks() - this.prevPartialTicks;
        if (f <= 0.0F) {
            f++;
        }
        this.partialTicks += f;
        this.prevPartialTicks = mc.getRenderPartialTicks();
    }

}
