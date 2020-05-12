package com.dvsnier;

import android.app.ActivityManager;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.dvsnier.cache.CacheManager;
import com.dvsnier.cache.base.TimeUnit;
import com.dvsnier.common.AppCommonWorkObject;
import com.dvsnier.common.impl.BaseImpl;
import com.dvsnier.test.utils.MD5;
import com.facebook.stetho.Stetho;
import com.facebook.stetho.common.LogRedirector;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.squareup.leakcanary.LeakCanary;

import java.util.List;

/**
 * Created by Administrator on 2017/1/7.
 */
public class DvsnierApplication extends BaseApplication<DvsnierApplication> {

    protected static final String TAG = "DvsnierApplication";
    protected static DvsnierApplication INSTANCE;

    public static DvsnierApplication getInstance() {
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        debug();
        if (getPackageName().equals(getProcessName(this))) {
            // 将“12345678”替换成您申请的APPID，申请地址：http://www.xfyun.cn
            // 请勿在“=”与appid之间添加任何空字符或者转义符
//        SpeechUtility.createUtility(this, SpeechConstant.APPID + "=599b97fa");
            AppCommonWorkObject.getInstance().setBaseCallback(new BaseImpl());

            StringBuilder stringBuilder = new StringBuilder(String.format("the current system time: %s%s", System.currentTimeMillis(), "\n"));
            initializedCrash();
            stringBuilder.append(String.format("the load crash module(%s) that is succeed.", System.currentTimeMillis())).append("\n");
            initializedCache();
            stringBuilder.append(String.format("the load cache module(%s) that is succeed.", System.currentTimeMillis())).append("\n");
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
        } else if ((getPackageName() + ":log").equals(getProcessName(this))) {
            StringBuilder stringBuilder = new StringBuilder(String.format("the current system time: %s%s", System.currentTimeMillis(), "\n"));
            initializedServerCache();
            stringBuilder.append(String.format("the load cache module(%s) that is succeed.", System.currentTimeMillis())).append("\n");
            stringBuilder.append("\n").append("the initialized succeed.").append("\n");
            CacheManager.getInstance().putString(ICacheType.TYPE_LOG_SERVICE, MD5.encode(getClass().getSimpleName() + System.currentTimeMillis()), stringBuilder.toString(), 2, TimeUnit.DAYS).commit();
        } else {
            // nothing to do
        }
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


    public String getProcessName(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        if (null != activityManager) {
            List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
            if (null != runningAppProcesses && !runningAppProcesses.isEmpty()) {
                for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                    if (runningAppProcessInfo.pid == android.os.Process.myPid()) {
                        return runningAppProcessInfo.processName;
                    }
                }
            }
        }
        return null;
    }

    protected void debug() {
        Log.w(TAG, String.format("the package name: %s , pid: %s , tid: %s , uid: %s.",
                getPackageName(),
                android.os.Process.myPid(),
                android.os.Process.myTid(),
                android.os.Process.myUid()));
        Log.w(TAG, String.format("process name: %s", getProcessName(this)));
    }
}
