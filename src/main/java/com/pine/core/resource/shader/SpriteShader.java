package com.pine.core.resource.shader;


public class SpriteShader extends AbstractShader {

    @Override
    public void compile() throws RuntimeException {
        compile("shaders/SPRITE.vert", "shaders/SPRITE.frag");
    }
}