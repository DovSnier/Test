package com.dvsnier.test.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
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
    protected CharSequence content;
    protected boolean richTextClickable;

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
            content = typedArray.getString(R.styleable.CompactTextView_content);
            richTextClickable = typedArray.getBoolean(R.styleable.CompactTextView_richTextClickable, true);
            typedArray.recycle();
        }
    }

    public void initView() {

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    public void onDestroy() {

    }

    public int getMaxWidth() {
        return maxWidth;
    }

    public void setMaxWidth(int maxWidth) {
        this.maxWidth = maxWidth;
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    public void setMaxHeight(int maxHeight) {
        this.maxHeight = maxHeight;
    }

    public int getTextSize() {
        return textSize;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public int getEllipsize() {
        return ellipsize;
    }

    public void setEllipsize(int ellipsize) {
        this.ellipsize = ellipsize;
    }

    public int getHeightLightColor() {
        return heightLightColor;
    }

    public void setHeightLightColor(int heightLightColor) {
        this.heightLightColor = heightLightColor;
    }

    public int getMaxLines() {
        return maxLines;
    }

    public void setMaxLines(int maxLines) {
        this.maxLines = maxLines;
    }

    public String getEllipseSuffix() {
        return ellipseSuffix;
    }

    public void setEllipseSuffix(String ellipseSuffix) {
        this.ellipseSuffix = ellipseSuffix;
    }

    public CharSequence getContent() {
        return content;
    }

    public void setContent(CharSequence content) {
        this.content = content;
    }

    public boolean isRichTextClickable() {
        return richTextClickable;
    }

    public void setRichTextClickable(boolean richTextClickable) {
        this.richTextClickable = richTextClickable;
    }
}