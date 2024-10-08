package com.pine.component;

import com.pine.injection.PBean;
import com.pine.inspection.MutableField;
import com.pine.theme.Icons;

import java.util.LinkedList;

@PBean
public class LightProbeComponent extends AbstractComponent<LightProbeComponent> {
    @MutableField(label = "Mipmaps")
    public int mipmaps = 6;
    /**
     * Only entities within  this radius will be rendered into the probe
     */
    @MutableField(label = "Radius")
    public int collectionRadius = 50;

    public LightProbeComponent(Entity entity, LinkedList<?> bag) {
        super(entity, bag);
    }

    public LightProbeComponent() {}

    @Override
    public String getTitle() {
        return "Probe";
    }


    @Override
    public String getIcon() {
        return Icons.panorama;
    }
}
