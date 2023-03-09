package io.github.citrushappy.client.renderer.entity;

import io.github.citrushappy.client.model.entity.ModelBacteria;
import io.github.citrushappy.client.model.entity.ModelCrewMate;
import io.github.citrushappy.entity.monster.EntityBacteria;
import io.github.citrushappy.entity.passive.EntityCrewMate;
import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RendererBacteria extends GeoEntityRenderer<EntityBacteria>
{
    public RendererBacteria(RenderManager renderManager)
    {
        super(renderManager, new ModelBacteria());
        this.shadowSize = 0.4F; //change 0.7 to the desired shadow size.
    }

}