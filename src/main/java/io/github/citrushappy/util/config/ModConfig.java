package io.github.citrushappy.util.config;

import io.github.citrushappy.util.Reference;
import net.minecraftforge.common.config.Config;

@Config(modid = Reference.MOD_ID)
public class ModConfig {

    @Config.Comment("Client options")
    @Config.Name("Client")
    public static ClientConfig client = new ClientConfig();

    @Config.Comment("Server options")
    @Config.Name("Server")
    public static ServerConfig server = new ServerConfig();

    public static class ClientConfig {
        @Config.Comment("Changes the behavior of Tattletail")
        @Config.Name("Tattletail")
        public ConfigTattletail tattletail = new ConfigTattletail();
    }

    public static class ServerConfig {
        @Config.Comment("Generic Settings")
        @Config.Name("Generic")
        public ConfigGeneric generic = new ConfigGeneric();

        @Config.Comment("Advent of Ascension 3 (Nevermine) Tweaks")
        @Config.Name("AOA3")
        public ConfigAOA3 aoa3 = new ConfigAOA3();
    }
}
