package com.pine.component.impl;

import com.pine.component.AbstractFormField;
import com.pine.injection.PInject;
import com.pine.inspection.FieldDTO;
import com.pine.repository.streaming.*;
import com.pine.theme.Icons;
import imgui.ImGui;
import imgui.type.ImInt;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;

public class ResourceField extends AbstractFormField {

    @PInject
    public StreamingRepository repository;

    private final StreamableResourceType type;
    private final ImInt selected = new ImInt(-1);
    private final List<AbstractStreamableResource<?>> items = new ArrayList<>();
    private String[] itemsArr = new String[0];

    public ResourceField(FieldDTO dto, BiConsumer<FieldDTO, Object> changerHandler) {
        super(dto, changerHandler);

        if (dto.getField().getType() == MeshStreamableResource.class) {
            type = StreamableResourceType.MESH;
        } else if (dto.getField().getType() == TextureStreamableResource.class) {
            type = StreamableResourceType.TEXTURE;
        } else if (dto.getField().getType() == AudioStreamableResource.class) {
            type = StreamableResourceType.AUDIO;
        } else {
            type = null;
        }
    }

    @Override
    public void onInitialize() {
        refresh();
        AbstractStreamableResource<?> value = (AbstractStreamableResource<?>) dto.getValue();

        if (value != null) {
            for (int i = 0; i < items.size(); i++) {
                var item = items.get(i);
                if (Objects.equals(item.id, value.id)) {
                    selected.set(i);
                }
            }
        }
    }

    private void refresh() {
        items.clear();
        for (var history : repository.streamableResources) {
            if (type == null || type == history.getResourceType()) {
                items.add(history);
            }
        }
        itemsArr = new String[items.size()];
        for (int i = 0; i < items.size(); i++) {
            var r = items.get(i);
            itemsArr[i] = r.name;
        }
    }

    @Override
    public void renderInternal() {
        ImGui.text(dto.getLabel());
        if (ImGui.combo(imguiId, selected, itemsArr)) {
            changerHandler.accept(dto, items.get(selected.get()));
        }

        ImGui.sameLine();
        if (ImGui.button(Icons.remove + "Remove" + imguiId)) {
            changerHandler.accept(dto, null);
        }

        if (ImGui.button("Refresh" + imguiId + "1")) {
            refresh();
        }
    }
}
