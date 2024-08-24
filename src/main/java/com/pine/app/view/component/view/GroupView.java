package com.pine.app.view.component.view;

import com.pine.app.view.component.View;
import com.pine.app.view.component.panel.AbstractPanel;
import imgui.ImGui;
import imgui.flag.ImGuiWindowFlags;

public class GroupView extends AbstractView {
    public GroupView(View parent, String id, AbstractPanel panel) {
        super(parent, id, panel);
    }

    @Override
    public void render(long index) {
        ImGui.beginGroup();
        super.render(index);
        ImGui.endGroup();
    }
}
