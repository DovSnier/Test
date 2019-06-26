package com.dvsnier;

import android.content.Context;

/**
 * IWith
 * Created by dovsnier on 2019/6/25.
 */
public interface IWith<T> {

    T with(Context context);
}
