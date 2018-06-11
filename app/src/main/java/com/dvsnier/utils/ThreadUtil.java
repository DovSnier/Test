package com.dvsnier.utils;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

/**
 * 线程工具类
 * Created by DovSnier on 2018/3/28.
 */
public class ThreadUtil extends ExecuteUtil implements UIThread {

    protected static Handler mHandler = new Handler(Looper.getMainLooper());
    protected volatile static ThreadUtil INSTANCE;

    public static ThreadUtil getInstance() {
        if (null == INSTANCE) {
            synchronized (ThreadUtil.class) {
                if (null == INSTANCE) {
                    INSTANCE = new ThreadUtil();
                }
            }
        }
        return INSTANCE;
    }

    private ThreadUtil() {
        super(ExecuteType.FIXED);
    }

    @Override
    public boolean isUIThread() {
        return Looper.getMainLooper() == Looper.myLooper();
    }

    @Override
    public void runOnUiThread(@NonNull Runnable runnable) {
        if (isUIThread()) {
            runnable.run();
        } else {
            mHandler.post(runnable);
        }
    }

    @Override
    public void runOnUiThread(@NonNull Runnable runnable, long delayMillis) {
        mHandler.postDelayed(runnable, delayMillis);
    }
}
