package com.pine.component;

import com.pine.PBean;
import com.pine.component.rendering.CompositeScene;
import com.pine.inspection.MutableField;
import com.pine.repository.rendering.PrimitiveRenderRequest;
import com.pine.theme.Icons;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@PBean
public class SceneComponent extends AbstractComponent<SceneComponent> {
    @MutableField(label = "Scene members")
    public final CompositeScene compositeScene = new CompositeScene(getEntityId());

    public final transient List<PrimitiveRenderRequest> requests = new ArrayList<>();

    public SceneComponent(Integer entityId) {
        super(entityId);
    }

    public SceneComponent() {
        super();
    }

    @Override
    protected Set<Class<? extends EntityComponent>> getDependenciesInternal() {
        return Set.of();
    }

    @Override
    public String getTitle() {
        return "Scene";
    }

    @Override
    public String getIcon() {
        return Icons.movie_edit;
    }
}
