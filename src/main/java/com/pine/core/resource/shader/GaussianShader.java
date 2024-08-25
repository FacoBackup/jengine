package com.pine.core.resource.shader;


public class GaussianShader extends AbstractShader {

    @Override
    public void compile() throws RuntimeException {
        compile("shaders/QUAD.vert", "shaders/GAUSSIAN.frag");
    }
}
