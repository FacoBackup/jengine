package com.pine.engine;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.pine.app.core.service.WindowService;
import com.pine.engine.core.components.component.MeshComponent;
import com.pine.engine.core.components.component.TransformationComponent;
import com.pine.engine.core.service.WorldService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Objects;

import static com.pine.common.serialization.SerializableRepository.CLASS_KEY;
import static com.pine.common.serialization.SerializableRepository.DATA_KEY;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class EngineTest {
    private static String serialized;

    private Engine engine;

    @BeforeEach
    void setUpBefore() {
        engine = new Engine();
        int entity = engine.getWorld().addEntity();
        engine.getWorld().addComponent(entity, MeshComponent.class);

        int entity2 = engine.getWorld().addEntity();
        engine.getWorld().addComponent(entity2, TransformationComponent.class);

        serialized = engine.serialize();
    }

    @Test
    @Order(1)
    void serializeAll() {
        testDump(2, serialized);
    }

    void testDump(int expectedSize, String dump) {
        JsonObject element = new Gson().fromJson(dump, JsonObject.class);
        assertNotNull(element);
        assertFalse(element.isEmpty());
        JsonObject serviceData = element.getAsJsonObject();
        if (Objects.equals(serviceData.get(CLASS_KEY).getAsString(), WorldService.class.getName())) {
            JsonArray entitiesArray = serviceData.get(DATA_KEY).getAsJsonArray();
            assertEquals(expectedSize, entitiesArray.size());
        }
    }

    @Test
    @Order(2)
    void parseAll() {
        engine.getWorld().getWorld().delete(0);
        engine.getWorld().getWorld().delete(1);
        engine.getWorld().getWorld().process();
        testDump(0, engine.serialize());
        engine.parse(serialized);
        testDump(2, engine.serialize());
    }
}