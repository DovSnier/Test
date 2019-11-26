package com.dvsnier;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.dvsnier.base.flavor.constant.IDvsType;
import com.dvsnier.cache.CacheManager;
import com.dvsnier.cache.base.CacheGenre;
import com.dvsnier.cache.config.ICacheConfig;
import com.dvsnier.cache.config.IType;
import com.dvsnier.cache.infrastructure.AbstractStorage;
import com.dvsnier.cache.infrastructure.CacheStorage;
import com.dvsnier.cache.infrastructure.LogStorage;
import com.dvsnier.crash.Crash;

/**
 * BaseApplication
 * Created by dovsnier on 2019/6/25.
 */
public abstract class BaseApplication<T> extends AbstractBaseApplication<T> {


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
        //      自定义磁盘512M 缓存空间
        int cacheMaxSizeOfDisk = Double.valueOf(CacheStorage.INSTANCE().getFormatted(512, AbstractStorage.SCU.M)).intValue(); // 1G < Integer.MAX_VALUE ~ 2G
        CacheManager.getInstance().initialize(new ICacheConfig.Builder(this)
                .setContext(this)
                .setAppVersion(getAppVersionCode(this))
                .setCacheMaxSizeOfDisk(cacheMaxSizeOfDisk)
                .setUniqueName(IDvsType.TYPE_DVS)
                .setCacheGenre(new CacheGenre.SCHEDULED())  // the Scheduled Mode, otherwise is default
                .create());

        CacheManager.getInstance().initialize(IType.TYPE_DOWNLOADS, new ICacheConfig.Builder(this)
                .setContext(this)
                .setAppVersion(getAppVersionCode(this))
                .setCacheMaxSizeOfDisk(cacheMaxSizeOfDisk)
                .setUniqueName(IType.TYPE_DOWNLOADS)
                .setCacheGenre(new CacheGenre.SCHEDULED())  // the Scheduled Mode, otherwise is default
                .create());
    }

    protected void initializedCrash() {
        LogStorage.INSTANCE().log(this);
        Crash.initialize(this);
    }
}
