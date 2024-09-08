package com.pine.app.core.ui;


import com.pine.app.core.ui.panel.AbstractPanelContext;
import com.pine.common.Renderable;

import java.util.List;

public interface View extends Renderable {

    String getInnerText();

    void setInnerText(String textContent);

    boolean isVisible();

    void setVisible(boolean visible);

    List<View> getChildren();

    String getId();

    View getParent();

    void appendChild(View child);

    ViewDocument getDocument();

    void setDocument(ViewDocument document);

    AbstractPanelContext getContext();

    void setContext(AbstractPanelContext internalContext);

    void setParent(View parent);

    void renderInternal();
}
