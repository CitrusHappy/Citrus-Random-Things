package io.github.citrushappy.entity.boss;

import net.minecraft.entity.monster.EntityMob;
import net.minecraft.world.World;

public class EntityObamaHead extends EntityMob {
    public EntityObamaHead(World worldIn) {
        super(worldIn);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        this.setNoGravity(true);
    }
}
