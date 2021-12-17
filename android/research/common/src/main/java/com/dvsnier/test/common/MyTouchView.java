package com.dvsnier.test.common;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.Nullable;

import com.dvsnier.test.utils.DebugUtil;

public class MyTouchView extends androidx.appcompat.widget.AppCompatTextView {

    protected final String TAG = MyTouchView.this.getClass().getSimpleName();

    public MyTouchView(Context context) {
        super(context);
    }

    public MyTouchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTouchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        DebugUtil.d(TAG, "dispatchTouchEvent", event);
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        DebugUtil.d(TAG, "onTouchEvent", event);
        return super.onTouchEvent(event);
    }
}
