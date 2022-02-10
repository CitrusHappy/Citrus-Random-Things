package io.github.citrushappy.client.model.armor;

import io.github.citrushappy.items.ItemDrip;
import io.github.citrushappy.util.Reference;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelDrip extends AnimatedGeoModel<ItemDrip> {
    @Override
    public ResourceLocation getModelLocation(ItemDrip object)
    {
        return new ResourceLocation(Reference.MOD_ID, "geo/armor/drip.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(ItemDrip object)
    {
        return new ResourceLocation(Reference.MOD_ID, "textures/armor/drip.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(ItemDrip animatable)
    {
        return new ResourceLocation(Reference.MOD_ID, "animations/drip.animation.json");
    }
}
