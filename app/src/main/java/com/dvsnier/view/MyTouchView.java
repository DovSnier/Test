package com.dvsnier.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

public class MyTouchView extends android.support.v7.widget.AppCompatTextView {

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
        Log.d(TAG, "dispatchTouchEvent");
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "onTouchEvent");
        return super.onTouchEvent(event);
    }
}
