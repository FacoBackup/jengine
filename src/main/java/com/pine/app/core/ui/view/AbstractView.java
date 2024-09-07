package com.pine.app.core.ui.view;

import com.pine.app.core.Icon;
import com.pine.app.core.ui.View;
import com.pine.app.core.ui.ViewDocument;
import com.pine.app.core.ui.panel.AbstractPanelContext;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AbstractView implements View {
    protected ViewDocument document;
    protected final String internalId;
    protected final String id;
    protected final List<View> children = new ArrayList<>();
    protected View parent;
    protected String innerText;
    protected boolean visible = true;
    private AbstractPanelContext internalContext;

    public AbstractView(View parent, String id) {
        this.parent = parent;
        this.id = id;
        this.internalId = "##" + UUID.randomUUID().toString().replaceAll("-", "");
    }

    @Override
    public ViewDocument getDocument() {
        return document;
    }

    @Override
    public AbstractPanelContext getContext() {
        return internalContext;
    }

    @Override
    public void setContext(AbstractPanelContext internalContext) {
        this.internalContext = internalContext;
    }

    @Override
    public void setDocument(ViewDocument document) {
        if (this.document != null) {
            throw new RuntimeException("Document already bound to view");
        }
        this.document = document;
    }

    @Override
    public List<View> getChildren() {
        return children;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public View getParent() {
        return parent;
    }

    @Override
    public void appendChild(View child) {
        document.appendChild(child, this);
    }


    @Override
    public String getInnerText() {
        return innerText;
    }

    @Override
    public boolean isVisible() {
        return this.visible;
    }

    @Override
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    protected void renderInternal() {
        for (View child : children) {
            child.render();
        }
    }

    @Override
    public void setParent(View parent) {
        this.parent = parent;
    }

    @Override
    public void tick() {
    }

    @Override
    final public void render() {
        if (!visible) {
            return;
        }

        tick();
        renderInternal();
    }

    @Override
    public void onInitialize() {
        setInnerText(innerText);
    }

    @Override
    public void setInnerText(String textContent) {
        innerText = textContent;
        processIcons();
    }

    private void processIcons() {
        if (innerText != null) {
            for (var icon : Icon.values()) {
                innerText = innerText.replace("[" + icon.getIconName() + "]", icon.codePoint);
            }
        }
    }
}

