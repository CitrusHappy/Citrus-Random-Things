package io.github.citrushappy.entity.ai;

import io.github.citrushappy.entity.EntityBakaMitaiCreeper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;


public class EntityAIBakaMitaiCreeperSwell extends EntityAIBase
{
    EntityBakaMitaiCreeper swellingCreeper;
    EntityLivingBase creeperAttackTarget;

    public EntityAIBakaMitaiCreeperSwell(EntityBakaMitaiCreeper entitycreeperIn)
    {
        this.swellingCreeper = entitycreeperIn;
        this.setMutexBits(1);
    }

    public boolean shouldExecute()
    {
        EntityLivingBase entitylivingbase = this.swellingCreeper.getAttackTarget();
        return this.swellingCreeper.getCreeperState() > 0 || entitylivingbase != null && this.swellingCreeper.getDistanceSq(entitylivingbase) < 9.0D;
    }

    public void startExecuting()
    {
        this.swellingCreeper.getNavigator().clearPath();
        this.creeperAttackTarget = this.swellingCreeper.getAttackTarget();
    }

    public void resetTask()
    {
        this.creeperAttackTarget = null;
    }

    public void updateTask()
    {
        if (this.creeperAttackTarget == null)
        {
            this.swellingCreeper.setCreeperState(-1);
        }
        else if (this.swellingCreeper.getDistanceSq(this.creeperAttackTarget) > 49.0D)
        {
            this.swellingCreeper.setCreeperState(-1);
        }
        else if (!this.swellingCreeper.getEntitySenses().canSee(this.creeperAttackTarget))
        {
            this.swellingCreeper.setCreeperState(-1);
        }
        else
        {
            this.swellingCreeper.setCreeperState(1);
        }
    }
}
