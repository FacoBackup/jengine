package com.pine.app.core.ui.view;

import com.pine.app.core.ui.View;
import com.pine.app.core.ui.panel.AbstractPanel;
import imgui.ImGui;

public class TextView extends AbstractView{
    public TextView(View parent, String id) {
        super(parent, id);
    }

    @Override
    public void render() {
        ImGui.text(getInnerText());
    }
}
