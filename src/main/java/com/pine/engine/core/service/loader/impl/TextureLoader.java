package com.pine.engine.core.service.loader.impl;

import com.pine.engine.core.EngineInjectable;
import com.pine.engine.core.service.loader.AbstractResourceLoader;
import com.pine.engine.core.service.loader.impl.info.AbstractLoaderExtraInfo;
import com.pine.engine.core.service.loader.impl.info.LoadRequest;
import com.pine.engine.core.service.loader.impl.response.AbstractLoaderResponse;
import com.pine.engine.core.service.loader.impl.response.TextureLoaderResponse;
import com.pine.engine.core.service.resource.resource.ResourceType;
import jakarta.annotation.Nullable;

import java.util.Collections;

@EngineInjectable
public class TextureLoader extends AbstractResourceLoader {

    @Override
    public AbstractLoaderResponse load(LoadRequest resource, @Nullable AbstractLoaderExtraInfo extraInfo) {
        return new TextureLoaderResponse(false, resource.path(), Collections.emptyList());
    }

    @Override
    public ResourceType getResourceType() {
        return ResourceType.TEXTURE;
    }
}
