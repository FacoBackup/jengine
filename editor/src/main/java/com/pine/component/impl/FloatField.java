package com.pine.component.impl;

import com.pine.component.AbstractFormField;
import com.pine.inspection.FieldDTO;
import imgui.ImGui;

import java.util.function.BiConsumer;

public class FloatField extends AbstractFormField {
    private final float[] values = new float[1];

    public FloatField(FieldDTO dto, BiConsumer<FieldDTO, Object> changerHandler) {
        super(dto, changerHandler);
        var cast = (Float) dto.getValue();
        values[0] = cast;
    }

    @Override
    public void renderInternal() {
        ImGui.text(dto.getLabel());
        if(ImGui.dragFloat(imguiId, values, .01f, dto.getMin(), dto.getMax())){
            changerHandler.accept(dto, values[0]);
        }
    }
}
