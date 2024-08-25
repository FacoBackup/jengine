package com.pine.core.resource.shader;


public class BloomShader extends AbstractShader {

    @Override
    public void compile() throws RuntimeException {
        compile("shaders/QUAD.vert", "shaders/BRIGHTNESS_FILTER.frag");
    }
}
