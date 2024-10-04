package com.pine.service.streaming.audio;

import com.pine.injection.PBean;
import com.pine.repository.streaming.StreamableResourceType;
import com.pine.service.streaming.AbstractStreamableService;

@PBean
public class AudioService extends AbstractStreamableService<AudioStreamableResource> {

    @Override
    protected void bindInternal() {

    }

    @Override
    public void unbind() {

    }

    @Override
    public StreamableResourceType getResourceType() {
        return StreamableResourceType.AUDIO;
    }
}
