package com.dvsnier.common;

import com.dvsnier.common.callback.IBaseCallback;
import com.dvsnier.common.callback.IEventCallback;

/**
 * AppCommonWorkObject
 * Created by dovsnier on 2020/4/26.
 */
public class AppCommonWorkObject {

    private static final AppCommonWorkObject INSTANCE = new AppCommonWorkObject();

    public static AppCommonWorkObject getInstance() {
        return INSTANCE;
    }

    private AppCommonWorkObject() {
    }

    protected IBaseCallback baseCallback;

    public IBaseCallback getBaseCallback() {
        return baseCallback;
    }

    public void setBaseCallback(IBaseCallback baseCallback) {
        this.baseCallback = baseCallback;
        if (null != baseCallback) {
            if (baseCallback instanceof IEventCallback) {
                BaseCommonResourceObject.getInstance().setBaseCallback(baseCallback);
            }
        }
    }
}
