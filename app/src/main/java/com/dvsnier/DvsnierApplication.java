package com.dvsnier;

import android.app.Application;
import android.widget.Toast;
import com.dvsnier.bean.DaoMaster;
import com.dvsnier.bean.DaoSession;
import com.facebook.stetho.Stetho;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by Administrator on 2017/1/7.
 */
public class DvsnierApplication extends Application {

  protected static DvsnierApplication instance;
  protected DaoSession daoSession;

  public static DvsnierApplication getInstance() {
    return instance;
  }

  @Override public void onCreate() {
    // 将“12345678”替换成您申请的APPID，申请地址：http://www.xfyun.cn
    // 请勿在“=”与appid之间添加任何空字符或者转义符
    SpeechUtility.createUtility(this, SpeechConstant.APPID + "=599b97fa");
    super.onCreate();
    instance = this;
    if (LeakCanary.isInAnalyzerProcess(this)) {
      // This process is dedicated to LeakCanary for heap analysis.
      // You should not init your app in this process.
      return;
    }
    LeakCanary.install(this);
    Stetho.initializeWithDefaults(this);
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

  public DaoSession getDaoSession() {
    return daoSession;
  }
}
