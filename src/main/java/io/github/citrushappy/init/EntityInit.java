package io.github.citrushappy.init;


import io.github.citrushappy.CitrusThings;
import io.github.citrushappy.entity.EntityBakaMitaiCreeper;
import io.github.citrushappy.entity.EntityCrewMate;
import io.github.citrushappy.util.Reference;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class EntityInit {

    public static void registerEntities()
    {
        registerEntity("crewmate", EntityCrewMate.class, Reference.ENTITY_CREWMATE, 30, 16731716, 16777215);
        registerEntity("bakamitai_creeper", EntityBakaMitaiCreeper.class, Reference.ENTITY_BAKA_MITAI_CREEPER, 30, 60474, 18490);
    }

    private static void registerEntity(String name, Class<? extends Entity> entity, int id, int range, int color1, int color2)
    {
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID + ":" + name), entity, name, id, CitrusThings.instance, range, 1, true, color1, color2);
    }
}
