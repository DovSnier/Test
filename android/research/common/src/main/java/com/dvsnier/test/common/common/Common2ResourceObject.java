package com.dvsnier.test.common.common;

import com.dvsnier.common.callback.IBaseCallback;

/**
 * Common2ResourceObject
 * Created by dovsnier on 2020/4/26.
 */
public class Common2ResourceObject {

    private static final Common2ResourceObject INSTANCE = new Common2ResourceObject();

    public static Common2ResourceObject getInstance() {
        return INSTANCE;
    }

    private Common2ResourceObject() {
    }

    protected IBaseCallback baseCallback;

    public IBaseCallback getBaseCallback() {
        return baseCallback;
    }

    public void setBaseCallback(IBaseCallback baseCallback) {
        this.baseCallback = baseCallback;
    }
}
