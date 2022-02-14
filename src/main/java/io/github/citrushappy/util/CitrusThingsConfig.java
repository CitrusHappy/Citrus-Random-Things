package io.github.citrushappy.util;

import io.github.citrushappy.util.config.GuiConfig;
import net.minecraftforge.common.config.Config;

@Config(modid = Reference.MOD_ID)
public class CitrusThingsConfig {

    @Config.LangKey("config.citrusthings")
    @Config.Comment("")
    public static final CitrusThingsConfig MASTER_CONFIG = new CitrusThingsConfig();

    /** Use {@link #getInstance()} instead. */
    @Config.Ignore
    public static final CitrusThingsConfig SLAVE_CONFIG = new CitrusThingsConfig();

    @Config.LangKey("config.category_client_settings")
    @Config.Comment("")
    public final Client client = new Client();

    public static CitrusThingsConfig getInstance() {
        return CitrusThingsConfig.SLAVE_CONFIG;
    }

    public static class Client
    {
        @Config.LangKey("config.gui_tattletail")
        @Config.Comment("")
        public GuiConfig guiTattleTailConfig = new GuiConfig(true, 0, 50, 15);
    }
}
