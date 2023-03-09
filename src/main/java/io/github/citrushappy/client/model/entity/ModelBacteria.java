package io.github.citrushappy.client.model.entity;

import io.github.citrushappy.entity.monster.EntityBacteria;
import io.github.citrushappy.entity.passive.EntityCrewMate;
import io.github.citrushappy.util.Reference;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class ModelBacteria extends AnimatedTickingGeoModel<EntityBacteria>
{
    @Override
    public ResourceLocation getModelLocation(EntityBacteria object) {
        return new ResourceLocation(Reference.MOD_ID, "geo/entity/bacteria.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(EntityBacteria object) {
        return new ResourceLocation(Reference.MOD_ID, "textures/entity/bacteria.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EntityBacteria animatable) {
        return new ResourceLocation(Reference.MOD_ID, "animations/bacteria.animation.json");
    }

    @Override
    public void setLivingAnimations(EntityBacteria entity, Integer uniqueID, AnimationEvent customPredicate)
    {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
    }
}