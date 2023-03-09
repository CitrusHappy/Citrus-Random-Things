package io.github.citrushappy.items;

import io.github.citrushappy.CitrusThings;
import io.github.citrushappy.init.SoundsHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.*;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
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

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.List;
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
    public boolean isBeingHeld = false;
    public int timeToSpeak = 200;
    //per tick drain amount 60 ticks in 1 second
    public float drainAmount = .001F;

    //NBT
    public boolean metTT = false;
    public int idTT = 0;
    public String nameTT = "Tattletail";
    public float feedMeter = 100F;
    public float groomMeter = 100F;
    public float batteryMeter = 100F;
    public int happiness = 255;
    public int friendliness = 255;



    public ItemTattletail()
     {
         super();
         setMaxStackSize(1);
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

            if(timeToSpeak <= 0 && isBeingHeld)
            {
                //can speak
                CitrusThings.logger.log(Level.INFO, "light level: " + player.world.getLight(player.getPosition()) + " < 4");
                CitrusThings.logger.log(Level.INFO, "busy?: " + this.busy);
                CitrusThings.logger.log(Level.INFO, "hunger: " + this.feedMeter);
                CitrusThings.logger.log(Level.INFO, "power: " + this.batteryMeter);
                CitrusThings.logger.log(Level.INFO, "brush: " + this.groomMeter);

                if(!metTT)
                {
                    //fist time meeting tattletail
                    player.playSound(SoundsHandler.ITEM_TT_BARK, 1, 1);
                    //event.getController().setAnimation("animation.tattletail.speak");
                    this.metTT = true;
                }

                if (this.groomMeter <= 0)
                {
                    this.busy = true;
                    this.timeToSpeak = 2 * 60;
                    player.playSound(SoundsHandler.ITEM_TT_BRUSH_ME, 1, 1);
                }
                else if (batteryMeter <= 0)
                {
                    busy = true;
                    this.timeToSpeak = 2 * 60;
                    float f = 0.1F + random.nextFloat() * 0.9F;
                    player.playSound(SoundsHandler.ITEM_TT_UH_OH, 1, f);
                }
                else if (feedMeter <= 0)
                {
                    this.busy = true;
                    this.timeToSpeak = 2 * 60;
                    player.playSound(SoundsHandler.ITEM_TT_GIVE_ME_A_TREAT, 1, 1);
                    //event.getController().setAnimation();
                }
                else if (player.world.getLight(player.getPosition()) < 4)
                {
                    this.busy = true;
                    //low light detected level 4
                    this.timeToSpeak = 2 * 60;
                    player.playSound(SoundsHandler.ITEM_TT_AHH, 1, 1);
                }
                else
                {
                    //chance to speak or sing randomly
                    //int i = random.nextInt(animationListVerbal.length);
                    //player.playSound(SoundsHandler.ITEM_TT_AHH, 1, 1);
                    //controller.setAnimation(new AnimationBuilder().addAnimation(animationListVerbal[i], false));
                    this.busy = false;
                }
            }
            if(!this.busy)
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

        if(isBeingHeld)
        {
            if (event.instructions.contains("servo"))
            {
                player.playSound(SoundsHandler.ITEM_TT_SERVO, 1, 1);
            }
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

        isBeingHeld = isSelected;

        if(this.timeToSpeak > 0)
        {
            this.timeToSpeak--;
        }

        this.feedMeter -= this.drainAmount;
        this.groomMeter -= this.drainAmount;
        this.batteryMeter -= this.drainAmount;

        //drains slower the stronger the bond
        //this.happiness -= drainAmount * (friendliness/100);

    }

    @Override
    public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn) {
        NBTTagCompound nbt;
        if (stack.hasTagCompound())
        {
            nbt = stack.getTagCompound();
        }
        else
        {
            nbt = new NBTTagCompound();
        }

        assert nbt != null;
        if(!nbt.hasKey("ID"))
            nbt.setInteger("ID", this.idTT++);
        else
        {
            nbt.setInteger("ID", this.idTT);
            nbt.setBoolean("MetTT", this.metTT);
            nbt.setString("Name", this.nameTT);
            nbt.setFloat("BrushMeter", this.groomMeter);
            nbt.setFloat("HungerMeter", this.feedMeter);
            nbt.setFloat("PowerMeter", this.batteryMeter);
        }
        stack.setTagCompound(nbt);
    }

    //item overrides
    @Override
    public boolean onDroppedByPlayer(ItemStack item, EntityPlayer player) {
        if(!this.busy)
            player.playSound(SoundsHandler.ITEM_TT_WHEE, 1, 1);
        //create entity
        return super.onDroppedByPlayer(item, player);
    }

    @Override
    public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, EntityPlayer player) {
        if(!this.busy && this.timeToSpeak <= 0)
        {
            this.timeToSpeak = 60;
            this.happiness -= 10;
            player.playSound(SoundsHandler.ITEM_TT_AHH, 1, 1);
        }



        return super.onBlockStartBreak(itemstack, pos, player);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        this.batteryMeter = 0;
        //playerIn.openGui();

        return super.onItemRightClick(worldIn, playerIn, handIn);
    }



    //tooltips
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        NBTTagCompound nbt = stack.getTagCompound();

        if (stack.hasTagCompound())
        {
            Collection<String> tags = nbt.getKeySet();
            for (String key : tags)
            {
                NBTBase tag = nbt.getTag(key);

                if(tag instanceof NBTTagString)
                    tooltip.add(((NBTTagString) tag).getString());

                if(tag instanceof NBTTagFloat)
                    tooltip.add(Float.toString(((NBTTagFloat) tag).getFloat()));

                if(tag instanceof NBTTagInt)
                    tooltip.add(Integer.toString(((NBTTagInt) tag).getInt()));
            }
        }

    }




    //capabilities
    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable NBTTagCompound nbt) {
        return super.initCapabilities(stack, nbt);
    }
}
