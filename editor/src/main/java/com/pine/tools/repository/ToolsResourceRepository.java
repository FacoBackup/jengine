package com.pine.tools.repository;

import com.pine.Initializable;
import com.pine.PInject;
import com.pine.service.resource.ResourceService;
import com.pine.service.resource.shader.ShaderResource;
import com.pine.service.resource.shader.ShaderCreationData;

public class ToolsResourceRepository implements Initializable {

    @PInject
    public ResourceService resources;

    public ShaderResource gridShader;

    @Override
    public void onInitialize() {
        gridShader = (ShaderResource) resources.addResource(new ShaderCreationData("shaders/GRID.vert", "shaders/GRID.frag", "grid"));
    }
}
