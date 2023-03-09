package io.github.citrushappy.items;

import io.github.citrushappy.CitrusThings;
import io.github.citrushappy.init.SoundsHandler;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

public class ItemKnife extends ItemSword
{
    public ItemKnife(Item.ToolMaterial material)
    {
        super(material);
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
}
