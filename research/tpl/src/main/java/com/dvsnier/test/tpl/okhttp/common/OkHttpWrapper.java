package com.dvsnier.test.tpl.okhttp.common;

import android.content.Context;

import com.dvsnier.IWith;
import com.dvsnier.base.task.ICycle;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import okhttp3.OkHttpClient;

/**
 * OkHttpWrapper
 * Created by dovsnier on 2019/6/26.
 */
public class OkHttpWrapper implements IWith<OkHttpWrapper>, ICycle {

    private static final OkHttpWrapper INSTANCE = new OkHttpWrapper();
    protected Context context;
    protected OkHttpClient okHttpClient;

    public static OkHttpWrapper getInstance() {
        return INSTANCE;
    }

    private OkHttpWrapper() {
    }

    @Override
    public OkHttpWrapper with(Context context) {
        setContext(context);
        return this;
    }

    @Override
    public void onDestroy() {
        if (null != getContext()) {
            setContext(null);
        }
        if (null != getOkHttpClient()) {
            setOkHttpClient(null);
        }
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public static OkHttpClient getHttpClient() {
        return getInstance().getOkHttpClient();
    }

    public OkHttpClient getOkHttpClient() {
        if (null == okHttpClient) {
            synchronized (OkHttpClient.class) {
                if (null == okHttpClient) {
                    okHttpClient = new OkHttpClient().newBuilder()
                            .addNetworkInterceptor(new StethoInterceptor())
                            .build();
                }
            }
        }
        return okHttpClient;
    }

    public void setOkHttpClient(OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
    }
}
