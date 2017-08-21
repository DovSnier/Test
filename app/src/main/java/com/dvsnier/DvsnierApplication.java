package com.dvsnier;

import android.app.Application;
import android.widget.Toast;
import com.dvsnier.bean.DaoMaster;
import com.dvsnier.bean.DaoSession;
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
    super.onCreate();
    instance = this;
    if (LeakCanary.isInAnalyzerProcess(this)) {
      // This process is dedicated to LeakCanary for heap analysis.
      // You should not init your app in this process.
      return;
    }
    LeakCanary.install(this);
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
