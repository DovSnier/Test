package com.dvsnier.test.provider.common;


import com.dvsnier.test.provider.common.callback.IProviderCallback;

/**
 * ProviderCommonResourceObject
 * Created by dovsnier on 2020/4/26.
 */
public class ProviderCommonResourceObject {

    private static final ProviderCommonResourceObject INSTANCE = new ProviderCommonResourceObject();

    public static ProviderCommonResourceObject getInstance() {
        return INSTANCE;
    }

    private ProviderCommonResourceObject() {
    }

    protected IProviderCallback providerCallback;

    public IProviderCallback getProviderCallback() {
        return providerCallback;
    }

    public void setProviderCallback(IProviderCallback providerCallback) {
        this.providerCallback = providerCallback;
    }
}
