package com.pine.app.editor.panels.files;

import com.pine.app.ProjectService;
import com.pine.app.core.ui.panel.AbstractWindowPanel;
import com.pine.common.Inject;
import com.pine.common.fs.FSService;

public class FilesPanel extends AbstractWindowPanel {
    @Inject
    public FSService service;

    @Inject
    public ProjectService projectService;

    public FilesPanel() {
        super();
        setContext(new FilesContext(projectService.getCurrentProject().getPath()));
    }

    @Override
    protected String getDefinition() {
        return """
                <group>
                    <fragment id='filesHeader'/>
                    <inline id='filesContainer'/>
                </group>
                """;
    }

    @Override
    public void onInitialize() {
        super.onInitialize();

        getContext().subscribe(this::refreshFiles);

        var container = document.getElementById("filesContainer");
        document.getElementById("filesHeader").appendChild(new FilesHeaderPanel());
        container.appendChild(new FilesDirectoryPanel());
    }

    @Override
    protected String getTitle() {
        return "Files";
    }

    private void refreshFiles() {
        final FilesContext context = (FilesContext) getContext();
        final String directory = (context).getDirectory();
        service.refreshFiles(directory, () -> {
            context.setFiles(service.readFiles(directory));
        });
    }
}
