package com.dvsnier.test.utils;

import android.content.Context;

/**
 * Created by dovsnier on 2020/3/15.
 */
public class WindowUtil {

    public static int getStatusBarHeight(Context context) {
        int result = 0;
        //获取状态栏高度的资源id
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}
