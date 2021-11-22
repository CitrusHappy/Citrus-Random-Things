package io.github.citrushappy.client.model.entity;

import io.github.citrushappy.entity.EntityCrewMate;
import io.github.citrushappy.util.Reference;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;

public class ModelCrewMate extends AnimatedTickingGeoModel<EntityCrewMate>
{
    @Override
    public ResourceLocation getModelLocation(EntityCrewMate object) {
        return new ResourceLocation(Reference.MOD_ID, "geo/entity/crewmate.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(EntityCrewMate object) {
        return new ResourceLocation(Reference.MOD_ID, "textures/entity/crewmate.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EntityCrewMate animatable) {
        return new ResourceLocation(Reference.MOD_ID, "animations/crewmate.animation.json");
    }

}