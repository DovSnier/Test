package com.dvsnier.test.utils;

/**
 * Created by dovsnier on 2019/5/11.
 */
public class ClickUtils {

    protected static long CURRENT_TIME_STAMP;
    protected long mTimeStamp;
    protected static final long MAX_TIME_STAMP = 1000;

    public static ClickUtils newInstance() {
        return new ClickUtils();
    }

    public static boolean clickable() {
        if (System.currentTimeMillis() - CURRENT_TIME_STAMP > MAX_TIME_STAMP) {
            CURRENT_TIME_STAMP = System.currentTimeMillis();
            return true;
        }
        CURRENT_TIME_STAMP = System.currentTimeMillis();
        return false;
    }

    public boolean isClick() {
        return isClick(MAX_TIME_STAMP);
    }

    public boolean isClick(long maxTimeStamp) {
        if (maxTimeStamp <= 0) {
            maxTimeStamp = MAX_TIME_STAMP;
        }
        if (System.currentTimeMillis() - mTimeStamp > maxTimeStamp) {
            mTimeStamp = System.currentTimeMillis();
            return true;
        }
        mTimeStamp = System.currentTimeMillis();
        return false;
    }
}
