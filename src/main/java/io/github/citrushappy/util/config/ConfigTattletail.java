package io.github.citrushappy.util.config;

import io.github.citrushappy.util.config.annotation.ExtraConfig;
import net.minecraftforge.common.config.Config;

public class ConfigTattletail {
    @Config.Comment("Enable/Disable Tattletail care requirement GUI")
    @Config.Name("Care GUI Enabled")
    @ExtraConfig.FieldElement("true")
    public boolean enabled = true;

    @Config.Comment({"Determines which side of the screen to anchor the GUI to",
            "0: top-left, 1: top-middle, 2: top-right, 3: bottom-right, 4: bottom-middle, 5: bottom-left"})
    @Config.Name("Care GUI Anchor Point")
    @Config.RangeInt(min = 0, max = 5)
    public int anchor = 0;

    @Config.Comment("X-offset")
    @Config.Name("Care GUI X Offset")
    @Config.RangeInt(min = -1000, max = 1000)
    public int offsetX = 50;

    @Config.Comment("Y-offset")
    @Config.Name("Care GUI Y Offset")
    @Config.RangeInt(min = -1000, max = 1000)
    public int offsetY = 15;
}

