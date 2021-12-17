package com.dvsnier.test.interceptor.common.impl;


import android.content.Context;

import com.dvsnier.common.TplCommonResourceObject;
import com.dvsnier.common.callback.ITplCallback;
import com.dvsnier.common.callback.ITplSpeechCallback;
import com.dvsnier.test.interceptor.common.InterceptorCommonResourceObject;

/**
 * ToolImpl
 * Created by dovsnier on 2020/4/26.
 */
public class TplImpl implements ITplCallback, ITplSpeechCallback {

    @Override
    public void onSpeechCallback(Context context) {
        TplCommonResourceObject.getInstance().onSpeechCallback(
                InterceptorCommonResourceObject.getInstance().getContext());
    }
}
