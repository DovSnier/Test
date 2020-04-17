package com.dvsnier.test.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import com.dvsnier.base.view.IView;

/**
 * CompactTextView
 * Created by machine code on 2020/4/16.
 */
public class CompactTextView extends View implements IView {

    protected int maxWidth;
    protected int maxHeight;
    protected int textSize;
    protected int textColor;
    protected int ellipsize;
    protected int heightLightColor;
    protected int maxLines;
    protected String ellipseSuffix;

    public CompactTextView(Context context) {
        super(context);
        obtainStyledAttributes(context.getTheme().obtainStyledAttributes(R.styleable.CompactTextView));
        initView();
    }

    public CompactTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        obtainStyledAttributes(context.getTheme().obtainStyledAttributes(attrs, R.styleable.CompactTextView, 0, 0));
        initView();
    }

    public CompactTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        obtainStyledAttributes(context.getTheme().obtainStyledAttributes(attrs, R.styleable.CompactTextView, defStyleAttr, 0));
        initView();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CompactTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        obtainStyledAttributes(context.getTheme().obtainStyledAttributes(attrs, R.styleable.CompactTextView, defStyleAttr, defStyleRes));
        initView();
    }

    public void obtainStyledAttributes(@NonNull TypedArray typedArray) {
        //noinspection ConstantConditions
        if (null != typedArray) {
            maxWidth = typedArray.getDimensionPixelSize(R.styleable.CompactTextView_maxWidth, 0);
            maxHeight = typedArray.getDimensionPixelSize(R.styleable.CompactTextView_maxHeight, 0);
            textSize = typedArray.getDimensionPixelSize(R.styleable.CompactTextView_textSize, 0);
            textColor = typedArray.getColor(R.styleable.CompactTextView_textColor, 0);
            ellipsize = typedArray.getInt(R.styleable.CompactTextView_ellipsize, 1);
            heightLightColor = typedArray.getColor(R.styleable.CompactTextView_heightLightColor, 0);
            maxLines = typedArray.getInt(R.styleable.CompactTextView_maxLines, 0);
            ellipseSuffix = typedArray.getString(R.styleable.CompactTextView_ellipseSuffix);
            typedArray.recycle();
        }
    }

    public void initView() {
    }

    @Override
    public void onDestroy() {

    }
}