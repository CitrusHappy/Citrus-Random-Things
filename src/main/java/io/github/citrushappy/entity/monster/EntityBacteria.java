package io.github.citrushappy.entity.monster;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.IAnimationTickable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class EntityBacteria extends EntityCreature implements IAnimatable, IAnimationTickable
{
    private final AnimationFactory factory = new AnimationFactory(this);

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (event.isMoving())
        {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.bacteria.walk", true));
            return PlayState.CONTINUE;
        }
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.bacteria.idle", true));
        return PlayState.CONTINUE;
    }

    public EntityBacteria(World worldIn)
    {
        super(worldIn);
        this.ignoreFrustumCheck = true;
        this.setSize(1.5F, 1.5F);
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
    public void onUpdate()
    {
        super.onUpdate();
    }

    public void onLivingUpdate()
    {
        super.onLivingUpdate();
    }

    @Override
    protected void initEntityAI()
    {
        //this.tasks.addTask(0, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(0, new EntityAIWatchClosest(this, EntityPlayer.class, 50.0F));
        //this.tasks.addTask(1, new EntityAIWander(this, 0.4D));
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

    //play sounds
    /*
    @Override
    protected void playStepSound(BlockPos pos, Block blockIn) {
        this.playSound(this.getStepSound(), 1F, 1F);
    }
    //get sounds

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

    protected SoundEvent getStepSound()
    {

        return SoundsHandler.ENTITY_CREWMATE_STEP_GENERIC;
    }

     */


    @Override
    public void onCollideWithPlayer(EntityPlayer entityIn)
    {
    }



    @Override
    protected float getSoundVolume()
    {
        return 0.4F;
    }
}
