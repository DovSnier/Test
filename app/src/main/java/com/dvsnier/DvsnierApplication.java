package com.dvsnier;

import android.app.Application;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;
import com.dvsnier.bean.DaoMaster;
import com.dvsnier.bean.DaoSession;
import com.dvsnier.crashmonitor.server.MoniterService;
import com.facebook.stetho.Stetho;
import com.facebook.stetho.common.LogRedirector;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;
import com.squareup.leakcanary.LeakCanary;
import okhttp3.OkHttpClient;

/**
 * Created by Administrator on 2017/1/7.
 */
public class DvsnierApplication extends Application {

  protected static DvsnierApplication instance;
  protected DaoSession daoSession;
  protected OkHttpClient okHttpClient;

  public static DvsnierApplication getInstance() {
    return instance;
  }

  @Override public void onCreate() {
    // 将“12345678”替换成您申请的APPID，申请地址：http://www.xfyun.cn
    // 请勿在“=”与appid之间添加任何空字符或者转义符
    SpeechUtility.createUtility(this, SpeechConstant.APPID + "=599b97fa");
    super.onCreate();
    instance = this;
    initializedServerConfig();
    if (LeakCanary.isInAnalyzerProcess(this)) {
      // This process is dedicated to LeakCanary for heap analysis.
      // You should not init your app in this process.
      return;
    }
    LeakCanary.install(this);
    Stetho.initializeWithDefaults(this);
    LogRedirector.setLogger(new LogRedirector.Logger() {
      @Override public boolean isLoggable(String tag, int priority) {
        return true;
      }

      @Override public void log(int priority, String tag, String message) {
        Log.println(priority, tag, message);
      }
    });
    obtainDataBase();
  }

  protected void obtainDataBase() {
    DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(this, "test.db");
    DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
    daoSession = daoMaster.newSession();
  }

  @Override public void onLowMemory() {
    super.onLowMemory();
    Toast.makeText(this, "to Low Memory warning", Toast.LENGTH_SHORT).show();
  }

  @Override public void onTerminate() {
    super.onTerminate();
    stopServer();
  }

  public DaoSession getDaoSession() {
    return daoSession;
  }

  /**
   * the initialized server config
   *
   * @version 0.0.2
   */
  protected void initializedServerConfig() {
    Intent intent = new Intent(this, MoniterService.class);
    startService(intent);
  }

  /**
   * to closed server monitor
   *
   * @version 0.0.1
   */
  protected void stopServer() {
    Intent intent = new Intent(this, MoniterService.class);
    stopService(intent);
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
