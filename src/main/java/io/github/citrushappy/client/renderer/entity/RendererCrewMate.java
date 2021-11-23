package io.github.citrushappy.client.renderer.entity;

import io.github.citrushappy.client.model.entity.ModelCrewMate;
import io.github.citrushappy.entity.EntityCrewMate;
import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RendererCrewMate extends GeoEntityRenderer<EntityCrewMate>
{
    public RendererCrewMate(RenderManager renderManager)
    {
        super(renderManager, new ModelCrewMate());
        this.shadowSize = 0.4F; //change 0.7 to the desired shadow size.
    }
}