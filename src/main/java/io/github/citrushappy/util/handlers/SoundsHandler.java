package io.github.citrushappy.util.handlers;

import io.github.citrushappy.util.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class SoundsHandler {
    public static SoundEvent ENTITY_CREWMATE_AMBIENT, ENTITY_CREWMATE_HURT, ENTITY_CREWMATE_DEATH, ENTITY_CREWMATE_FALL_BIG, ENTITY_CREWMATE_FALL_SMALL, ENTITY_CREWMATE_PLAYER_COLLISION, ENTITY_CREWMATE_STEP_GENERIC;
    public static SoundEvent BLOCK_EMERGENCY_BUTTON;
    public static SoundEvent ENTITY_BAKA_MITAI_CREEPER_BLOW, ENTITY_BAKA_MITAI_CREEPER_FUSE;
    public static SoundEvent KILL;

    public static SoundEvent ITEM_TT_SERVO;
    public static SoundEvent ITEM_TT_SERVO_LONG;
    public static SoundEvent ITEM_TT_BARK;
    public static SoundEvent ITEM_TT_AHH;
    public static SoundEvent ITEM_TT_WHY_YOU_DO_BAD;
    public static SoundEvent ITEM_TT_GIVE_ME_A_TREAT;
    public static SoundEvent ITEM_TT_UH_OH;
    public static SoundEvent ITEM_TT_ITS_DARK;
    public static SoundEvent ITEM_TT_BRUSH_ME;
    public static SoundEvent ITEM_TT_WHEE;


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

        KILL = registerSound("item.kill");

        ITEM_TT_SERVO = registerSound("item.tt.servo");
        ITEM_TT_SERVO_LONG = registerSound("item.tt.servo_long");
        ITEM_TT_BARK = registerSound("item.tt.bark");
        ITEM_TT_AHH = registerSound("item.tt.ahh");
        ITEM_TT_WHY_YOU_DO_BAD = registerSound("item.tt.why_you_do_bad");

        ITEM_TT_GIVE_ME_A_TREAT = registerSound("item.tt.give_me_a_treat");
        ITEM_TT_UH_OH = registerSound("item.tt.uh_oh");
        ITEM_TT_ITS_DARK = registerSound("item.tt.its_dark");
        ITEM_TT_BRUSH_ME = registerSound("item.tt.brush_me");
        ITEM_TT_WHEE = registerSound("item.tt.whee");


    }

    public static SoundEvent registerSound(String name)
    {
        ResourceLocation location = new ResourceLocation(Reference.MOD_ID, name);
        SoundEvent event = new SoundEvent(location);
        event.setRegistryName(name);
        ForgeRegistries.SOUND_EVENTS.register(event);
        return event;
    }
}
