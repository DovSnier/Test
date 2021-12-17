package com.dvsnier.test.interceptor.common;

import android.content.Context;

import com.dvsnier.common.TplCommonResourceObject;
import com.dvsnier.common.callback.ITplCallback;
import com.dvsnier.common.callback.ITplDefaultCallback;
import com.dvsnier.common.callback.ITplSpeechCallback;
import com.dvsnier.test.common.callback.IServiceCallback;
import com.dvsnier.test.common.common.callback.ICommonCallback;
import com.dvsnier.test.interceptor.common.callback.IInterceptorCallback;
import com.dvsnier.test.interceptor.common.impl.CommonImpl;
import com.dvsnier.test.interceptor.common.impl.ProviderImpl;
import com.dvsnier.test.interceptor.common.impl.ServiceImpl;
import com.dvsnier.test.interceptor.common.impl.ToolImpl;
import com.dvsnier.test.interceptor.common.impl.TplImpl;
import com.dvsnier.test.interceptor.common.impl.ViewImpl;
import com.dvsnier.test.interceptor.common.impl.WidgetImpl;
import com.dvsnier.test.provider.common.callback.IProviderCallback;
import com.dvsnier.test.view.common.callback.IViewCallback;
import com.dvsnier.test.widget.common.callback.IWidgetCallback;
import com.dvsnier.tool.common.callback.IToolCallback;

/**
 * InterceptorCommonResourceObject
 * Created by dovsnier on 2020/4/26.
 */
public class InterceptorCommonResourceObject {

    private static final InterceptorCommonResourceObject INSTANCE = new InterceptorCommonResourceObject();

    public static InterceptorCommonResourceObject getInstance() {
        return INSTANCE;
    }

    private InterceptorCommonResourceObject() {
    }

    public final void setCallback() {
        setCommonCallback(new CommonImpl());
        setProviderCallback(new ProviderImpl());
        setServiceCallback(new ServiceImpl());
        setToolCallback(new ToolImpl());
        setTplCallback(new TplImpl());
        setViewCallback(new ViewImpl());
        setWidgetCallback(new WidgetImpl());
    }

    protected Context context;
    protected ICommonCallback commonCallback;
    protected IProviderCallback providerCallback;
    protected IServiceCallback serviceCallback;
    protected IToolCallback toolCallback;
    protected ITplCallback tplCallback;
    protected IViewCallback viewCallback;
    protected IWidgetCallback widgetCallback;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    protected IInterceptorCallback interceptorCallback;

    public IInterceptorCallback getInterceptorCallback() {
        return interceptorCallback;
    }

    public void setInterceptorCallback(IInterceptorCallback interceptorCallback) {
        this.interceptorCallback = interceptorCallback;
    }

    public ICommonCallback getCommonCallback() {
        return commonCallback;
    }

    public void setCommonCallback(ICommonCallback commonCallback) {
        this.commonCallback = commonCallback;
    }

    public IProviderCallback getProviderCallback() {
        return providerCallback;
    }

    public void setProviderCallback(IProviderCallback providerCallback) {
        this.providerCallback = providerCallback;
    }

    public IServiceCallback getServiceCallback() {
        return serviceCallback;
    }

    public void setServiceCallback(IServiceCallback serviceCallback) {
        this.serviceCallback = serviceCallback;
    }

    public IToolCallback getToolCallback() {
        return toolCallback;
    }

    public void setToolCallback(IToolCallback toolCallback) {
        this.toolCallback = toolCallback;
    }

    public ITplCallback getTplCallback() {
        return tplCallback;
    }

    public void setTplCallback(ITplCallback tplCallback) {
        this.tplCallback = tplCallback;
        if (null != tplCallback) {
            if (tplCallback instanceof ITplDefaultCallback) {
//                ((ITplDefaultCallback) tplCallback).onDefaultCallback();
                TplCommonResourceObject.getInstance().onDefaultCallback(getContext());
            }
            if (tplCallback instanceof ITplSpeechCallback) {
                ((ITplSpeechCallback) tplCallback).onSpeechCallback(null);
            }
        }
    }

    public IViewCallback getViewCallback() {
        return viewCallback;
    }

    public void setViewCallback(IViewCallback viewCallback) {
        this.viewCallback = viewCallback;
    }

    public IWidgetCallback getWidgetCallback() {
        return widgetCallback;
    }

    public void setWidgetCallback(IWidgetCallback widgetCallback) {
        this.widgetCallback = widgetCallback;
    }
}
