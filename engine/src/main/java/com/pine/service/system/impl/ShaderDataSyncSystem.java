package com.pine.service.system.impl;

import com.pine.Loggable;
import com.pine.service.resource.primitives.GLSLType;
import com.pine.service.resource.shader.UniformDTO;
import com.pine.service.system.AbstractSystem;
import org.lwjgl.system.MemoryUtil;

import java.nio.IntBuffer;

public class ShaderDataSyncSystem extends AbstractSystem implements Loggable {

    private UniformDTO entityCount;
    private final IntBuffer entityCountBuffer = MemoryUtil.memAllocInt(1);

    @Override
    public void onInitialize() {
        entityCount = computeRepository.transformationCompute.addUniformDeclaration("entityCount", GLSLType.INT);
    }

    @Override
    protected void renderInternal() {
        updateUBOs();

        if (renderingRepository.infoUpdated) {
            renderingRepository.infoUpdated = false;
            renderingRepository.switchRequests();

            ssboService.updateBuffer(ssboRepository.transformationSSBO, ssboRepository.transformationSSBOState, 0);
            ssboService.updateBuffer(ssboRepository.lightMetadataSSBO, ssboRepository.lightSSBOState, 0);

            ssboService.bind(ssboRepository.transformationSSBO);
            ssboService.bind(ssboRepository.modelSSBO);

            computeService.bind(computeRepository.transformationCompute);

            entityCountBuffer.put(0, renderingRepository.requestCount);

            computeService.bindUniform(entityCount, entityCountBuffer);

            computeService.compute();
        }
    }

    private void updateUBOs() {
        uboService.updateBuffer(uboRepository.cameraViewUBO, uboRepository.cameraViewUBOState, 0);
        uboService.updateBuffer(uboRepository.cameraProjectionUBO, uboRepository.cameraProjectionUBOState, 0);
        // TODO - SIMPLE UNIFORMS, NO NEED FOR UBO SINCE THIS WILL ONLY EXECUTE ONCE PER FRAME
        uboService.updateBuffer(uboRepository.lensPostProcessingUBO, uboRepository.lensPostProcessingUBOState, 0);
        uboService.updateBuffer(uboRepository.ssaoUBO, uboRepository.ssaoUBOState, 0);
    }
}
