package com.pine.engine.core.service.resource;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;

class ShaderServiceTest {

    @Test
    void processShader() {
        ShaderService shaderService = new ShaderService(null);
        String result = shaderService.processShader("shaders" + File.separator + "TO_BE_INJECTED.frag");
        Assertions.assertTrue(result.contains("// THIS WAS HERE BEFORE"));
        Assertions.assertFalse(result.contains("#include"));
        Assertions.assertTrue(result.contains("// THIS WAS INJECTED"));
    }
}