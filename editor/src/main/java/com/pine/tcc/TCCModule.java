package com.pine.tcc;

import com.pine.injection.EngineExternalModule;
import com.pine.service.system.AbstractSystem;
import com.pine.service.system.impl.FrameCompositionSystem;
import com.pine.tools.repository.ToolsResourceRepository;
import com.pine.tools.system.CullingVisualizationSystem;
import com.pine.tools.system.DebugSystem;
import com.pine.tools.system.GridSystem;

import java.util.ArrayList;
import java.util.List;

public class TCCModule implements EngineExternalModule {

    @Override
    public List<AbstractSystem> getExternalSystems(List<AbstractSystem> systems) {
        ArrayList<AbstractSystem> withTools = new ArrayList<>(systems);
        withTools.add(new CurveRenderingSystem());
        return withTools;
    }
}
