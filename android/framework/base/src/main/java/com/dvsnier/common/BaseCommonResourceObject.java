package com.dvsnier.common;

import com.dvsnier.common.callback.IBaseCallback;

/**
 * BaseCommonResourceObject
 * Created by dovsnier on 2020/4/26.
 */
public class BaseCommonResourceObject {

    private static final BaseCommonResourceObject INSTANCE = new BaseCommonResourceObject();

    public static BaseCommonResourceObject getInstance() {
        return INSTANCE;
    }

    private BaseCommonResourceObject() {
    }

    protected IBaseCallback baseCallback;

    public IBaseCallback getBaseCallback() {
        return baseCallback;
    }

    public void setBaseCallback(IBaseCallback baseCallback) {
        this.baseCallback = baseCallback;
    }
}
