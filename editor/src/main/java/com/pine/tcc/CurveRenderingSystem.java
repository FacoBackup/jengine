package com.pine.tcc;

import com.pine.FSUtil;
import com.pine.component.Entity;
import com.pine.component.InstancedPrimitiveComponent;
import com.pine.component.ResourceRef;
import com.pine.component.Transformation;
import com.pine.injection.PInject;
import com.pine.repository.WorldRepository;
import com.pine.service.RequestProcessingService;
import com.pine.service.request.AddEntityRequest;
import com.pine.service.resource.fbo.FrameBufferObject;
import com.pine.service.resource.resource.IResource;
import com.pine.service.resource.shader.Shader;
import com.pine.service.resource.shader.ShaderCreationData;
import com.pine.service.system.AbstractSystem;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.File;
import java.util.List;

public class CurveRenderingSystem extends AbstractSystem {
    private float[] samples;

    @PInject
    public RequestProcessingService requestProcessingService;

    @PInject
    public WorldRepository world;

    private float[] extractSamples(byte[] audioBytes, AudioFormat format) {
        int sampleSizeInBytes = format.getSampleSizeInBits() / 8;
        int totalSamples = audioBytes.length / sampleSizeInBytes;
        float[] samples = new float[totalSamples];

        for (int i = 0; i < totalSamples; i++) {
            int sample = 0;
            // Assuming the sample size is 16 bits (2 bytes)
            sample = (audioBytes[i * 2 + 1] << 8) | (audioBytes[i * 2] & 0xff); // Little Endian

            // Convert the sample to a float between -1.0 and 1.0
            samples[i] = sample / 32768f;
        }

        return samples;
    }

    @Override
    public void onInitialize() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("C:\\Users\\russi\\IdeaProjects\\jengine\\editor\\src\\main\\resources\\sample.wav"));
            AudioFormat format = audioInputStream.getFormat();
            byte[] audioBytes = audioInputStream.readAllBytes();
            samples = extractSamples(audioBytes, format);
            requestProcessingService.addRequest(new AddEntityRequest(List.of(InstancedPrimitiveComponent.class)));
            Entity root = world.rootEntity.transformation.children.getFirst().entity;
            InstancedPrimitiveComponent comp = (InstancedPrimitiveComponent) root.components.get(InstancedPrimitiveComponent.class.getSimpleName());
            comp.numberOfInstances = samples.length;
            comp.primitive = new ResourceRef<>(primitiveRepository.cubeMesh.getId());
            for (int i = 0; i < comp.numberOfInstances; i++) {
                Transformation transformation = new Transformation(root, true);
                comp.primitives.add(transformation);
                transformation.translation.x = i* 2;
                transformation.translation.y = samples[i] * 10e+4f;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected boolean isRenderable() {
        return false;
    }
}
