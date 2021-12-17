package com.dvsnier.common;

import android.content.Context;

import androidx.annotation.NonNull;

import com.dvsnier.common.callback.ITplCallback;
import com.dvsnier.common.callback.ITplDefaultCallback;
import com.dvsnier.common.callback.ITplSpeechCallback;
import com.facebook.soloader.SoLoader;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;

/**
 * TplCommonResourceObject
 * Created by dovsnier on 2020/4/26.
 */
public class TplCommonResourceObject implements ITplDefaultCallback, ITplSpeechCallback {

    private static final TplCommonResourceObject INSTANCE = new TplCommonResourceObject();

    public static TplCommonResourceObject getInstance() {
        return INSTANCE;
    }

    private TplCommonResourceObject() {
    }

    protected ITplCallback tplCallback;

    public ITplCallback getTplCallback() {
        return tplCallback;
    }

    public void setTplCallback(ITplCallback tplCallback) {
        this.tplCallback = tplCallback;
    }


    @Override
    public void onDefaultCallback(@NonNull Context context) {
        SoLoader.init(context, false);
    }

    @Override
    public void onSpeechCallback(Context context) {
        // 将“12345678”替换成您申请的APPID，申请地址：http://www.xfyun.cn
        // 请勿在“=”与appid之间添加任何空字符或者转义符
        SpeechUtility.createUtility(context, SpeechConstant.APPID + "=599b97fa");
    }
}
