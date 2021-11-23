package io.github.citrushappy.entity;

import io.github.citrushappy.util.handlers.SoundsHandler;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.IAnimationTickable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class EntityCrewMate extends EntityCreature implements IAnimatable, IAnimationTickable
{
    private AnimationFactory factory = new AnimationFactory(this);

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (event.isMoving())
        {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.crewmate.walk", true));
            return PlayState.CONTINUE;
        }
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.crewmate.idle", true));
        return PlayState.CONTINUE;
    }

    public EntityCrewMate(World worldIn)
    {
        super(worldIn);
        this.ignoreFrustumCheck = true;
        this.setSize(0.7F, 1.3F);
    }

    @Override
    public void registerControllers(AnimationData data)
    {
        data.addAnimationController(new AnimationController(this, "controller", 1, this::predicate));
    }

    @Override
    public AnimationFactory getFactory()
    {
        return this.factory;
    }

    @Override
    protected void initEntityAI()
    {
        this.tasks.addTask(0, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(1, new EntityAIWander(this, 0.4D));
        this.tasks.addTask(2, new EntityAIWatchClosest(this, EntityPlayer.class, 5.0F));
        this.tasks.addTask(2, new EntityAILookIdle(this));
        super.initEntityAI();
    }

    @Override
    public int tickTimer() {
        return ticksExisted;
    }

    @Override
    public void tick() {
        super.onUpdate();
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return SoundsHandler.ENTITY_CREWMATE_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source)
    {
        return SoundsHandler.ENTITY_CREWMATE_HURT;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return SoundsHandler.ENTITY_CREWMATE_DEATH;
    }

    protected void entityInit()
    {
        super.entityInit();
    }
}
