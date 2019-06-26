package com.dvsnier.test.tpl.green.common;

import android.content.Context;

import com.dvsnier.IWith;
import com.dvsnier.base.task.ICycle;
import com.dvsnier.bean.DaoMaster;
import com.dvsnier.bean.DaoSession;
import com.dvsnier.db.IDaoSession;

/**
 * DaoSessionWrapper
 * Created by dovsnier on 2019/6/25.
 */
public class DaoSessionWrapper implements IDaoSession, IWith<DaoSessionWrapper>, ICycle {

    private static final DaoSessionWrapper INSTANCE = new DaoSessionWrapper();
    protected DaoSession daoSession;
    protected Context context;

    public static DaoSessionWrapper getInstance() {
        return INSTANCE;
    }

    private DaoSessionWrapper() {
    }

    @Override
    public DaoSession getDaoSession() {
        return daoSession;
    }

    protected void obtainDataBase() {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(getContext(), "test.db");
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
        daoSession = daoMaster.newSession();
    }

    @Override
    public DaoSessionWrapper with(Context context) {
        if (null == getContext()) {
            setContext(context);
        }
        if (null == getDaoSession()) {
            obtainDataBase();
        }
        return this;
    }

    @Override
    public void onDestroy() {
        if (null != getContext()) {
            setContext(null);
        }
        if (null != getDaoSession()) {
            getDaoSession().clear();
        }
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
