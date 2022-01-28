package io.github.citrushappy.client.model.entity;


import io.github.citrushappy.entity.EntityImposter;
import io.github.citrushappy.util.Reference;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class ModelImposter extends AnimatedTickingGeoModel<EntityImposter>
{
    @Override
    public ResourceLocation getModelLocation(EntityImposter object) {
        return new ResourceLocation(Reference.MOD_ID, "geo/entity/crewmate.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(EntityImposter object) {
        return new ResourceLocation(Reference.MOD_ID, "textures/entity/crewmate" + object.getCrewMateType() + ".png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EntityImposter animatable) {
        return new ResourceLocation(Reference.MOD_ID, "animations/crewmate.animation.json");
    }

    @Override
    public void setLivingAnimations(EntityImposter entity, Integer uniqueID, AnimationEvent customPredicate)
    {
        super.setLivingAnimations(entity, uniqueID, customPredicate);

        if(!entity.isDead())
        {
            IBone head = this.getAnimationProcessor().getBone("head");

            EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
            head.setRotationX(extraData.headPitch * ((float)Math.PI / 180F));
            head.setRotationY(extraData.netHeadYaw * ((float)Math.PI / 180F));
        }
    }
}