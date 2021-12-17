package com.dvsnier.common.impl;


import android.content.Context;

import com.dvsnier.DvsnierApplication;
import com.dvsnier.common.callback.ILauncherCallback;
import com.dvsnier.test.interceptor.common.callback.IInterceptorCallback;
import com.dvsnier.test.interceptor.common.callback.IInterceptorContextCallback;

/**
 * InterceptorImpl
 * Created by dovsnier on 2020/4/26.
 */
public class InterceptorImpl implements IInterceptorCallback, ILauncherCallback, IInterceptorContextCallback {

    @Override
    public Context getApplicationContext() {
        return DvsnierApplication.getInstance();
    }
}
