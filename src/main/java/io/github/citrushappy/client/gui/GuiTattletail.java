package io.github.citrushappy.client.gui;


import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.math.MathHelper;

public class GuiTattletail extends Gui {

    /*
    private static final ResourceLocation BACKGROUND = new ResourceLocation(BetterDiving.MOD_ID, "textures/gui/oxygen/oxygen_alternative_background.png");
    private static final ResourceLocation BAR = new ResourceLocation(BetterDiving.MOD_ID, "textures/gui/oxygen/oxygen_alternative_bar.png");
    private static final ResourceLocation FRAME = new ResourceLocation(BetterDiving.MOD_ID, "textures/gui/oxygen/oxygen_alternative_frame.png");

    private static final ResourceLocation OXYGEN_BAR = new ResourceLocation(BetterDiving.MOD_ID, "textures/gui/oxygen/oxygen_bar.png");
    private static final ResourceLocation OXYGEN_BUBBLES = new ResourceLocation(BetterDiving.MOD_ID, "textures/gui/oxygen/oxygen_bubbles.png");

    private float partialTicks = 0.0F;
    private float prevPartialTicks = 0.0F;
}

    public void render() {
        this.updatePartialTicks();
        Minecraft mc = Minecraft.getMinecraft();
        TextureManager textureManager = mc.getTextureManager();
        ScaledResolution scaled = new ScaledResolution(mc);
        FontRenderer fontRenderer = mc.fontRenderer;
        BetterDivingConfig config = BetterDivingConfig.getInstance();
        ICapabilityDivingAttributes idiving = mc.player.getCapability(CapabilityDivingAttributesProvider.DIVING_ATTRIBUTES, null);

        if (config.client.guiOxygenAlternative) {
            int air = (int) Math.round(idiving.getOxygenFromPlayer() / 20.0D / 3.0D) * 3;
            double percent = idiving.getOxygenFromPlayerInPercent();
            int x = GuiHelper.getAnchorX(102, config.client.guiOxygenConfig);
            int y = GuiHelper.getAnchorY(21, config.client.guiOxygenConfig);
            double offset;

            boolean blend = GL11.glGetBoolean(GL11.GL_BLEND);
            GL11.glEnable(GL11.GL_BLEND);
            GL14.glBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_ONE, GL11.GL_ZERO);

            textureManager.bindTexture(GuiOxygen.BACKGROUND);
            GuiHelper.drawTexture(x, y, 0.0D, 0.0D, 102, 21, 1.0D, 1.0D);

            textureManager.bindTexture(GuiOxygen.BAR);
            GuiHelper.drawTexture(x + 1.0D + 80.0D * (1.0D - percent), y + 7.0D, (1.0D - percent), 0.0D, 80.0D * percent, 7.0D, percent, 1.0D);

            textureManager.bindTexture(GuiOxygen.OXYGEN_BUBBLES);
            offset = ((2.0D * this.partialTicks) % 128) / 128.0D;
            this.drawBubbles(x + 1.0D, y + 7.0D, 0.0D, offset, percent);
            offset = ((2.5D * this.partialTicks) % 128) / 128.0D;
            this.drawBubbles(x + 1.0D, y + 7.0D, 20.0D, offset + 0.45D, percent);
            offset = ((1.5D * this.partialTicks) % 128) / 128.0D;
            this.drawBubbles(x + 1.0D, y + 7.0D, 35.0D, offset + 0.12D, percent);
            offset = ((2.0D * this.partialTicks) % 128) / 128.0D;
            this.drawBubbles(x + 1.0D, y + 7.0D, 55.0D, offset + 0.68D, percent);

            textureManager.bindTexture(GuiOxygen.FRAME);
            GuiHelper.drawTexture(x, y, 0.0D, 0.0D, 102, 21, 1.0D, 1.0D);

            if (!blend) {
                GL11.glDisable(GL11.GL_BLEND);
            }

            this.drawCenteredString(fontRenderer, Integer.toString(air), x + 91, y + 11, Integer.parseInt("FFFFFF", 16));
            this.drawCenteredString(fontRenderer, "O\u2082", x + 91, y + 2, Integer.parseInt("FFFFFF", 16));
        }
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

    public void drawBubbles(double x, double y, double xOffset, double vOffset, double percent) {
        double width = 128.0D / 6.0D;
        double height = 128.0D / 16.0D;
        xOffset = MathHelper.clamp(xOffset, 0.0D, 80.0D - width);
        percent = MathHelper.clamp(percent * 80.0D / width - (80.0D - width - xOffset) / width, 0.0D, 1.0D);
        GuiHelper.drawTexture(x + xOffset + width * (1.0D - percent), y, 1.0D - percent, vOffset, width * percent, height, percent, 0.375D);
    }

     */

}
