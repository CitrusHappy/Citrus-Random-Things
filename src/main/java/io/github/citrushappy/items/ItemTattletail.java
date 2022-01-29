package io.github.citrushappy.items;

import io.github.citrushappy.CitrusThings;
import io.github.citrushappy.util.handlers.SoundsHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import org.apache.logging.log4j.Level;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.CustomInstructionKeyframeEvent;
import software.bernie.geckolib3.core.event.SoundKeyframeEvent;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

import java.util.Random;

public class ItemTattletail extends Item implements IAnimatable {
    private AnimationFactory factory = new AnimationFactory(this);
    private String controllerName = "popupController";

    private static String IDLE = "animation.tattletail.idle";
    private static String WORRIED_LOOK = "animation.tattletail.look_worried";
    private static String EAR_WIGGLE = "animation.tattletail.ear_wiggle";
    private static String BLINK = "animation.tattletail.blink";

    String[] animationList1 = { WORRIED_LOOK, EAR_WIGGLE, BLINK };

    public boolean metTT = false;

    public float hungerMeter = 100F;
    public float brushMeter = 100F;
    public float powerMeter = 100F;

    private ItemStack stackLocal;


    public ItemTattletail()
     {
         super();
     }

    //animation functions
    private <P extends Item & IAnimatable> PlayState predicate(AnimationEvent<P> event)
    {
        AnimationController controller = GeckoLibUtil.getControllerForStack(this.factory, stackLocal, controllerName);

        EntityPlayerSP player = Minecraft.getMinecraft().player;

        if(controller.getAnimationState() == AnimationState.Stopped)
        {
            //nothing important is happening
            //look for something to do
            //CitrusThings.logger.log(Level.INFO,"your position: " + entity.getPosition() + world.checkLight(entity.getPosition()));

            if(!metTT)
            {
                player.playSound(SoundsHandler.ITEM_TT_BARK, 1, 1);
                //event.getController().setAnimation("animation.tattletail.speak");
                metTT = true;
            }
            else if (brushMeter <= 0)
            {

            }
            else if (powerMeter <= 0)
            {

            }
            else if (hungerMeter <= 0)
            {
                //event.getController().setAnimation();
            }
            /*
            else if (!world.checkLight(entity.getPosition()))
            {
                //no light detected

            }
             */
            else
            {
                //pick random animation
                Random random = new Random();
                int i = random.nextInt(animationList1.length);

                //CitrusThings.logger.log(Level.INFO, "picked animation: " + animationList1[i]);
                controller.setAnimation(new AnimationBuilder().addAnimation(animationList1[i], false));
            }
        }


        return PlayState.CONTINUE;
    }

    private <ENTITY extends IAnimatable> void soundListener(SoundKeyframeEvent<ENTITY> event)
    {
        // The animation for the jackinthebox has a sound keyframe at time 0:00.
        // As soon as that keyframe gets hit this method fires and it starts playing the sound to the current player.
        // The music is synced with the animation so the box opens as soon as the music plays the box opening sound
    }

    private <ENTITY extends IAnimatable> void customEventListener(CustomInstructionKeyframeEvent<ENTITY> event)
    {
        EntityPlayerSP player = Minecraft.getMinecraft().player;


        if (event.instructions.contains("servo"))
        {
            player.playSound(SoundsHandler.ITEM_TT_SERVO, 1, 1);
        }
    }



    @Override
    public void registerControllers(AnimationData data)
    {
        AnimationController controller = new AnimationController(this, controllerName, 20, this::predicate);

        controller.registerCustomInstructionListener(this::customEventListener);
        controller.registerSoundListener(this::soundListener);

        data.addAnimationController(controller);
    }

    @Override
    public AnimationFactory getFactory()
    {
        return this.factory;
    }


    @Override
    public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn) {
        super.onCreated(stack, worldIn, playerIn);

        metTT = false;
        //add NBT tag for name
        //stack.setTagInfo("");
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        stackLocal = stack;
        super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
    }


    //item overrides
    @Override
    public boolean onDroppedByPlayer(ItemStack item, EntityPlayer player) {
         //create entity
        return true;
    }

}
