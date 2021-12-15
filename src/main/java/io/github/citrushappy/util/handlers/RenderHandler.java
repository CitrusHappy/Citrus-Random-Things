package io.github.citrushappy.util.handlers;

import io.github.citrushappy.client.renderer.entity.RendererBakaMitaiCreeper;
import io.github.citrushappy.client.renderer.entity.RendererCrewMate;
import io.github.citrushappy.entity.EntityBakaMitaiCreeper;
import io.github.citrushappy.entity.EntityCrewMate;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class RenderHandler {
    public static void registerEntityRenders()
    {
        RenderingRegistry.registerEntityRenderingHandler(EntityCrewMate.class, RendererCrewMate::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityBakaMitaiCreeper.class, RendererBakaMitaiCreeper::new);
    }
}
