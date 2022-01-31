package io.github.citrushappy.items;

import io.github.citrushappy.CitrusThings;
import io.github.citrushappy.util.handlers.SoundsHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
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

import java.util.Random;

public class ItemTattletail extends Item implements IAnimatable {
    private AnimationFactory factory = new AnimationFactory(this);
    private String controllerName = "popupController";

    private static String IDLE = "animation.tattletail.idle";
    private static String WORRIED_LOOK = "animation.tattletail.look_worried";
    private static String EAR_WIGGLE = "animation.tattletail.ear_wiggle";
    private static String BLINK = "animation.tattletail.blink";

    String[] animationListNonVerbal = { WORRIED_LOOK, EAR_WIGGLE, BLINK };
    String [] animationListVerbal = {};

    public boolean busy = false;
    public int timeToSpeak = 200;
    //per tick drain amount 60 ticks in 1 second
    public float drainAmount = .001F;

    //NBT
    public boolean metTT = false;
    public int idTT = 0;
    public String nameTT = "Tattletail";
    public float hungerMeter = 100F;
    public float brushMeter = 100F;
    public float powerMeter = 100F;
    public int happiness = 255;
    public int friendliness = 255;



    public ItemTattletail()
     {
         super();
         this.maxStackSize = 1;
     }

    //animation functions
    private <P extends Item & IAnimatable> PlayState predicate(AnimationEvent<P> event)
    {
        AnimationController controller = event.getController();

        EntityPlayerSP player = Minecraft.getMinecraft().player;

        Random random = new Random();


        if(controller.getAnimationState() == AnimationState.Stopped)
        {
            //nothing important is happening
            //look for something to do

            if(!metTT)
            {
                //fist time meeting tattletail
                player.playSound(SoundsHandler.ITEM_TT_BARK, 1, 1);
                //event.getController().setAnimation("animation.tattletail.speak");
                metTT = true;
            }

            if(timeToSpeak <= 0)
            {
                //can speak
                CitrusThings.logger.log(Level.INFO, "light level: " + player.world.getLight(player.getPosition()) + " < 4");
                CitrusThings.logger.log(Level.INFO, "busy?: " + this.busy);
                CitrusThings.logger.log(Level.INFO, "hunger: " + this.hungerMeter);
                CitrusThings.logger.log(Level.INFO, "power: " + this.powerMeter);
                CitrusThings.logger.log(Level.INFO, "brush: " + this.brushMeter);

                if (brushMeter <= 0)
                {
                    busy = true;
                    timeToSpeak = 2 * 60;
                    player.playSound(SoundsHandler.ITEM_TT_BRUSH_ME, 1, 1);
                }
                else if (powerMeter <= 0)
                {
                    busy = true;
                    timeToSpeak = 2 * 60;
                    float f = 0.1F + random.nextFloat() * 0.9F;
                    player.playSound(SoundsHandler.ITEM_TT_UH_OH, 1, f);
                }
                else if (hungerMeter <= 0)
                {
                    busy = true;
                    timeToSpeak = 2 * 60;
                    player.playSound(SoundsHandler.ITEM_TT_GIVE_ME_A_TREAT, 1, 1);
                    //event.getController().setAnimation();
                }
                else if (player.world.getLight(player.getPosition()) < 4)
                {
                    busy = true;
                    //low light detected level 4
                    timeToSpeak = 2 * 60;
                    player.playSound(SoundsHandler.ITEM_TT_AHH, 1, 1);
                }
                else
                {
                    //chance to speak or sing randomly
                    int i = random.nextInt(animationListVerbal.length);
                    //player.playSound(SoundsHandler.ITEM_TT_AHH, 1, 1);
                    //controller.setAnimation(new AnimationBuilder().addAnimation(animationListVerbal[i], false));
                    busy = false;
                }
            }
            if(!busy)
            {
                //not speaking

                int i = random.nextInt(animationListNonVerbal.length);
                //CitrusThings.logger.log(Level.INFO, "picked animation: " + animationList1[i]);
                controller.setAnimation(new AnimationBuilder().addAnimation(animationListNonVerbal[i], false));
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
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);

        if (!worldIn.isRemote)
        {
            if (!getTagCompoundSafe(stack).hasKey("ID") && stack.getTagCompound() != null)
            {
                getTagCompoundSafe(stack).setBoolean("MetTT", this.metTT);
                getTagCompoundSafe(stack).setInteger("ID", this.idTT++);
                getTagCompoundSafe(stack).setString("Name", this.nameTT);
                getTagCompoundSafe(stack).setFloat("BrushMeter", this.brushMeter);
                getTagCompoundSafe(stack).setFloat("HungerMeter", this.hungerMeter);
                getTagCompoundSafe(stack).setFloat("PowerMeter", this.powerMeter);
            }
        }

        if(timeToSpeak > 0)
        {
            timeToSpeak--;
        }

        this.hungerMeter -= drainAmount;
        this.brushMeter -= drainAmount;
        this.powerMeter -= drainAmount;

        //drains slower the stronger the bond
        //this.happiness -= drainAmount * (friendliness/100);

    }

    //item overrides
    @Override
    public boolean onDroppedByPlayer(ItemStack item, EntityPlayer player) {
        if(!busy)
            player.playSound(SoundsHandler.ITEM_TT_WHEE, 1, 1);
        //create entity
        return super.onDroppedByPlayer(item, player);
    }

    @Override
    public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn) {
        super.onCreated(stack, worldIn, playerIn);
        //add NBT tag for name
        //stack.setTagInfo("");
    }

    @Override
    public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, EntityPlayer player) {
        if(!busy && timeToSpeak <= 0)
        {
            timeToSpeak = 60;
            happiness -= 10;
            player.playSound(SoundsHandler.ITEM_TT_AHH, 1, 1);
        }



        return super.onBlockStartBreak(itemstack, pos, player);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        this.powerMeter = 0;

        return super.onItemRightClick(worldIn, playerIn, handIn);
    }



    private NBTTagCompound getTagCompoundSafe(ItemStack stack) {
        NBTTagCompound tagCompound = stack.getTagCompound();
        if (tagCompound == null) {
            tagCompound = new NBTTagCompound();
            stack.setTagCompound(tagCompound);
        }
        return tagCompound;
    }
}
