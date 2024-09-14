package com.pine.engine.core.service.entity;

import com.google.gson.JsonElement;
import com.pine.engine.core.service.serialization.SerializableRepository;
import com.pine.engine.core.EngineInjectable;

@EngineInjectable
public class EntityService   {
    private final EntityRepository entityRepository = new EntityRepository();

}
