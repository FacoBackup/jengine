package com.pine.engine.service.primitives.audio;

import com.pine.common.resource.AbstractResource;
import com.pine.common.resource.ResourceType;

public class Audio extends AbstractResource<AudioDTO> {
    public Audio(String id) {
        super(id);
    }

    @Override
    public ResourceType getResourceType() {
        return ResourceType.AUDIO;
    }
}
