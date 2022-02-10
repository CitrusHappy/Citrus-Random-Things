package io.github.citrushappy.client.renderer.armor;

import io.github.citrushappy.client.model.armor.ModelDrip;
import io.github.citrushappy.items.ItemDrip;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class RendererDrip extends GeoArmorRenderer<ItemDrip>
{
    public RendererDrip()
    {
        super(new ModelDrip());

        //These values are what each bone name is in blockbench. So if your head bone is named "bone545", make sure to do this.headBone = "bone545";
        // The default values are the ones that come with the default armor template in the geckolib blockbench plugin.
    }
}
