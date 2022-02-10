package io.github.citrushappy.client.renderer.item;

import io.github.citrushappy.client.model.item.ModelTattletail;
import io.github.citrushappy.items.ItemTattletail;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class RendererTattletail extends GeoItemRenderer<ItemTattletail> {

    public RendererTattletail() {
        super(new ModelTattletail());
    }
}
