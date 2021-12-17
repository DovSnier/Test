package com.dvsnier.common.callback;

import android.content.Context;

import androidx.annotation.Nullable;

/**
 * ITplSpeechCallback
 * Created by dovsnier on 2020/4/26.
 */
public interface ITplSpeechCallback extends ITplCallback {

    void onSpeechCallback(@Nullable Context context);
}
