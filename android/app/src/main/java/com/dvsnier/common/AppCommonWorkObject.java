package com.dvsnier.common;

import com.dvsnier.common.callback.IBaseCallback;
import com.dvsnier.common.callback.IEventCallback;
import com.dvsnier.common.callback.ILauncherCallback;
import com.dvsnier.common.impl.BaseImpl;
import com.dvsnier.common.impl.InterceptorImpl;
import com.dvsnier.test.interceptor.common.InterceptorCommonResourceObject;
import com.dvsnier.test.interceptor.common.callback.IInterceptorCallback;
import com.dvsnier.test.interceptor.common.callback.IInterceptorContextCallback;

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

    public final void setCallback() {
        setBaseCallback(new BaseImpl());
        setInterceptorCallback(new InterceptorImpl());
    }

    protected IBaseCallback baseCallback;
    protected IInterceptorCallback interceptorCallback;


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

    public IInterceptorCallback getInterceptorCallback() {
        return interceptorCallback;
    }

    public void setInterceptorCallback(IInterceptorCallback interceptorCallback) {
        this.interceptorCallback = interceptorCallback;
        if (null != interceptorCallback) {
            if (interceptorCallback instanceof ILauncherCallback) {
                // the obtain Application Context
                if (interceptorCallback instanceof IInterceptorContextCallback) {
                    InterceptorCommonResourceObject.getInstance()
                            .setContext(((IInterceptorContextCallback) interceptorCallback)
                                    .getApplicationContext());
                }
                InterceptorCommonResourceObject.getInstance().setCallback();
            }
        }
    }
}
