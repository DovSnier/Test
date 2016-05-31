package com.dvsnier.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by DovSnier on 2016/5/31.
 */
public class DBManager {

    protected static final String TAG = DBManager.class.getSimpleName();
    private static DBManager instance;
    private DBHelper dbHelper;
    private SQLiteDatabase database;

    private DBManager() {
    }

    /**
     * to get instance objects
     *
     * @return {@see DBManager}
     */
    public static DBManager getInstance() {
        if (null == instance) {
            synchronized (DBManager.class) {
                if (null == instance) {
                    instance = new DBManager();
                    Log.i(TAG, "the initialized databases manager is successfully.");
                }
            }
        }
        return instance;
    }

    public void createDatabase(DBHelper dbHelper) {
        this.dbHelper = dbHelper;
        database = this.dbHelper.getWritableDatabase();
    }

    public final void openDatabase(DBHelper dbHelper) {
        closeDatabase();
        this.dbHelper = dbHelper;
        database = this.dbHelper.getReadableDatabase();
        Log.i(TAG, "the open databases(that opened databases already) is success.");
    }

    public final void openDatabase(Context context, final String dabaseName) {
        closeDatabase();
        if (null == dabaseName || "".equals(dabaseName) || "null".equals(dabaseName)) {
            new IllegalAccessException("the dabasename do not conform to the specifications.");
        }
        this.dbHelper = new Builder(context).setDatabaseName(dabaseName).setVersion(1).builder();
        database = this.dbHelper.getReadableDatabase();
        Log.i(TAG, "the open databases(that opened databases already) is success.");
    }

//    createTable
//    query
//    insert
//    update
//    delete
//    dropTable

    public synchronized final void closeDatabase() {
        if (null != this.dbHelper) {
            dbHelper.close();
            Log.i(TAG, "the close databases(that opened databases already) is success.");
        }
    }

//    public synchronized final void dropDatabase() {
//    }

    public static class DBHelper extends SQLiteOpenHelper {

        protected String TAG = this.getClass().getSimpleName();
        protected Context context;

        protected DBHelper(Context context, String databaseName, SQLiteDatabase.CursorFactory factory, int databaseVersion) {
            super(context, databaseName, factory, databaseVersion);
            this.context = context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.i(TAG, "to check database or table has exist otherwise on create that.");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            if (newVersion <= oldVersion) {
                return;
            }
            Log.i(TAG, "to check database or table has best up to date otherwise on upgrade that.");
            int offset = newVersion - oldVersion;
        }
    }

    public static class Builder {

        protected Context context;
        protected String databaseName;
        protected SQLiteDatabase.CursorFactory factory;
        protected int version;

        private Builder() {}

        public Builder(Context context) {
            if (null == context) {
                new IllegalAccessException("the context is required.");
            }
            this.context = context;
        }

        public DBHelper builder() {
            Log.i(TAG, "the databases who is name that " + databaseName + " and is version that " + version + ".");
            return new DBHelper(context, databaseName, factory, version);
        }

        public String getDatabaseName() {
            return databaseName;
        }

        public Builder setDatabaseName(String databaseName) {
            this.databaseName = databaseName;
            return this;
        }

        public Context getContext() {
            return context;
        }

        public Builder setContext(Context context) {
            this.context = context;
            return this;
        }

        public SQLiteDatabase.CursorFactory getFactory() {
            return factory;
        }

        public Builder setFactory(SQLiteDatabase.CursorFactory factory) {
            this.factory = factory;
            return this;
        }

        public int getVersion() {
            return version;
        }

        public Builder setVersion(int version) {
            this.version = version;
            return this;
        }
    }
}
