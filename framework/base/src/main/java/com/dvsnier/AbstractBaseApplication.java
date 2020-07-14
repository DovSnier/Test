package com.dvsnier;

import android.support.multidex.MultiDexApplication;

/**
 * AbstractBaseApplication
 * Created by dovsnier on 2019/6/26.
 */
public abstract class AbstractBaseApplication<T> extends MultiDexApplication implements IBaseApplication<T> {
}
