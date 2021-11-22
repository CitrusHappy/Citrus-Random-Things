package io.github.citrushappy.init;


import io.github.citrushappy.entity.EntityCrewMate;
import io.github.citrushappy.MoreModels;
import io.github.citrushappy.util.Reference;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class EntityInit {

    public static final EntityEntry CREW_MATE = null;

    public static void registerEntities()
    {
        registerEntity("CREW_MATE", EntityCrewMate.class, Reference.ENTITY_CREWMATE, 30, 16731716, 16777215);
    }

    private static void registerEntity(String name, Class<? extends Entity> entity, int id, int range, int color1, int color2)
    {
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID + ":" + name), entity, name, id, MoreModels.instance, range, 1, true, color1, color2);
    }
}
