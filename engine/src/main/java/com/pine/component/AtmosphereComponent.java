package com.pine.component;

import com.pine.PBean;
import com.pine.inspection.MutableField;
import com.pine.type.AtmosphereType;
import org.joml.Vector3f;

import java.util.Set;

@PBean
public class AtmosphereComponent extends AbstractComponent<AtmosphereComponent> {

    @MutableField(label = "Time of day")
    public float elapsedTime = 0;
    @MutableField(label = "Max samples", max = 20, min = 1, isDirectChange = false, isAngle = false)
    public int maxSamples = 10;
    @MutableField(label = "Mie height")
    public int mieHeight = 1000;
    @MutableField(label = "Rayleigh Height")
    public int rayleighHeight = 8000;
    @MutableField(label = "Atmosphere Radius")
    public float atmosphereRadius = 1;
    @MutableField(label = "Planet Radius")
    public float planetRadius = 1;
    @MutableField(label = "Intensity", max = 20, min = 1, isDirectChange = false, isAngle = false)
    public float intensity = 10;
    @MutableField(label = "Rendering Type")
    public AtmosphereType renderingType = AtmosphereType.COMBINED;
    @MutableField(label = "Beta Rayleigh")
    public final Vector3f betaRayleigh = new Vector3f(1);
    @MutableField(label = "Beta Mie")
    public final Vector3f betaMie = new Vector3f(1);
    @MutableField(label = "Threshold", max = 10, min = -1, isDirectChange = false, isAngle = false)
    public float threshold = 0;

    public AtmosphereComponent() {
        super();
    }

    public AtmosphereComponent(Integer entityId) {
        super(entityId);
    }

    @Override
    protected Set<Class<? extends EntityComponent>> getDependenciesInternal() {
        return Set.of();
    }

    @Override
    public String getComponentName() {
        return "Atmosphere";
    }
}
