package com.pine.component;

import java.util.Set;
import java.util.Vector;

public interface EntityComponent {
    int getEntityId();

    Set<Class<? extends EntityComponent>> getDependencies();

    String getComponentName();

    void addComponent(EntityComponent instance);
}
