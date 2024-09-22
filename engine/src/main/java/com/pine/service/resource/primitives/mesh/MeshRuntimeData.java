package com.pine.service.resource.primitives.mesh;

import com.pine.service.resource.resource.IResourceRuntimeData;

public final class MeshRuntimeData implements IResourceRuntimeData {
    public MeshRenderingMode mode;
    public Integer instanceCount;

    public MeshRuntimeData(MeshRenderingMode mode) {
        this(mode, 0);
    }

    public MeshRuntimeData(MeshRenderingMode mode, Integer instanceCount) {
        this.mode = mode;
        this.instanceCount = instanceCount;
    }
}
