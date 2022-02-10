package io.github.citrushappy.items;


import io.github.citrushappy.CitrusThings;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

import java.util.List;

public class ItemBobux extends Item {




    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {

        int lookDistance = 32;
        float eyePosition = playerIn.getEyeHeight();
        Vec3d vecStart = playerIn.getLookVec();
        Vec3d vecEnd = new Vec3d(vecStart.x * lookDistance, vecStart.y * lookDistance, vecStart.z * lookDistance);
        CitrusThings.logger.info("LOOK VECTORS:" + vecStart + " to " + vecEnd);

        //this is returning null, we need to fix this so it returns something
        //position #1 should be eye position,
        //position #2 should be position X blocks away
        RayTraceResult lookingAt = worldIn.rayTraceBlocks(vecStart, vecEnd, false, true, false);

        List<EntityPlayer> nearbyPlayers = worldIn.getEntitiesWithinAABB(EntityPlayer.class, playerIn.getEntityBoundingBox().grow(8.0D));

        for (EntityPlayer p: nearbyPlayers) {
            //send chat message
            p.sendStatusMessage(new TextComponentString(playerIn.getDisplayNameString() + " has no bobux and is looking at " + lookingAt.hitInfo.toString()), true);
        }

        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
