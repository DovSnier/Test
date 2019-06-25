package com.dvsnier;

import android.util.Log;
import android.widget.Toast;

import com.dvsnier.crash.Crash;
import com.facebook.stetho.Stetho;
import com.facebook.stetho.common.LogRedirector;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.squareup.leakcanary.LeakCanary;

import okhttp3.OkHttpClient;

/**
 * Created by Administrator on 2017/1/7.
 */
public class DvsnierApplication extends BaseApplication {

    protected static DvsnierApplication instance;
    protected OkHttpClient okHttpClient;

    public static DvsnierApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        // 将“12345678”替换成您申请的APPID，申请地址：http://www.xfyun.cn
        // 请勿在“=”与appid之间添加任何空字符或者转义符
//        SpeechUtility.createUtility(this, SpeechConstant.APPID + "=599b97fa");
        super.onCreate();
        instance = this;
        Crash.initialize(this);
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
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


    public static OkHttpClient getHttpClient() {
        return getInstance().getOkHttpClient();
    }

    public OkHttpClient getOkHttpClient() {
        if (null == okHttpClient) {
            synchronized (OkHttpClient.class) {
                if (null == okHttpClient) {
                    okHttpClient = new OkHttpClient().newBuilder()
                            .addNetworkInterceptor(new StethoInterceptor())
                            .build();
                }
            }
        }
        return okHttpClient;
    }
}
