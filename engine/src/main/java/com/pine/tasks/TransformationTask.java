package com.pine.tasks;

import com.pine.annotation.EngineInjectable;

@EngineInjectable
public class TransformationTask extends AbstractTask {

    @Override
    protected int getTickIntervalMilliseconds() {
        return super.getTickIntervalMilliseconds(); // TODO - GET PROPERTY
    }

    @Override
    protected void tickInternal() {

    }
}
