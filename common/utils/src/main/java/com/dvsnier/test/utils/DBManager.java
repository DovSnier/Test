package com.dvsnier.test.utils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by DovSnier on 2016/5/20.
 */
public class DBManager {

    protected static final String TAG = DBManager.class.getSimpleName();
    private static DBManager instance;
    private DBHelper dbHelper;
    private SQLiteDatabase database;
    private static boolean DEBUG = true;

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

    public SQLiteDatabase getDatabase() {
        return database;
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

    public final void executeAssetsSQL(InputStream inputStream, SQLiteDatabase db) {
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            String buffer = "";
            while ((line = bufferedReader.readLine()) != null) {
                buffer += line;
                if (null != line && !"".equals(line.trim()) && "--".equals(line.trim().substring(0, 2))) {
                    buffer = "";
                    continue;
                }
                if (line.trim().endsWith(";")) {
                    String sql = buffer.replace(";", "");
                    if (DEBUG) Log.d(TAG, "SQL Command: " + sql);
                    db.execSQL(sql);
                    buffer = "";
                } else {
                    if (null != buffer && !"".equals(buffer)) {
                        if ("//".equals(buffer.substring(0, 2))) buffer = "";
                        if ("/*".equals(buffer.substring(0, 2)) && "*/".equals(buffer.substring(buffer.length() - 2)))
                            buffer = "";
                    }
                }
            }
            if (DEBUG) Log.i(TAG, "the all sql command executed successfully");
        } catch (IOException e) {
            Log.e("db-error", e.toString());
        } finally {
            try {
                if (bufferedReader != null) bufferedReader.close();
            } catch (IOException e) {
                Log.e("db-error", e.toString());
            }
        }
    }

    public final boolean tableIsExist(String tableName, SQLiteDatabase db) {
        boolean result = false;
        if (tableName == null) {
            return false;
        }
        Cursor cursor = null;
        try {
            String sql = "select count(*) as c from sqlite_master where type ='table' and name ='" + tableName.trim() + "' ";
            cursor = db.rawQuery(sql, null);
            if (cursor.moveToNext()) {
                int count = cursor.getInt(0);
                if (count > 0) {
                    result = true;
                }
            }
        } catch (Exception e) {
            Log.e("db-error", e.toString());
        } finally {
            if (null != cursor) cursor.close();
        }
        return result;
    }


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

        private Builder() {
        }

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
