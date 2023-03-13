package io.github.citrushappy.util.config;

import io.github.citrushappy.util.config.annotation.ExtraConfig;
import net.minecraftforge.common.config.Config;

public class ConfigAOA3 {
    @Config.Comment({"Enable/Disable Expedition Mechanics",
            "Read more about it here: https://adventofascension.fandom.com/wiki/Removed_features/Expedition",
            "Prevents \"You feel the wind carry you beneath your feet\" from occurring."})
    @Config.Name("Disable Expedition Mechanics")
    @ExtraConfig.FieldElement("true")
    public boolean disableExpedition = true;
}
