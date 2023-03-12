package io.github.citrushappy.entity.passive;

import io.github.citrushappy.entity.ai.EntityAIDance;
import io.github.citrushappy.entity.monster.EntityImposter;
import io.github.citrushappy.util.Reference;
import io.github.citrushappy.init.SoundsHandler;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
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
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
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
    private int timeToSpeak = 200;
    private boolean canPlayFallSound = false;
    private boolean isPartying = false;
    private BlockPos jukeboxPosition;

    private final AnimationFactory factory = new AnimationFactory(this);

    public EntityCrewMate(World worldIn) {
        super(worldIn);
        this.ignoreFrustumCheck = true;
        this.setSize(0.7F, 1.3F);
    }

    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
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


    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(CREWMATE_VARIANT, 0);
    }

    @Override
    protected void initEntityAI()
    {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIAvoidEntity(this, EntityImposter.class, 8.0F, 0.6D, 0.6D));
        this.tasks.addTask(1, new EntityAIDance(this));
        this.tasks.addTask(2, new EntityAIMoveIndoors(this));
        this.tasks.addTask(3, new EntityAIRestrictOpenDoor(this));
        this.tasks.addTask(4, new EntityAIOpenDoor(this, true));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 0.6D));
        this.tasks.addTask(9, new EntityAIWatchClosest2(this, EntityPlayer.class, 3.0F, 1.0F));
        this.tasks.addTask(9, new EntityAIWanderAvoidWater(this, 0.6D));
        this.tasks.addTask(10, new EntityAIWatchClosest(this, EntityLiving.class, 8.0F));
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if(timeToSpeak > 0) {
            timeToSpeak--;
        }
    }

    public void onLivingUpdate()
    {
        if (this.jukeboxPosition == null || this.jukeboxPosition.distanceSq(this.posX, this.posY, this.posZ) > 34.0D || this.world.getBlockState(this.jukeboxPosition).getBlock() != Blocks.JUKEBOX) {
            this.isPartying = false;
            this.jukeboxPosition = null;
        }

        if(!this.onGround && canPlayFallSound && this.motionY < -0.8D) {
            canPlayFallSound = false;
            this.playSound(SoundsHandler.ENTITY_CREWMATE_FALL_BIG, 1f, 1f);
        }
        if(this.onGround) {
            canPlayFallSound = true;
        }

        super.onLivingUpdate();
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
    public void onCollideWithPlayer(EntityPlayer entityIn) {
        if(!isPartying() && timeToSpeak <= 0 && this.isEntityAlive() && this.rand.nextInt(1000) > this.livingSoundTime++) {
            this.playSound(SoundsHandler.ENTITY_CREWMATE_PLAYER_COLLISION, getSoundVolume(), 1f);
            timeToSpeak = 200;
        }
    }


    //Animation Control
    //==================
    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        String animationName;
        double animationSpeed = 1D;
        AnimationBuilder animationBuilder = new AnimationBuilder();

        if(this.isPartying) {
            animationName = "animation.crewmate.dance";
            animationSpeed = 3D;
        }
        else if(event.isMoving()) {
            //TODO: this gets called way too late after dancing
            animationName = "animation.crewmate.walk";
        }
        else if(isDead()) {
            animationName = "animation.crewmate.death.pose";
        }
        else {
            animationName = "animation.crewmate.idle";
        }

        animationBuilder.addAnimation(animationName, true);
        event.getController().setAnimation(animationBuilder);
        event.getController().setAnimationSpeed(animationSpeed);

        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller", 1, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }


    //Sound Control
    //==================
    @Override
    protected void playStepSound(BlockPos pos, Block blockIn) {
        this.playSound(this.getStepSound(), 1F, 1F);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundsHandler.ENTITY_CREWMATE_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundsHandler.ENTITY_CREWMATE_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundsHandler.ENTITY_CREWMATE_DEATH;
    }

    @Override
    protected float getSoundVolume() { return 0.4F; }

    protected SoundEvent getStepSound() {

        return SoundsHandler.ENTITY_CREWMATE_STEP_GENERIC;
    }


    //Death Logic
    //==================
    public boolean isDead() {
        return ((this.dead || this.getHealth() < 0.01));
    }

    @Override
    protected void onDeathUpdate() {
        ++this.deathTime;
        if (this.deathTime == 80) {
            this.setDead();

            for (int k = 0; k < 20; ++k) {
                double d2 = this.rand.nextGaussian() * 0.02D;
                double d0 = this.rand.nextGaussian() * 0.02D;
                double d1 = this.rand.nextGaussian() * 0.02D;
                this.world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, this.posY + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, d2, d0, d1);
            }
        }
    }


    //Loot Table control
    //==================
    @Nullable
    @Override
    protected ResourceLocation getLootTable() {
        return LOOT;
    }


    //Color variant control
    //==================
    private int getRandomCrewMateType() {
        int i = this.rand.nextInt(12);
        return i;
    }

    public int getCrewMateType() {
        return this.dataManager.get(CREWMATE_VARIANT);
    }

    public void setCrewMateType(int crewmateTypeId) {
        this.dataManager.set(CREWMATE_VARIANT, crewmateTypeId);
    }

    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        compound.setInteger("CrewMateType", this.getCrewMateType());
    }

    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        this.setCrewMateType(compound.getInteger("CrewMateType"));
    }

    public static class CrewMateTypeData implements IEntityLivingData {
        public int typeData;

        public CrewMateTypeData(int type) {
            this.typeData = type;
        }
    }


    //Partying control
    //==================
    @SideOnly(Side.CLIENT)
    public void setPartying(BlockPos pos, boolean isPartying) {
        this.setRotation(this.rotationYaw + 180f, this.rotationPitch);
        this.jukeboxPosition = pos;
        this.isPartying = isPartying;
    }

    @SideOnly(Side.CLIENT)
    public boolean isPartying() {
        return this.isPartying;
    }
}
