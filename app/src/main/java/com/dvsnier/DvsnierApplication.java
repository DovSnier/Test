package com.dvsnier;

import android.app.Application;
import android.widget.Toast;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by Administrator on 2017/1/7.
 */

public class DvsnierApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Toast.makeText(this, "to Low Memory warning", Toast.LENGTH_SHORT).show();
    }
}
