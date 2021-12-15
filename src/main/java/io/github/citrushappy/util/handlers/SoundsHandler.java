package io.github.citrushappy.util.handlers;

import io.github.citrushappy.util.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class SoundsHandler {
    public static SoundEvent ENTITY_CREWMATE_AMBIENT, ENTITY_CREWMATE_HURT, ENTITY_CREWMATE_DEATH, ENTITY_CREWMATE_FALL_BIG, ENTITY_CREWMATE_FALL_SMALL, ENTITY_CREWMATE_PLAYER_COLLISION, ENTITY_CREWMATE_STEP_GENERIC;
    public static SoundEvent BLOCK_EMERGENCY_BUTTON;
    public static SoundEvent ENTITY_BAKA_MITAI_CREEPER_BLOW, ENTITY_BAKA_MITAI_CREEPER_FUSE;

    public static void registerSounds()
    {
        ENTITY_CREWMATE_AMBIENT = registerSound("entity.crewmate.ambient");
        ENTITY_CREWMATE_HURT = registerSound("entity.crewmate.hurt");
        ENTITY_CREWMATE_DEATH = registerSound("entity.crewmate.death");
        ENTITY_CREWMATE_FALL_BIG = registerSound("entity.crewmate.fall.big");
        ENTITY_CREWMATE_FALL_SMALL = registerSound("entity.crewmate.fall.small");
        ENTITY_CREWMATE_PLAYER_COLLISION = registerSound("entity.crewmate.player.collision");
        ENTITY_CREWMATE_STEP_GENERIC = registerSound("entity.crewmate.step.generic");

        BLOCK_EMERGENCY_BUTTON = registerSound("block.emergencybutton");

        ENTITY_BAKA_MITAI_CREEPER_FUSE = registerSound("entity.bakamitaicreeper.fuse");
        ENTITY_BAKA_MITAI_CREEPER_BLOW = registerSound("entity.bakamitaicreeper.blow");
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
