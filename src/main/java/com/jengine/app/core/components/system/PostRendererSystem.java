package com.jengine.app.core.components.system;

import com.artemis.systems.IteratingSystem;
import org.springframework.stereotype.Component;
import com.artemis.annotations.All;

@Component
@All
public class PostRendererSystem extends IteratingSystem {
    @Override
    protected void process(int id) {
    }
}
