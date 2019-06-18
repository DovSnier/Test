package com.dvsnier.test.utils;

import android.content.Context;
import android.util.Log;

import org.xutils.DbManager;

/**
 * 　┏┓　　　┏┓
 * ┏┛┻━━━┛┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 * 　　┃　　　┃神兽保佑
 * 　　┃　　　┃代码无BUG！
 * 　　┃　　　┗━━━┓
 * 　　┃　　　　　　　┣┓
 * 　　┃　　　　　　　┏┛
 * 　　┗┓┓┏━┳┓┏┛
 * 　　　┃┫┫　┃┫┫
 * 　　　┗┻┛　┗┻┛
 * <p>
 * Created by DovSnier on 2017/3/07.
 */
public class DataBaseManager {

    protected static final String TAG = DataBaseManager.class.getSimpleName();
    private static DataBaseManager instance;
    protected Context context;
    protected DbManager.DaoConfig daoConfig;
    private static boolean DEBUG = true;

    private DataBaseManager() {
        daoConfig = new DbManager.DaoConfig()
                .setDbName(context.getPackageName() + ".db")
                .setDbDir(context.getDatabasePath("temp").getParentFile().getAbsoluteFile())
                .setDbVersion(1)
                .setDbOpenListener(new DbManager.DbOpenListener() {
                    @Override
                    public void onDbOpened(DbManager db) {
                        // 开启WAL, 对写入加速提升巨大
                        db.getDatabase().enableWriteAheadLogging();
                    }
                });
    }

    /**
     * to get instance objects
     *
     * @return {@see DBManager}
     */
    public static DataBaseManager getInstance() {
        if (null == instance) {
            synchronized (DataBaseManager.class) {
                if (null == instance) {
                    instance = new DataBaseManager();
                    Log.i(TAG, "the initialized databases manager is successfully.");
                }
            }
        }
        return instance;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public DbManager.DaoConfig getDaoConfig() {
        return daoConfig;
    }

    public void setDaoConfig(DbManager.DaoConfig daoConfig) {
        this.daoConfig = daoConfig;
    }
    //    public static class Builder {
//    }
}
