package com.dvsnier.tool.common;


import com.dvsnier.tool.common.callback.IToolCallback;

/**
 * BaseCommonResourceObject
 * Created by dovsnier on 2020/4/26.
 */
public class ToolCommonResourceObject {

    private static final ToolCommonResourceObject INSTANCE = new ToolCommonResourceObject();

    public static ToolCommonResourceObject getInstance() {
        return INSTANCE;
    }

    private ToolCommonResourceObject() {
    }

    protected IToolCallback toolCallback;

    public IToolCallback getToolCallback() {
        return toolCallback;
    }

    public void setToolCallback(IToolCallback toolCallback) {
        this.toolCallback = toolCallback;
    }
}
