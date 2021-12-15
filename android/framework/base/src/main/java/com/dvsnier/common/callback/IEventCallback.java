package com.dvsnier.common.callback;

import android.content.Context;

/**
 * IEventCallbak
 * Created by dovsnier on 2020/4/26.
 */
public interface IEventCallback extends IBaseCallback {

    void onEvent(Context context, int position, Object object);
}
