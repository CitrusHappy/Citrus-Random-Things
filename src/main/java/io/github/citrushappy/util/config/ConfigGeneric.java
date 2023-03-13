package io.github.citrushappy.util.config;

import io.github.citrushappy.util.config.annotation.ExtraConfig;
import net.minecraftforge.common.config.Config;

public class ConfigGeneric {
    @Config.Comment("Enable/Disable Debugging")
    @Config.Name("Debug Enabled")
    @ExtraConfig.FieldElement("false")
    public boolean enableDebug = false;
}
