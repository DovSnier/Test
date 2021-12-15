package com.dvsnier.db;

import org.greenrobot.greendao.AbstractDaoSession;

/**
 * Created by dovsnier on 2019/6/25.
 */
public interface IDaoSession {

    AbstractDaoSession getDaoSession();
}
