package com.pine.dock;

import com.pine.view.AbstractView;
import imgui.ImVec2;
import org.joml.Vector2f;

public abstract class AbstractDockPanel extends AbstractView {
    public ImVec2 position;
    public Vector2f size;

    public void setSize(Vector2f size) {
        this.size = size;
    }

    public void setPosition(ImVec2 position) {
        this.position = position;
    }
}
