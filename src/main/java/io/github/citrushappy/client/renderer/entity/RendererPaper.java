package io.github.citrushappy.client.renderer.entity;

import io.github.citrushappy.client.model.entity.ModelPaper;
import io.github.citrushappy.entity.passive.EntityPaper;
import io.github.citrushappy.util.Reference;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.Nullable;

public class RendererPaper extends RenderLiving<EntityPaper> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MOD_ID, "textures/entity/paper.png");

    public RendererPaper(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelPaper(), 0.5F);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityPaper entity) {
        return RendererPaper.TEXTURE;
    }
}
