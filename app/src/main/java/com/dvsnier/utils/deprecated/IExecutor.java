package com.dvsnier.utils.deprecated;

import android.support.annotation.NonNull;

/**
 * 线程池接口类
 * Created by DovSnier on 2016/5/20.
 */
public interface IExecutor {

    int THREADS_COUNT = 6;
    int CORE_POOL_SIZE = 3;

    /**
     * to execution of tasks
     *
     * @param runnable {@see Runnable}
     */
    void execute(@NonNull Runnable runnable);

    /**
     * the thread dormancy specified time
     *
     * @param millis
     */
    void sleep(long millis);

    /**
     * the close executor service pool
     */
    void shutdown();
}
