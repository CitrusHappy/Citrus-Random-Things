package io.github.citrushappy.entity.ai;

import io.github.citrushappy.entity.passive.EntityCrewMate;
import net.minecraft.entity.ai.EntityAIBase;

public class EntityAIDance extends EntityAIBase {
    protected final EntityCrewMate entity;

    public EntityAIDance(EntityCrewMate creatureIn){
        this.entity = creatureIn;
    }

    @Override
    public boolean shouldExecute() {
        if(this.entity.isPartying()){
            return true;
        }
        return false;
    }

    public void startExecuting() {
        this.entity.getNavigator().clearPath();
    }
}
