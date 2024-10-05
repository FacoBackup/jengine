package com.pine.repository.core;

import com.pine.injection.PBean;
import com.pine.injection.PInject;
import com.pine.service.resource.ResourceService;

@PBean
public class CoreComputeRepository implements CoreRepository {
    @PInject
    public ResourceService resources;


    @Override
    public void initialize() {
//        transformationCompute = (Compute) resources.addResource(new ComputeCreationData(LOCAL_SHADER + "compute/TRANSFORMATION_COMPUTE.glsl").staticResource());
    }
}
