package io.github.citrushappy.client.model;


import codechicken.lib.texture.TextureUtils;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;

public class ArmorModelHelper implements TextureUtils.IIconRegister {

    @Override
    public void registerIcons(TextureMap textureMap) {


        textureMap.registerSprite(new ResourceLocation("citrusthings:models/armor/drip"));
    }
}
