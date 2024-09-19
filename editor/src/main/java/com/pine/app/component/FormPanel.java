package com.pine.app.component;

import com.pine.app.component.impl.*;
import com.pine.component.WithMutableData;
import com.pine.inspection.FieldDTO;
import com.pine.ui.panel.AbstractPanel;

import java.util.function.BiConsumer;

public class FormPanel extends AbstractPanel {
    private final BiConsumer<FieldDTO, Object> changeHandler;
    private final WithMutableData data;

    public FormPanel(WithMutableData data, BiConsumer<FieldDTO, Object> changeHandler) {
        this.data = data;
        this.changeHandler = changeHandler;
    }

    @Override
    public void onInitialize() {
        for (FieldDTO field : data.getFieldsAnnotated()) {
            switch (field.getType()) {
                case STRING: {
                    appendChild(new StringField(field, changeHandler));
                    break;
                }
                case LONG:
                case INT: {
                    appendChild(new IntField(field, changeHandler));
                    break;
                }
                case DOUBLE:
                case FLOAT: {
                    appendChild(new FloatField(field, changeHandler));
                    break;
                }
                case BOOLEAN: {
                    appendChild(new BooleanField(field, changeHandler));
                    break;
                }
                case VECTOR2:
                case VECTOR3:
                case VECTOR4:
                case QUATERNION: {
                    appendChild(new VectorField(field, changeHandler));
                    break;
                }
            }
        }
    }
}
