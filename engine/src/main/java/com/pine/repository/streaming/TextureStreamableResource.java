package com.pine.repository.streaming;

import com.pine.service.streaming.texture.TextureStreamData;

public class TextureStreamableResource extends AbstractStreamableResource<TextureStreamData> {

    public TextureStreamableResource(String pathToFile, String id) {
        super(pathToFile, id);
    }

    @Override
    public StreamableResourceType getResourceType() {
        return StreamableResourceType.TEXTURE;
    }

    @Override
    protected void loadInternal(TextureStreamData data) {

    }

    @Override
    protected void disposeInternal() {

    }
}
