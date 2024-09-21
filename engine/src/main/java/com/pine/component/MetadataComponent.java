package com.pine.component;

import com.pine.PBean;
import com.pine.inspection.MutableField;
import org.joml.Vector3f;

import java.util.Collections;
import java.util.Set;

@PBean
public class MetadataComponent extends AbstractComponent<MetadataComponent> {
    public final Vector3f pickerId = new Vector3f();
    @MutableField(label = "Name")
    public String name = "New Entity";
    public final long creationDate = System.currentTimeMillis();

    public MetadataComponent(Integer entityId) {
        super(entityId);
        pickerId.x = (float) ((entityId) & 0xFF) / 0xFF;
        pickerId.y = (float) ((entityId >> 8) & 0xFF) / 0xFF;
        pickerId.z = (float) ((entityId >> 16) & 0xFF) / 0xFF;
    }

    public MetadataComponent() {
        super();
    }

    @Override
    protected Set<Class<? extends EntityComponent>> getDependenciesInternal() {
        return Collections.emptySet();
    }
    
    @Override
    public String getComponentName() {
        return "Metadata";
    }
}
