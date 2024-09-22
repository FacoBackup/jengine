package com.pine.app.component.impl;

import com.pine.app.component.AbstractFormField;
import com.pine.inspection.Color;
import com.pine.inspection.FieldDTO;
import imgui.ImGui;

import java.util.function.BiConsumer;

public class ColorField extends AbstractFormField {
    private final float[] values = new float[3];

    public ColorField(FieldDTO dto, BiConsumer<FieldDTO, Object> changerHandler) {
        super(dto, changerHandler);
        var cast = (Color) dto.getValue();
        values[0] = cast.x;
        values[1] = cast.y;
        values[2] = cast.z;
    }

    @Override
    public void renderInternal() {
        if (ImGui.colorPicker3(dto.getLabel(), values)) {
            changerHandler.accept(dto, values);
        }
    }
}
