package io.github.citrushappy.client.model;

import io.github.citrushappy.items.ItemTattletail;
import io.github.citrushappy.util.Reference;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelTattletail extends AnimatedGeoModel<ItemTattletail> {

    @Override
    public ResourceLocation getModelLocation(ItemTattletail object) {
        return new ResourceLocation(Reference.MOD_ID, "geo/item/tattletail.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(ItemTattletail object) {
        return new ResourceLocation(Reference.MOD_ID, "textures/items/tattletail.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(ItemTattletail animatable) {
        return new ResourceLocation(Reference.MOD_ID, "animations/tattletail.animation.json");
    }
}
