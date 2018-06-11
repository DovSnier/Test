package com.dvsnier.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

import com.dvsnier.utils.DebugUtil;

public class ParentLayout extends RelativeLayout {

    protected final String TAG = ParentLayout.this.getClass().getSimpleName();

    public ParentLayout(Context context) {
        super(context);
    }

    public ParentLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ParentLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ParentLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        DebugUtil.d(TAG, "dispatchTouchEvent", event);
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        DebugUtil.d(TAG, "onInterceptTouchEvent", ev);
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        DebugUtil.d(TAG, "onTouchEvent", event);
        return super.onTouchEvent(event);
    }
}
