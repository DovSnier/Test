package com.dvsnier;

import android.app.Application;
import android.content.Intent;
import android.widget.Toast;
import com.dvsnier.bean.DaoMaster;
import com.dvsnier.bean.DaoSession;
import com.dvsnier.crashmonitor.server.MoniterService;
import com.facebook.stetho.Stetho;
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
    initializedServerConfig();
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
}
