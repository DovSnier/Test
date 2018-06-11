package com.dvsnier.utils;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MotionEvent;

public class DebugUtil {

    public static void d(@NonNull String tag, @Nullable String value, @NonNull MotionEvent motionEvent) {
        Log.d(tag, String.format("%1$s , %2$s", value, obtainAction(motionEvent)));
    }

    public static void d(@NonNull String tag, @NonNull MotionEvent motionEvent) {
        Log.d(tag, obtainAction(motionEvent));
    }

    public static void d(@NonNull String tag, @Nullable String value) {
        Log.d(tag, value);
    }

    private static String obtainAction(@NonNull MotionEvent motionEvent) {
        String value = "";
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                value = "ACTION_DOWN";
                break;
            case MotionEvent.ACTION_MOVE:
                value = "ACTION_MOVE";
                break;
            case MotionEvent.ACTION_UP:
                value = "ACTION_UP";
                break;
            case MotionEvent.ACTION_CANCEL:
                value = "ACTION_CANCEL";
                break;
        }
        return value;
    }

}
