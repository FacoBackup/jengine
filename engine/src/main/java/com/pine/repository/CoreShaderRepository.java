package com.pine.repository;

import com.pine.PBean;
import com.pine.PInject;
import com.pine.service.resource.ResourceService;
import com.pine.service.resource.shader.Shader;
import com.pine.service.resource.shader.ShaderCreationData;

import static com.pine.service.resource.shader.ShaderCreationData.LOCAL_SHADER;

@PBean
public class CoreShaderRepository implements CoreRepository{
    public Shader spriteShader;
    public Shader visibilityShader;
    public Shader toScreenShader;
    public Shader downscaleShader;
    public Shader bilateralBlurShader;
    public Shader bokehShader;
    public Shader irradianceShader;
    public Shader prefilteredShader;
    public Shader ssgiShader;
    public Shader mbShader;
    public Shader ssaoShader;
    public Shader boxBlurShader;
    public Shader directShadowsShader;
    public Shader omniDirectShadowsShader;
    public Shader compositionShader;
    public Shader bloomShader;
    public Shader lensShader;
    public Shader gaussianShader;
    public Shader upSamplingShader;
    public Shader atmosphereShader;
    public Shader terrainShader;
    public Shader demoShader;

    @PInject
    public ResourceService resources;

    @Override
    public void initialize(){
        demoShader = (Shader) resources.addResource(new ShaderCreationData(LOCAL_SHADER + "DEMO.vert", LOCAL_SHADER + "DEMO.frag").staticResource());
        terrainShader = (Shader) resources.addResource(new ShaderCreationData(LOCAL_SHADER + "TERRAIN.vert", LOCAL_SHADER + "TERRAIN.frag").staticResource());
        spriteShader = (Shader) resources.addResource(new ShaderCreationData(LOCAL_SHADER + "SPRITE.vert", LOCAL_SHADER + "SPRITE.frag").staticResource());
        visibilityShader = (Shader) resources.addResource(new ShaderCreationData(LOCAL_SHADER + "V_BUFFER.vert", LOCAL_SHADER + "V_BUFFER.frag").staticResource());
        toScreenShader = (Shader) resources.addResource(new ShaderCreationData(LOCAL_SHADER + "QUAD.vert", LOCAL_SHADER + "TO_SCREEN.frag").staticResource());
        downscaleShader = (Shader) resources.addResource(new ShaderCreationData(LOCAL_SHADER + "QUAD.vert", LOCAL_SHADER + "BILINEAR_DOWNSCALE.glsl").staticResource());
        bilateralBlurShader = (Shader) resources.addResource(new ShaderCreationData(LOCAL_SHADER + "QUAD.vert", LOCAL_SHADER + "BILATERAL_BLUR.glsl").staticResource());
        bokehShader = (Shader) resources.addResource(new ShaderCreationData(LOCAL_SHADER + "QUAD.vert", LOCAL_SHADER + "BOKEH.frag").staticResource());
        irradianceShader = (Shader) resources.addResource(new ShaderCreationData(LOCAL_SHADER + "CUBEMAP.vert", LOCAL_SHADER + "IRRADIANCE_MAP.frag").staticResource());
        prefilteredShader = (Shader) resources.addResource(new ShaderCreationData(LOCAL_SHADER + "CUBEMAP.vert", LOCAL_SHADER + "PREFILTERED_MAP.frag").staticResource());
        ssgiShader = (Shader) resources.addResource(new ShaderCreationData(LOCAL_SHADER + "QUAD.vert", LOCAL_SHADER + "SSGI.frag").staticResource());
        mbShader = (Shader) resources.addResource(new ShaderCreationData(LOCAL_SHADER + "QUAD.vert", LOCAL_SHADER + "MOTION_BLUR.frag").staticResource());
        ssaoShader = (Shader) resources.addResource(new ShaderCreationData(LOCAL_SHADER + "QUAD.vert", LOCAL_SHADER + "SSAO.frag").staticResource());
        boxBlurShader = (Shader) resources.addResource(new ShaderCreationData(LOCAL_SHADER + "QUAD.vert", LOCAL_SHADER + "BOX-BLUR.frag").staticResource());
        directShadowsShader = (Shader) resources.addResource(new ShaderCreationData(LOCAL_SHADER + "SHADOWS.vert", LOCAL_SHADER + "DIRECTIONAL_SHADOWS.frag").staticResource());
        omniDirectShadowsShader = (Shader) resources.addResource(new ShaderCreationData(LOCAL_SHADER + "SHADOWS.vert", LOCAL_SHADER + "OMNIDIRECTIONAL_SHADOWS.frag").staticResource());
        compositionShader = (Shader) resources.addResource(new ShaderCreationData(LOCAL_SHADER + "QUAD.vert", LOCAL_SHADER + "FRAME_COMPOSITION.frag").staticResource());
        bloomShader = (Shader) resources.addResource(new ShaderCreationData(LOCAL_SHADER + "QUAD.vert", LOCAL_SHADER + "BRIGHTNESS_FILTER.frag").staticResource());
        lensShader = (Shader) resources.addResource(new ShaderCreationData(LOCAL_SHADER + "QUAD.vert", LOCAL_SHADER + "LENS_POST_PROCESSING.frag").staticResource());
        gaussianShader = (Shader) resources.addResource(new ShaderCreationData(LOCAL_SHADER + "QUAD.vert", LOCAL_SHADER + "GAUSSIAN.frag").staticResource());
        upSamplingShader = (Shader) resources.addResource(new ShaderCreationData(LOCAL_SHADER + "QUAD.vert", LOCAL_SHADER + "UPSAMPLE_TENT.glsl").staticResource());
        atmosphereShader = (Shader) resources.addResource(new ShaderCreationData(LOCAL_SHADER + "QUAD.vert", LOCAL_SHADER + "ATMOSPHERE.frag").staticResource());
    }
}
