package com.dvsnier;

import android.util.Log;
import android.widget.Toast;

import com.dvsnier.cache.CacheManager;
import com.dvsnier.cache.base.TimeUnit;
import com.dvsnier.test.utils.MD5;
import com.facebook.stetho.Stetho;
import com.facebook.stetho.common.LogRedirector;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by Administrator on 2017/1/7.
 */
public class DvsnierApplication extends BaseApplication<DvsnierApplication> {

    protected static DvsnierApplication INSTANCE;

    public static DvsnierApplication getInstance() {
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        // 将“12345678”替换成您申请的APPID，申请地址：http://www.xfyun.cn
        // 请勿在“=”与appid之间添加任何空字符或者转义符
//        SpeechUtility.createUtility(this, SpeechConstant.APPID + "=599b97fa");
        super.onCreate();
        INSTANCE = this;
        StringBuilder stringBuilder = new StringBuilder(String.format("the current system time: %s%s", System.currentTimeMillis(), "\n"));

        initializedCrash();
        stringBuilder.append(String.format("the load crash module(%s) that is succeed.", System.currentTimeMillis())).append("\n");

        initializedCache();
        stringBuilder.append(String.format("the load cache module(%s) that is succeed.", System.currentTimeMillis())).append("\n");

        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        stringBuilder.append(String.format("the load leak canary module(%s) that is succeed.", System.currentTimeMillis())).append("\n");

        Logger.addLogAdapter(new AndroidLogAdapter() {
            @Override
            public boolean isLoggable(int priority, String tag) {
                return !BuildConfig.DEBUG;
            }
        });
        Stetho.initializeWithDefaults(this);
        LogRedirector.setLogger(new LogRedirector.Logger() {
            @Override
            public boolean isLoggable(String tag, int priority) {
                return true;
            }

            @Override
            public void log(int priority, String tag, String message) {
                switch (priority) {
                    case Log.VERBOSE:
                        Logger.v("%1$s %2$s", tag, message);
                        break;
                    case Log.DEBUG:
                        Logger.d("%1$s %2$s", tag, message);
                        break;
                    case Log.INFO:
                        Logger.i("%1$s %2$s", tag, message);
                        break;
                    case Log.WARN:
                        Logger.w("%1$s %2$s", tag, message);
                        break;
                    case Log.ERROR:
                        Logger.e("%1$s %2$s", tag, message);
                        break;
                }
            }
        });
        stringBuilder.append(String.format("the load stetho module(%s) that is succeed.", System.currentTimeMillis())).append("\n");

        stringBuilder.append("\n").append("the initialized succeed.").append("\n");

        CacheManager.getInstance().putString(MD5.encode(getClass().getSimpleName() + System.currentTimeMillis()), stringBuilder.toString(), 2, TimeUnit.DAYS).commit();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Toast.makeText(this, "to Low Memory warning", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    public DvsnierApplication getApplication() {
        return this;
    }
}
