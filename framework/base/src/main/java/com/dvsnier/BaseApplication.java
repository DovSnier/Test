package com.dvsnier;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.dvsnier.base.flavor.constant.IDvsType;
import com.dvsnier.cache.CacheManager;
import com.dvsnier.cache.base.CacheGenre;
import com.dvsnier.cache.config.ICacheConfig;
import com.dvsnier.cache.infrastructure.AbstractStorage;
import com.dvsnier.cache.infrastructure.CacheStorage;
import com.dvsnier.cache.infrastructure.FileStorage;
import com.dvsnier.cache.infrastructure.LogStorage;
import com.dvsnier.crash.Crash;

import java.io.File;

/**
 * BaseApplication
 * Created by dovsnier on 2019/6/25.
 */
public abstract class BaseApplication<T> extends AbstractBaseApplication<T> {

    protected int cacheMaxSizeOfDisk;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
//        自定义磁盘512M 缓存空间
//        1 G<Integer.MAX_VALUE ~2 G
        cacheMaxSizeOfDisk = Double.valueOf(CacheStorage.INSTANCE()
                .getFormatted(512, AbstractStorage.SCU.M)).intValue();
    }

    public static int getAppVersionCode(Context context) {
        int versionCode = 1;
        PackageManager pm = context.getPackageManager();
        try {
            PackageInfo packInfo = pm.getPackageInfo(context.getPackageName(), 0);
            versionCode = packInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    public static String getAppVersionName(Context context) {
        String versionName = "";
        PackageManager pm = context.getPackageManager();
        try {
            PackageInfo packInfo = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = packInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }

    protected void initializedCache() {
        initializedDefaultCache();
        initializedDownloadCache();

        initializedPersistenceCache();
    }

    protected void initializedDefaultCache() {
        CacheManager.getInstance().initialize(new ICacheConfig.Builder(this)
                .setContext(this)
                .setAppVersion(getAppVersionCode(this))
                .setCacheMaxSizeOfDisk(cacheMaxSizeOfDisk)
                .setUniqueName(IDvsType.TYPE_DVS)
                .setCacheGenre(new CacheGenre.SCHEDULED())  // the Scheduled Mode, otherwise is default
                .create());
    }

    protected void initializedDownloadCache() {
        CacheManager.getInstance().initialize(ICacheType.TYPE_DOWNLOADS, new ICacheConfig.Builder(this)
                .setContext(this)
                .setAppVersion(getAppVersionCode(this))
                .setCacheMaxSizeOfDisk(cacheMaxSizeOfDisk)
                .setUniqueName(ICacheType.TYPE_DOWNLOADS)
                .setCacheGenre(new CacheGenre.SCHEDULED())  // the Scheduled Mode, otherwise is default
                .create());
    }

    protected void initializedServerCache() {
        CacheManager.getInstance().initialize(ICacheType.TYPE_LOG_SERVICE, new ICacheConfig.Builder(this)
                .setContext(this)
                .setAppVersion(getAppVersionCode(this))
                .setCacheMaxSizeOfDisk(cacheMaxSizeOfDisk)
                .setUniqueName(ICacheType.TYPE_LOG_SERVICE)
                .setCacheGenre(new CacheGenre.SCHEDULED())  // the Scheduled Mode, otherwise is default
                .create());
    }

    protected void initializedPersistenceCache() {
        int version = getPackageName().hashCode();
        FileStorage instance = FileStorage.INSTANCE();
        File appDir = new File(instance.getExternalStorageDirectory() + File.separator
                + IDvsType.TYPE_DOVSNIER);
        File fileWithDownloads = new File(appDir + File.separator + IDvsType.TYPE_DOWNLOADS);
        File fileWithSharedPrefs = new File(appDir + File.separator + IDvsType.TYPE_SHARED_PREFS);
        //noinspection ConstantConditions
        if (null != fileWithDownloads) {
            if (fileWithDownloads.exists()) {
//                file.deleteOnExit();
            }
            //noinspection ResultOfMethodCallIgnored
            fileWithDownloads.mkdirs();
//            Log.w("TAG", String.format("file is file(%s) or dir(%s) that absolute path is (%s)",
//                    fileWithDownloads.isFile(), fileWithDownloads.isDirectory(), fileWithDownloads.getAbsolutePath()));
        }
        if (null != fileWithSharedPrefs) {
            if (fileWithSharedPrefs.exists()) {
//                file.deleteOnExit();
            }
            //noinspection ResultOfMethodCallIgnored
            fileWithSharedPrefs.mkdirs();
//            Log.w("TAG", String.format("file is file(%s) or dir(%s) that absolute path is (%s)",
//                    fileWithSharedPrefs.isFile(), fileWithSharedPrefs.isDirectory(), fileWithSharedPrefs.getAbsolutePath()));
        }
        CacheManager.getInstance().initialize(IDvsType.TYPE_PERSISTENCE_DOWNLOADS,
                new ICacheConfig.Builder(this)
                        .setContext(this)
                        .setCacheDirectory(fileWithDownloads)
                        .setAppVersion(version)
                        .setCacheMaxSizeOfDisk(cacheMaxSizeOfDisk)
                        .setUniqueName(IDvsType.TYPE_DOWNLOADS)
                        .setCacheGenre(new CacheGenre.SCHEDULED())  // the Scheduled Mode, otherwise is default
                        .create());


        CacheManager.getInstance().initialize(IDvsType.TYPE_PERSISTENCE_DVS,
                new ICacheConfig.Builder(this)
                        .setContext(this)
                        .setCacheDirectory(fileWithSharedPrefs)
                        .setAppVersion(version)
                        .setCacheMaxSizeOfDisk(cacheMaxSizeOfDisk)
                        .setUniqueName(IDvsType.TYPE_SHARED_PREFS)
                        .setCacheGenre(new CacheGenre.SCHEDULED())  // the Scheduled Mode, otherwise is default
                        .create());
    }

    protected void initializedCrash() {
        LogStorage.INSTANCE().log(this);
        Crash.initialize(this);
    }
}
