package com.dvsnier.test.view.common;


import com.dvsnier.test.view.common.callback.IViewCallback;

/**
 * ViewCommonResourceObject
 * Created by dovsnier on 2020/4/26.
 */
public class ViewCommonResourceObject {

    private static final ViewCommonResourceObject INSTANCE = new ViewCommonResourceObject();

    public static ViewCommonResourceObject getInstance() {
        return INSTANCE;
    }

    private ViewCommonResourceObject() {
    }

    protected IViewCallback viewCallback;

    public IViewCallback getViewCallback() {
        return viewCallback;
    }

    public void setViewCallback(IViewCallback viewCallback) {
        this.viewCallback = viewCallback;
    }
}
