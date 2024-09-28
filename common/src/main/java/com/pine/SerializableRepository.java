package com.pine;

import imgui.ImVec4;
import imgui.type.ImInt;
import org.joml.Vector3f;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface SerializableRepository extends Serializable, Loggable {
    default void merge(Object data) {
        if (data.getClass() != this.getClass()) {
            getLogger().error("Classes are not compatible {} {}", data.getClass(), this.getClass());
            return;
        }

        if(SerializationState.loaded.containsKey(this)){
            return;
        }

        SerializationState.loaded.put(this, true);
        Field[] declaredFields = this.getClass().getDeclaredFields();
        final Map<String, Field> fieldMap = new HashMap<>();

        for (Field sourceField : data.getClass().getDeclaredFields()) {
            fieldMap.put(sourceField.getName(), sourceField);
        }

        for (Field declaredField : declaredFields) {
            int modifiers = declaredField.getModifiers();
            if (Modifier.isTransient(modifiers) || Modifier.isPrivate(modifiers) || Modifier.isStatic(modifiers)) {
                continue;
            }

            try {
                var value = fieldMap.get(declaredField.getName()).get(data);
                var targetValue = fieldMap.get(declaredField.getName()).get(this);

                if (declaredField.isAnnotationPresent(PInject.class)) {
                    if (List.class.isAssignableFrom(declaredField.getType())) {
                        List<?> t = (List<?>) targetValue;
                        List<?> tO = (List<?>) value;
                        t.forEach(a -> {
                            if (a instanceof SerializableRepository) {
                                ((SerializableRepository) a).merge(tO.get(t.indexOf(a)));
                            }
                        });
                    } else if (targetValue instanceof SerializableRepository) {
                        ((SerializableRepository) targetValue).merge(value);
                    }
                    continue;
                }

                mergeNormalField(declaredField, modifiers, value, targetValue);
            } catch (Exception e) {
                getLogger().error("Failed to update field {} onto {}", declaredField.getName(), this.getClass().getSimpleName(), e);
            }
        }
    }

    private void mergeNormalField(Field declaredField, int modifiers, Object value, Object targetValue) throws IllegalAccessException {
        if (Modifier.isFinal(modifiers)) {
            if (value instanceof Vector3f) {
                ((Vector3f) targetValue).set((Vector3f) value);
            } else if (value instanceof Map) {
                ((Map<?, ?>) targetValue).clear();
                ((Map<?, ?>) targetValue).putAll((Map) value);
            } else if (value instanceof ImVec4) {
                ((ImVec4) targetValue).set((ImVec4) value);
            } else if (value instanceof ImInt) {
                ((ImInt) targetValue).set((ImInt) value);
            } else if (value instanceof List) {
                List<?> t = (List<?>) targetValue;
                t.clear();
                t.addAll((List) value);
            }
        } else {
            declaredField.setAccessible(true);
            declaredField.set(this, value);
        }
    }
}
