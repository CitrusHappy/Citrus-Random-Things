package io.github.citrushappy.items;

import io.github.citrushappy.CitrusThings;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.apache.logging.log4j.Level;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class ItemTattletail extends Item implements IAnimatable {
    private AnimationFactory factory = new AnimationFactory(this);
    private boolean inactive = true;
    private World world;
    private Entity entity;

    public float hungerMeter = 100F;
    public float brushMeter = 100F;
    public float playMeter = 100F;

     public ItemTattletail()
     {
         super();
     }

    private <E extends Item & IAnimatable> PlayState predicate(AnimationEvent<E> event)
    {
        //animation.tattletail.worried_look

        if(inactive)
        {
            //nothing important is happening
            //look for something to do
            CitrusThings.logger.log(Level.INFO,"no light detected at " + entity.getPosition() + world.checkLight(entity.getPosition()));

            if(hungerMeter <= 0)
            {
                //event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.tattletail.talk", true));
                //return PlayState.CONTINUE;
            }
            else if (brushMeter <= 0)
            {

            }
            else if (playMeter <= 0)
            {

            }
            else if (!world.checkLight(entity.getPosition()))
            {
                //no light detected

            }
            else
            {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.tattletail.worried_look", true));
                return PlayState.CONTINUE;
            }
        }
        else
        {
            //pause and do nothing for the length of the animation
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.tattletail.idle", true));
            return PlayState.CONTINUE;
        }

        return PlayState.CONTINUE;
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
         world = worldIn;
         entity = entityIn;
        super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
    }

    @Override
    public void registerControllers(AnimationData data)
    {
        data.addAnimationController(new AnimationController(this, "controller", 20, this::predicate));
    }

    @Override
    public AnimationFactory getFactory()
    {
        return this.factory;
    }
}
