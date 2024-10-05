package com.pine.tools.system;

import com.pine.injection.PInject;
import com.pine.repository.SettingsRepository;
import com.pine.repository.rendering.PrimitiveRenderRequest;
import com.pine.service.resource.fbo.FrameBufferObject;
import com.pine.service.resource.primitives.GLSLType;
import com.pine.service.resource.shader.UniformDTO;
import com.pine.service.system.AbstractSystem;
import com.pine.tools.repository.ToolsResourceRepository;
import org.lwjgl.opengl.GL46;
import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class DebugSystem extends AbstractSystem {

    @PInject
    public SettingsRepository editorSettings;

    private UniformDTO transformationIndex;
    private UniformDTO lightCount;
    private UniformDTO elapsedTime;
    private UniformDTO isDecalPass;
    private UniformDTO shadowMapsQuantity;
    private UniformDTO shadowMapResolution;
    private UniformDTO SSRFalloff;
    private UniformDTO stepSizeSSR;
    private UniformDTO maxSSSDistance;
    private UniformDTO SSSDepthThickness;
    private UniformDTO SSSEdgeAttenuation;
    private UniformDTO SSSDepthDelta;
    private UniformDTO SSAOFalloff;
    private UniformDTO maxStepsSSR;
    private UniformDTO maxStepsSSS;
    private UniformDTO hasAmbientOcclusion;
    private UniformDTO shadingModel;
    private UniformDTO brdfSampler;
    private UniformDTO SSAO;
    private UniformDTO SSGI;
    private UniformDTO previousFrame;
    private UniformDTO shadowAtlas;
    private UniformDTO shadowCube;
    private UniformDTO ssrEnabled;
    private UniformDTO renderingMode;
    private UniformDTO anisotropicRotation;
    private UniformDTO anisotropy;
    private UniformDTO clearCoat;
    private UniformDTO sheen;
    private UniformDTO sheenTint;
    private UniformDTO useAlbedoDecal;
    private UniformDTO useMetallicDecal;
    private UniformDTO useRoughnessDecal;
    private UniformDTO useNormalDecal;
    private UniformDTO useOcclusionDecal;

    private final IntBuffer intBoolBuffer = MemoryUtil.memAllocInt(1);
    private final FloatBuffer floatBuffer = MemoryUtil.memAllocFloat(1);

    @PInject
    public ToolsResourceRepository toolsResourceRepository;

    @Override
    protected FrameBufferObject getTargetFBO() {
        return fboRepository.tempColorWithDepth;
    }

    @Override
    public void onInitialize() {
        GL46.glClear(GL46.GL_DEPTH_BUFFER_BIT);
        transformationIndex = toolsResourceRepository.debugShader.addUniformDeclaration("transformationIndex", GLSLType.INT);
        lightCount = toolsResourceRepository.debugShader.addUniformDeclaration("lightCount", GLSLType.INT);
        elapsedTime = toolsResourceRepository.debugShader.addUniformDeclaration("elapsedTime", GLSLType.FLOAT);
        isDecalPass = toolsResourceRepository.debugShader.addUniformDeclaration("isDecalPass", GLSLType.BOOL);
        shadowMapsQuantity = toolsResourceRepository.debugShader.addUniformDeclaration("shadowMapsQuantity", GLSLType.FLOAT);
        shadowMapResolution = toolsResourceRepository.debugShader.addUniformDeclaration("shadowMapResolution", GLSLType.FLOAT);
        SSRFalloff = toolsResourceRepository.debugShader.addUniformDeclaration("SSRFalloff", GLSLType.FLOAT);
        stepSizeSSR = toolsResourceRepository.debugShader.addUniformDeclaration("stepSizeSSR", GLSLType.FLOAT);
        maxSSSDistance = toolsResourceRepository.debugShader.addUniformDeclaration("maxSSSDistance", GLSLType.FLOAT);
        SSSDepthThickness = toolsResourceRepository.debugShader.addUniformDeclaration("SSSDepthThickness", GLSLType.FLOAT);
        SSSEdgeAttenuation = toolsResourceRepository.debugShader.addUniformDeclaration("SSSEdgeAttenuation", GLSLType.FLOAT);
        SSSDepthDelta = toolsResourceRepository.debugShader.addUniformDeclaration("SSSDepthDelta", GLSLType.FLOAT);
        SSAOFalloff = toolsResourceRepository.debugShader.addUniformDeclaration("SSAOFalloff", GLSLType.FLOAT);
        maxStepsSSR = toolsResourceRepository.debugShader.addUniformDeclaration("maxStepsSSR", GLSLType.INT);
        maxStepsSSS = toolsResourceRepository.debugShader.addUniformDeclaration("maxStepsSSS", GLSLType.INT);
        hasAmbientOcclusion = toolsResourceRepository.debugShader.addUniformDeclaration("hasAmbientOcclusion", GLSLType.BOOL);
        shadingModel = toolsResourceRepository.debugShader.addUniformDeclaration("shadingModel", GLSLType.INT);
        brdfSampler = toolsResourceRepository.debugShader.addUniformDeclaration("brdfSampler", GLSLType.SAMPLER_2_D);
        SSAO = toolsResourceRepository.debugShader.addUniformDeclaration("SSAO", GLSLType.SAMPLER_2_D);
        SSGI = toolsResourceRepository.debugShader.addUniformDeclaration("SSGI", GLSLType.SAMPLER_2_D);
        previousFrame = toolsResourceRepository.debugShader.addUniformDeclaration("previousFrame", GLSLType.SAMPLER_2_D);
        shadowAtlas = toolsResourceRepository.debugShader.addUniformDeclaration("shadowAtlas", GLSLType.SAMPLER_2_D);
        shadowCube = toolsResourceRepository.debugShader.addUniformDeclaration("shadowCube", GLSLType.SAMPLER_CUBE);
        ssrEnabled = toolsResourceRepository.debugShader.addUniformDeclaration("ssrEnabled", GLSLType.BOOL);
        renderingMode = toolsResourceRepository.debugShader.addUniformDeclaration("renderingMode", GLSLType.INT);
        anisotropicRotation = toolsResourceRepository.debugShader.addUniformDeclaration("anisotropicRotation", GLSLType.FLOAT);
        anisotropy = toolsResourceRepository.debugShader.addUniformDeclaration("anisotropy", GLSLType.FLOAT);
        clearCoat = toolsResourceRepository.debugShader.addUniformDeclaration("clearCoat", GLSLType.FLOAT);
        sheen = toolsResourceRepository.debugShader.addUniformDeclaration("sheen", GLSLType.FLOAT);
        sheenTint = toolsResourceRepository.debugShader.addUniformDeclaration("sheenTint", GLSLType.FLOAT);
        useAlbedoDecal = toolsResourceRepository.debugShader.addUniformDeclaration("useAlbedoDecal", GLSLType.BOOL);
        useMetallicDecal = toolsResourceRepository.debugShader.addUniformDeclaration("useMetallicDecal", GLSLType.BOOL);
        useRoughnessDecal = toolsResourceRepository.debugShader.addUniformDeclaration("useRoughnessDecal", GLSLType.BOOL);
        useNormalDecal = toolsResourceRepository.debugShader.addUniformDeclaration("useNormalDecal", GLSLType.BOOL);
        useOcclusionDecal = toolsResourceRepository.debugShader.addUniformDeclaration("useOcclusionDecal", GLSLType.BOOL);
    }

    @Override
    protected void renderInternal() {
        ssboService.bind(ssboRepository.transformationSSBO);
        ssboService.bind(ssboRepository.lightMetadataSSBO);
        shaderService.bind(toolsResourceRepository.debugShader);

        var requests = renderingRepository.requests;
        for (PrimitiveRenderRequest request : requests) {
            intBoolBuffer.put(0, request.transformation.renderIndex);
            shaderService.bindUniform(transformationIndex, intBoolBuffer);
            primitiveService.bind(request.mesh, request.runtimeData);
        }
    }
}
