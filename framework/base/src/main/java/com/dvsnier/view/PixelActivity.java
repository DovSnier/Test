package com.dvsnier.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.dvsnier.base.flavor.R;
import com.dvsnier.base.flavor.activity.BaseActivity;
import com.dvsnier.base.task.AbstractUIRunnable;
import com.dvsnier.cache.CacheManager;

public class PixelActivity extends BaseActivity {

    public static final String KEY_TYPE = "type";
    public static final String KEY_TIME = "time";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pixel);
//        ButterKnife.bind(this);
        performScheduledInternal();
    }

    @Override
    public void initView() {
        super.initView();
    }

    @Override
    public void initData() {
        super.initData();

        Intent intent = getIntent();
        if (null != intent) {
            Uri uri = intent.getData();
            if (null != uri) {
                Log.d(TAG, String.format("uri: %s", uri.toString()));
                String scheme = uri.getScheme();
                Log.d(TAG, String.format("scheme: %s", scheme));
                String host = uri.getHost();
                Log.d(TAG, String.format("host: %s", host));
                int port = uri.getPort();
                Log.d(TAG, String.format("port: %s", port));
                String path = uri.getPath();
                Log.d(TAG, String.format("path: %s", path));
                String query = uri.getQuery();
                Log.d(TAG, String.format("query: %s", query));
                String type = uri.getQueryParameter(KEY_TYPE);
                Log.d(TAG, String.format("type: %s", type));
                String time = uri.getQueryParameter(KEY_TIME);
                Log.d(TAG, String.format("time: %s", time));

                onToast(String.format("type: %s , time: %s .", type, time));
                CacheManager.getInstance().put(KEY_TYPE, type).put(KEY_TIME, time).commit();
            }
        }

        post(new AbstractUIRunnable() {
            @Override
            public void stashRun() {
                Uri uri = Uri.parse("dvsnier://dovsnier.io/launcher");
                Intent launcherIntent = new Intent();
                launcherIntent.setData(uri);
                startActivity(launcherIntent);
                finish();
            }
        });
    }
}
