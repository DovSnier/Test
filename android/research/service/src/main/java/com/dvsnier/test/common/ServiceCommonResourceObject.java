package com.dvsnier.test.common;

import com.dvsnier.test.common.callback.IServiceCallback;

/**
 * ServiceCommonResourceObject
 * Created by dovsnier on 2020/4/26.
 */
public class ServiceCommonResourceObject {

    private static final ServiceCommonResourceObject INSTANCE = new ServiceCommonResourceObject();

    public static ServiceCommonResourceObject getInstance() {
        return INSTANCE;
    }

    private ServiceCommonResourceObject() {
    }

    protected IServiceCallback serviceCallback;

    public IServiceCallback getServiceCallback() {
        return serviceCallback;
    }

    public void setServiceCallback(IServiceCallback serviceCallback) {
        this.serviceCallback = serviceCallback;
    }
}
