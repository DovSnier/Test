package com.dvsnier;

/**
 * IBaseApplication
 * Created by dovsnier on 2019/6/26.
 */
public interface IBaseApplication<T> extends IApplication {

    T getApplication();
}
