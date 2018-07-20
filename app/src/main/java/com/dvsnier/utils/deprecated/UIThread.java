package com.dvsnier.utils.deprecated;

import android.support.annotation.NonNull;

/**
 * UI 线程工具类
 * Created by DovSnier on 2018/3/28.
 */
public interface UIThread {

    /**
     * running in the main thread
     *
     * @param runnable {@see Runnable}
     */
    void runOnUiThread(@NonNull Runnable runnable);

    /**
     * running in the main thread
     *
     * @param runnable    {@see Runnable}
     * @param delayMillis delay a few delayMillis to execute
     */
    void runOnUiThread(@NonNull Runnable runnable, long delayMillis);

    /**
     * whether it is the main thread
     *
     * @return true is that main thread
     */
    boolean isUIThread();
}
