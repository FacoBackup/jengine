package com.pine.app.view.projects.panel;

import com.pine.app.view.component.panel.AbstractPanel;
import com.pine.app.view.component.view.ButtonView;
import com.pine.app.view.component.view.InputView;
import com.pine.app.view.projects.ProjectDTO;

import java.util.function.Consumer;

public class ProjectRowPanel extends AbstractPanel {
    private final ProjectDTO dto;
    private final Consumer<ProjectDTO> openProject;
    private final Consumer<ProjectDTO> removeProject;
    private InputView name;

    public ProjectRowPanel(ProjectDTO dto, Consumer<ProjectDTO> openProject, Consumer<ProjectDTO> removeProject) {
        this.dto = dto;
        this.openProject = openProject;
        this.removeProject = removeProject;
    }

    @Override
    public void onInitialize() {
        super.onInitialize();
        var edit = (ButtonView) getElementById("edit");
        var remove = (ButtonView) getElementById("delete");
        var open = (ButtonView) getElementById("open");
        name = (InputView) getElementById("name");

        name.setOnChange(dto::setName);
        name.setState(dto.getName());

        edit.setOnClick(() -> name.setEnabled(!name.isEnabled()));
        remove.setOnClick(() -> removeProject.accept(dto));
        open.setOnClick(() -> openProject.accept(dto));
    }
}
