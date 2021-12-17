package com.dvsnier.test.widget.common;


import com.dvsnier.test.widget.common.callback.IWidgetCallback;

/**
 * WidgetCommonResourceObject
 * Created by dovsnier on 2020/4/26.
 */
public class WidgetCommonResourceObject {

    private static final WidgetCommonResourceObject INSTANCE = new WidgetCommonResourceObject();

    public static WidgetCommonResourceObject getInstance() {
        return INSTANCE;
    }

    private WidgetCommonResourceObject() {
    }

    protected IWidgetCallback widgetCallback;

    public IWidgetCallback getWidgetCallback() {
        return widgetCallback;
    }

    public void setWidgetCallback(IWidgetCallback widgetCallback) {
        this.widgetCallback = widgetCallback;
    }
}
