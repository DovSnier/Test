package com.dvsnier.base.flavor.cache;

import android.app.ActivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.collection.LruCache;

import com.dvsnier.base.flavor.R;
import com.dvsnier.base.flavor.R2;
import com.dvsnier.base.flavor.activity.BaseActivity;
import com.dvsnier.base.flavor.constant.IDvsType;
import com.dvsnier.base.task.AbstractUIRunnable;
import com.dvsnier.cache.CacheManager;
import com.dvsnier.cache.base.TimeUnit;
import com.dvsnier.test.utils.MD5;
import com.dvsnier.test.utils.U;
import com.jakewharton.disklrucache.DiskLruCache;

import junit.framework.Assert;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TestCacheActivity extends BaseActivity {

    @BindView(R2.id.btn_cache_start)
    Button btnCacheStart;
    @BindView(R2.id.btn_cache_close)
    Button btnCacheClose;
    @BindView(R2.id.btn_persistence_cache)
    Button btnPersistenceCache;
    @BindView(R2.id.btn_persistence_cache_get)
    Button btnPersistenceCacheGet;
    @BindView(R2.id.content)
    TextView content;

    protected final String TAG = this.getClass().getSimpleName();
    private ThreadFactory threadFactory;
    private ExecutorService executorService;
    private LruCache lruCache;
    private DiskLruCache diskLruCache;
    private String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_cache);
        ButterKnife.bind(this);
        threadFactory = Executors.defaultThreadFactory();
        executorService = Executors.newFixedThreadPool(3, threadFactory);
        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        int size = (activityManager.getMemoryClass() * 1024 * 1024) / 8;
        Log.d(TAG, "the current memory cache divide that is " + size);
        lruCache = new LruCache(size);
        try {
            File cacheFile = new File(
                    U.getCacheFile(this).getAbsolutePath() + File.separator +
                            "demo_test_cache");
            cacheFile.mkdirs();
            int appVersion = U.getAppVersion(this);
            long maxSize = Double.valueOf(Integer.MAX_VALUE / 1024).longValue();
            diskLruCache = DiskLruCache.open(cacheFile, appVersion, 1, maxSize);
            Assert.assertNotNull(diskLruCache);
            Log.d(TAG, "the current disk cache that is cacheFile " + cacheFile.getAbsolutePath() + " appVersion " + appVersion + " " + "and maxCache " + maxSize / (1024) + "m.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        CharSequence text = "the initialized success.";
        content.setText(text);
    }

    @OnClick({R2.id.btn_cache_start,
            R2.id.btn_cache_close,
            R2.id.btn_persistence_cache,
            R2.id.btn_persistence_cache_get})
    public void onClick(View view) {
        int viewId = view.getId();
        if (viewId == R.id.btn_cache_start) {
            for (int i = 0; i < 1000; i++) {
                final int ii = i;
                executorService.submit(threadFactory.newThread(new Runnable() {
                    @Override
                    public void run() {
                        key = MD5.encode(String.valueOf(System.currentTimeMillis()));
                        String value = String.valueOf(System.currentTimeMillis() * (Math.random() * 100));
                        lruCache.put(key, value);
                        try {
                            DiskLruCache.Editor edit = diskLruCache.edit(key);
                            OutputStream outputStream = edit.newOutputStream(0);
                            U.writeToFile(outputStream, value);
                            edit.commit();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        final String msg = "the current generate event sequence(" + key + ") is " + ii + "";
                        content.post(new Runnable() {
                            @Override
                            public void run() {
                                content.setText(msg);
                            }
                        });
                        Log.d(TAG, msg);
                    }
                }));
            }
        } else if (viewId == R.id.btn_cache_close) {
            if (null == key) return;
            Log.i(TAG, "to lru cache of key(" + key + ") that is " + lruCache.get(key));
            executorService.shutdownNow();
            CharSequence text = "the cache test has terminal.";
            content.setText(text);
            try {
                DiskLruCache.Snapshot snapshot = diskLruCache.get(key);
                Assert.assertNotNull(snapshot);
                Log.i(TAG, "to disk lru cache of key(" + key + ") that is " + U.readFromFile(snapshot.getInputStream(0)));
            } catch (IOException e) {
                e.printStackTrace();
            }
            finish();
        } else if (viewId == R.id.btn_persistence_cache) {
            String value = String.valueOf(System.currentTimeMillis());
            CacheManager.getInstance()
                    .put(IDvsType.TYPE_PERSISTENCE_DOWNLOADS, MD5.encode(value), value)
                    .putString(IDvsType.TYPE_PERSISTENCE_DOWNLOADS, MD5.encode(TAG), value,
                            2, TimeUnit.MINUTES)
                    .commit(IDvsType.TYPE_PERSISTENCE_DOWNLOADS);

            for (int i = 0; i < 3; i++) {
                String valueOfI = String.valueOf(System.currentTimeMillis());
                CacheManager.getInstance()
                        .put(IDvsType.TYPE_PERSISTENCE_DOWNLOADS, MD5.encode(valueOfI), valueOfI,
                                15, TimeUnit.SECONDS);
                CacheManager.getInstance()
                        .put(IDvsType.TYPE_PERSISTENCE_DVS, MD5.encode(valueOfI), valueOfI,
                                15, TimeUnit.SECONDS);
            }
            CacheManager.getInstance().commit(IDvsType.TYPE_PERSISTENCE_DOWNLOADS);
            CacheManager.getInstance().commit(IDvsType.TYPE_PERSISTENCE_DVS);

        } else if (viewId == R.id.btn_persistence_cache_get) {
            postDelayed(new AbstractUIRunnable() {
                @Override
                public void stashRun() {
                    String value = CacheManager.getInstance()
                            .getString(IDvsType.TYPE_PERSISTENCE_DOWNLOADS, MD5.encode(TAG));
                    String text = String.format("the persistence value is %s", value);
                    Log.d(TAG, text);
                    content.setText(text);
                }
            }, 500);
        } else {
            // nothing to do
        }
    }
}
