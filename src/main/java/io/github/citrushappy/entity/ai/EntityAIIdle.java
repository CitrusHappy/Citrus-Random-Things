package io.github.citrushappy.entity.ai;

import io.github.citrushappy.entity.passive.EntityCrewMate;
import net.minecraft.entity.ai.EntityAIBase;

public class EntityAIIdle extends EntityAIBase {
    protected final EntityCrewMate entity;

    public EntityAIIdle(EntityCrewMate creatureIn){
        this.entity = creatureIn;
    }

    @Override
    public boolean shouldExecute() {
        if(this.entity.isPartying()){
            return true;
        }
        return false;
    }

    public boolean shouldContinueExecuting()
    {
        return this.entity.isPartying();
    }

    @Override
    public boolean isInterruptible() {
        return false;
    }
}
