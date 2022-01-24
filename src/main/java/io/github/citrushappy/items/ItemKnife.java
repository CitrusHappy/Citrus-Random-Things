package io.github.citrushappy.items;

import ibxm.Player;
import io.github.citrushappy.CitrusThings;
import io.github.citrushappy.util.Reference;
import io.github.citrushappy.util.handlers.SoundsHandler;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemKnife extends ItemSword
{
    public ItemKnife()
    {
        this(ToolMaterial.IRON);
    }
    public ItemKnife(Item.ToolMaterial material)
    {
        super(material);
        setRegistryName("knife");
        setTranslationKey(Reference.MOD_ID + ".knife");
    }


    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        CitrusThings.logger.info(target.getHealth() + " < " + this.getAttackDamage());
        if (target.getHealth() < this.getAttackDamage())
        {
            target.playSound(SoundsHandler.KILL, 1f, 1f);
        }


        return super.hitEntity(stack, target, attacker);
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
}
