package com.dvsnier.utils.deprecated;

import android.support.annotation.IntDef;
import android.support.annotation.NonNull;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.dvsnier.utils.deprecated.ExecuteUtil.ExecuteType.CACHED;
import static com.dvsnier.utils.deprecated.ExecuteUtil.ExecuteType.FIXED;
import static com.dvsnier.utils.deprecated.ExecuteUtil.ExecuteType.SCHEDULED;
import static com.dvsnier.utils.deprecated.ExecuteUtil.ExecuteType.SINGLE;

/**
 * 线程池工具类
 * Created by DovSnier on 2016/5/20.
 */
public class ExecuteUtil implements IExecutor {

    protected ExecutorService mDefaultExecutorService;

    public ExecuteUtil() {
        mDefaultExecutorService = Executors.newCachedThreadPool();
    }

    public ExecuteUtil(@ExecuteType int executeType) {
        if (executeType == ExecuteType.FIXED) {
            mDefaultExecutorService = Executors.newFixedThreadPool(THREADS_COUNT);
        } else if (executeType == ExecuteType.SINGLE) {
            mDefaultExecutorService = Executors.newSingleThreadExecutor();
        } else if (executeType == ExecuteType.SCHEDULED) {
            mDefaultExecutorService = Executors.newScheduledThreadPool(CORE_POOL_SIZE);
        } else {
            mDefaultExecutorService = Executors.newCachedThreadPool();
        }
    }

    @Override
    public void execute(@NonNull Runnable runnable) {
        mDefaultExecutorService.execute(runnable);
    }

    @Override
    public void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void shutdown() {
        if (null != mDefaultExecutorService) {
            if (!mDefaultExecutorService.isShutdown()) {
                mDefaultExecutorService.shutdown();
                mDefaultExecutorService = null;
            }
        }
    }

    @IntDef({CACHED, SCHEDULED, SINGLE, FIXED})
    public @interface ExecuteType {
        int CACHED = -1; // 无限缓存
        int SCHEDULED = 0; // 周期类型
        int SINGLE = 1; // 单线程FIFO,LIFO
        int FIXED = 2; // 定长类型
    }

}
