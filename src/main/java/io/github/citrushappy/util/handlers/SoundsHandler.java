package io.github.citrushappy.util.handlers;

import io.github.citrushappy.util.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class SoundsHandler {
    public static SoundEvent ENTITY_CREWMATE_AMBIENT, ENTITY_CREWMATE_HURT, ENTITY_CREWMATE_DEATH;

    public static void registerSounds()
    {
        ENTITY_CREWMATE_AMBIENT = registerSound("entity.crewmate.ambient");
        ENTITY_CREWMATE_HURT = registerSound("entity.crewmate.hurt");
        ENTITY_CREWMATE_DEATH = registerSound("entity.crewmate.death");
    }

    private static SoundEvent registerSound(String name)
    {
        ResourceLocation location = new ResourceLocation(Reference.MOD_ID, name);
        SoundEvent event = new SoundEvent(location);
        event.setRegistryName(name);
        ForgeRegistries.SOUND_EVENTS.register(event);
        return event;
    }
}
