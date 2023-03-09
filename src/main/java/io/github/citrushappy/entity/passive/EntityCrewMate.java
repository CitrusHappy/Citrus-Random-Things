package io.github.citrushappy.entity.passive;

import io.github.citrushappy.util.Reference;
import io.github.citrushappy.init.SoundsHandler;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.IAnimationTickable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nullable;

public class EntityCrewMate extends EntityCreature implements IAnimatable, IAnimationTickable
{
    public static final ResourceLocation LOOT = new ResourceLocation(Reference.MOD_ID, "entities/crewmate");

    private static final DataParameter<Integer> CREWMATE_VARIANT = EntityDataManager.createKey(EntityCrewMate.class, DataSerializers.VARINT);
    int timeToSpeak = 200;
    boolean canPlayFallSound = false;


    private final AnimationFactory factory = new AnimationFactory(this);

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (event.isMoving())
        {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.crewmate.walk", true));
            return PlayState.CONTINUE;
        }
        if(isDead())
        {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.crewmate.death.pose", true));
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

    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata)
    {
        livingdata = super.onInitialSpawn(difficulty, livingdata);
        int i = this.getRandomCrewMateType();

        if (livingdata instanceof EntityCrewMate.CrewMateTypeData)
        {
            i = ((EntityCrewMate.CrewMateTypeData)livingdata).typeData;
        }
        else
        {
            livingdata = new EntityCrewMate.CrewMateTypeData(i);
        }

        this.setCrewMateType(i);

        return livingdata;
    }

    @Nullable
    @Override
    protected ResourceLocation getLootTable() {
        return LOOT;
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
        if(timeToSpeak > 0)
        {
            timeToSpeak--;
        }
    }

    public void onLivingUpdate()
    {
        super.onLivingUpdate();
        if(!this.onGround && canPlayFallSound && this.motionY < -0.8D)
        {
            canPlayFallSound = false;
            this.playSound(SoundsHandler.ENTITY_CREWMATE_FALL_BIG, 1f, 1f);
        }
        if(this.onGround)
        {
            canPlayFallSound = true;
        }
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

    //play sounds
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


    @Override
    public void onCollideWithPlayer(EntityPlayer entityIn)
    {
        if(timeToSpeak <= 0 && this.isEntityAlive() && this.rand.nextInt(1000) > this.livingSoundTime++)
        {
            this.playSound(SoundsHandler.ENTITY_CREWMATE_PLAYER_COLLISION, getSoundVolume(), 1f);
            timeToSpeak = 200;
        }
    }



    @Override
    protected float getSoundVolume()
    {
        return 0.4F;
    }


    protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(CREWMATE_VARIANT, Integer.valueOf(0));
    }

    public boolean isDead() {
        return ((this.dead || this.getHealth() < 0.01));
    }

    @Override
    protected void onDeathUpdate() {
        ++this.deathTime;
        if (this.deathTime == 80) {
            this.setDead();

            for (int k = 0; k < 20; ++k)
            {
                double d2 = this.rand.nextGaussian() * 0.02D;
                double d0 = this.rand.nextGaussian() * 0.02D;
                double d1 = this.rand.nextGaussian() * 0.02D;
                this.world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, this.posY + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, d2, d0, d1);
            }
        }
    }



    //color variant control
    private int getRandomCrewMateType()
    {
        int i = this.rand.nextInt(12);
        return i;
    }

    public int getCrewMateType()
    {
        return this.dataManager.get(CREWMATE_VARIANT).intValue();
    }

    public void setCrewMateType(int crewmateTypeId)
    {
        this.dataManager.set(CREWMATE_VARIANT, Integer.valueOf(crewmateTypeId));
    }


    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setInteger("CrewMateType", this.getCrewMateType());
    }

    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);
        this.setCrewMateType(compound.getInteger("CrewMateType"));
    }

    public static class CrewMateTypeData implements IEntityLivingData
    {
        public int typeData;

        public CrewMateTypeData(int type)
        {
            this.typeData = type;
        }
    }

}
