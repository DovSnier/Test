package com.dvsnier.test.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.text.Spannable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.dvsnier.base.view.IView;

import java.util.ArrayList;
import java.util.List;

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
    protected Paint paint;
    private List<String> source;
    private Spannable spannable;
    private DisplayMetrics displayMetrics;
    private int maxItemWidth;

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
        maxItemWidth = 0;
        source = new ArrayList<>();
        displayMetrics = getResources().getDisplayMetrics();
        paint = new Paint();
        paint.setTextSize(getTextSize());
        paint.setColor(getTextColor());
        paint.setAntiAlias(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (TextUtils.isEmpty(getContent())) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        } else {
            int widthMode = MeasureSpec.getMode(widthMeasureSpec);
            int widthSize = MeasureSpec.getSize(widthMeasureSpec);
            int heightMode = MeasureSpec.getMode(heightMeasureSpec);
            int heightSize = MeasureSpec.getSize(heightMeasureSpec);
            if (widthMode == MeasureSpec.EXACTLY) {

            } else {
                // the compute text
                String[] strings = getContent().toString().split("\n");
                //noinspection ConstantConditions
                if (null != strings) {
                    if (strings.length > 0) {
                        for (int i = 0; i < strings.length; i++) {
                            //noinspection UseBulkOperation
                            source.add(strings[i]);
                        }
                        computeItemMax(widthSize);
                    }
                }
            }
            setMeasuredDimension(widthSize, heightSize);
        }
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

    private void computeItemMax(int widthSize) {
        for (int j = 0; j < source.size(); j++) {
            String item = source.get(j);
            float measureItemText = paint.measureText(item); // to measure of the current line text
            if (getMaxWidth() > 0) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    if (getMaxWidth() > getMinimumWidth()) {
                        if (measureItemText > getMaxWidth()) {
                            float[] measuredWidth = {};
                            int textLength = paint.breakText(item, true, getMaxWidth(), measuredWidth);
                            String firstItem = item.substring(0, textLength);
                            String lastItem = item.substring(textLength, item.length());
                            item = firstItem;
                            source.set(j, item); // the reset value
                            if ((j + 1) < source.size()) {
                                String nextItem = source.get(j + 1);
                                if (TextUtils.isEmpty(nextItem)) {
                                    source.add(lastItem);
                                } else {
                                    source.set(j + 1, String.valueOf(lastItem + nextItem));
                                }
                            } else {
                                source.add(lastItem);
                            }
                            maxWidth = Math.max(maxWidth, Float.valueOf(paint.measureText(item)).intValue());
                        }
                    } else {
                        // nothing to do
                    }
                } else {
                    // not support less than 4.1 version
                }
            } else {
                setMaxWidth(widthSize);
            }
        }
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