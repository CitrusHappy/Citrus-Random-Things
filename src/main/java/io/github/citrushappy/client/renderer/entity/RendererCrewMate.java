package io.github.citrushappy.client.renderer.entity;

import io.github.citrushappy.client.model.entity.ModelCrewMate;
import io.github.citrushappy.entity.EntityCrewMate;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.math.Vec3d;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RendererCrewMate extends GeoEntityRenderer<EntityCrewMate>
{
    private float rotationYaw;

    public RendererCrewMate(RenderManager renderManager)
    {
        super(renderManager, new ModelCrewMate());
        this.shadowSize = 0.4F; //change 0.7 to the desired shadow size.
    }

    //The reason I am using this approach to stop the entity from rotating is because it is the least invasive. I tried
    //returning the tick method but that just stopped remove() from being executed. I tried to return all rotation methods
    //but those don't really work either because Vanilla jank. I tried returning this method but that made the rotation 0 no
    //matter what the angle was before death and that is just lazy even for me. This solution is simple but has one problem.
    //If I am ever to go out of the entity's rendering frustum, I believe the rotation will become 0, but it is an edge case
    //that isn't easily noticeable.
    @Override
    protected void applyRotations(EntityCrewMate entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        if(entityLiving.isDead()){
            super.applyRotations(entityLiving, ageInTicks, this.rotationYaw, partialTicks);
        }else{
            this.rotationYaw = rotationYaw;
            super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
        }
    }

    /**
     * How much the entity rotates when it dies. The default is 90 degrees like lying on the ground dead.
     * @param entity The entity that is dying.
     * @return a float of the degrees that this entity rotates on death.
     */
    @Override
    protected float getDeathMaxRotation(EntityCrewMate entity) {
        return 0f;
    }

    /*
    @Override
    public void doRender(EntityCrewMate entity, double x, double y, double z, float entityYaw, float partialTicks) {
        renderEarly(animatable, matrixStackIn, partialTicks, renderTypeBuffer, vertexBuilder, packedLightIn,
                packedOverlayIn, red, green, blue, alpha);

        if (renderTypeBuffer != null) {
            vertexBuilder = renderTypeBuffer.getBuffer(type);
        }
        //Makes it that the entity is only rendered with a red overlay when hurt but not on death.
        renderRecursively(model.topLevelBones.get(0), matrixStackIn, vertexBuilder, packedLightIn, OverlayTexture.pack(0,
                OverlayTexture.v(animatable.hurtTime > 0)), red, green, blue, alpha);
    }
     */


}