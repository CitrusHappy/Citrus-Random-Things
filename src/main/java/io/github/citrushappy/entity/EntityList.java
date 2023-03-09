package io.github.citrushappy.entity;

import io.github.citrushappy.CitrusThings;
import io.github.citrushappy.client.renderer.entity.*;
import io.github.citrushappy.entity.boss.EntityObamaHead;
import io.github.citrushappy.entity.monster.EntityBacteria;
import io.github.citrushappy.entity.monster.EntityBakaMitaiCreeper;
import io.github.citrushappy.entity.monster.EntityImposter;
import io.github.citrushappy.entity.passive.EntityCrewMate;
import io.github.citrushappy.entity.passive.EntityPaper;
import io.github.citrushappy.util.Reference;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityList {

    public static void init()
    {
        int id = 1;
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID, "crewmate"), EntityCrewMate.class, "crewmate", id++, CitrusThings.instance, 30, 1, true,  16731716, 16777215);
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID, "bakamitai_creeper"), EntityBakaMitaiCreeper.class, "bakamitai_creeper", id++, CitrusThings.instance, 30, 1, true,  60474, 18490);
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID, "imposter"), EntityBakaMitaiCreeper.class, "imposter", id++, CitrusThings.instance, 30, 1, true,  16731716, 6731716);
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID, "obama_head"), EntityObamaHead.class, "obama_head", id++, CitrusThings.instance, 30, 1, true, 60474, 18490);
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID, "bacteria"), EntityBacteria.class, "bacteria", id++, CitrusThings.instance, 60, 1, true,  1248522, 0);
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID, "paper"), EntityPaper.class, "paper", id++, CitrusThings.instance, 30, 1, true,  11111111, 0);


        EntityRegistry.addSpawn(EntityCrewMate.class, 10, 8,8, EnumCreatureType.CREATURE, Biomes.HELL, Biomes.PLAINS, Biomes.FOREST);
        EntityRegistry.addSpawn(EntityBakaMitaiCreeper.class, 10, 1,2,EnumCreatureType.MONSTER, Biomes.PLAINS);
    }

    @SideOnly(Side.CLIENT)
    public static void initModels() {
        RenderingRegistry.registerEntityRenderingHandler(EntityCrewMate.class, RendererCrewMate::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityImposter.class, RendererImposter::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityBakaMitaiCreeper.class, RendererBakaMitaiCreeper::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityBacteria.class, RendererBacteria::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityPaper.class, RendererPaper::new);
        //RenderingRegistry.registerEntityRenderingHandler(EntityObamaHead.class, RendererBakaMitaiCreeper::new);
    }
}
