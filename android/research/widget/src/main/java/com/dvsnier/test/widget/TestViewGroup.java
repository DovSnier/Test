package com.dvsnier.test.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.dvsnier.common.compat.ICompatView;

/**
 * TestViewGroup
 * Created by dovsnier on 2020/6/19.
 */
public class TestViewGroup extends ViewGroup implements ICompatView {

    protected final String TAG = "TestViewGroup";

    private static final int DEFAULT_CHILD_GRAVITY = Gravity.TOP | Gravity.START;

    protected int spacing;

    public TestViewGroup(Context context) {
        super(context);
        obtainStyledAttributes(context.getTheme().obtainStyledAttributes(R.styleable.TestViewGroup));
        initView();
    }

    public TestViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        obtainStyledAttributes(context.getTheme().obtainStyledAttributes(attrs, R.styleable.TestViewGroup, 0, 0));
        initView();
    }

    public TestViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        obtainStyledAttributes(context.getTheme().obtainStyledAttributes(attrs, R.styleable.TestViewGroup, defStyleAttr, 0));
        initView();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TestViewGroup(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        obtainStyledAttributes(context.getTheme().obtainStyledAttributes(attrs, R.styleable.TestViewGroup, defStyleAttr, defStyleRes));
        initView();
    }

    public void obtainStyledAttributes(@NonNull TypedArray typedArray) {
        //noinspection ConstantConditions
        if (null != typedArray) {
            spacing = typedArray.getDimensionPixelSize(R.styleable.TestViewGroup_spacing, 0);
            typedArray.recycle();
        }
    }

    @Override
    public void initView() {
    }

    @Override
    protected MarginLayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int childWidth = 0, childHeight = 0;
        int childCount = getChildCount();
//        measureChildren(widthMeasureSpec, heightMeasureSpec);
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (null != childAt) {
//                MarginLayoutParams layoutParams = (MarginLayoutParams) childAt.getLayoutParams();
                LayoutParams layoutParams = childAt.getLayoutParams();
                measureChild(childAt, widthMeasureSpec, heightMeasureSpec);
//                childWidth = Math.max(childWidth, childAt.getWidth() + layoutParams.leftMargin + layoutParams.rightMargin);
                childWidth = Math.max(childWidth, childAt.getMeasuredWidth());
                Log.d(TAG, String.format("childWidth(%s): %s", i, childWidth));
//                childHeight = Math.max(childHeight, childAt.getHeight() + layoutParams.topMargin + layoutParams.bottomMargin);
                childHeight = Math.max(childHeight, childAt.getMeasuredHeight());
                Log.d(TAG, String.format("childHeight(%s): %s", i, childHeight));
            }
        }

        int measureWidth = widthMode == MeasureSpec.EXACTLY ? width : childWidth;
        int measureHeight = heightMode == MeasureSpec.EXACTLY ? height : childHeight;
        setMeasuredDimension(measureWidth, measureHeight);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        layoutChildren(left, top, right, bottom, false /* no force left gravity */);
    }

    public void layoutChildren(int left, int top, int right, int bottom, boolean forceLeftGravity) {
        final int count = getChildCount();

//        final int parentLeft = getPaddingLeftWithForeground();
//        final int parentRight = right - left - getPaddingRightWithForeground();
        final int parentLeft = 0;
        final int parentRight = right - left;

//        final int parentTop = getPaddingTopWithForeground();
//        final int parentBottom = bottom - top - getPaddingBottomWithForeground();
        final int parentTop = 0;
        final int parentBottom = bottom - top;

        for (int i = 0; i < count; i++) {
            final View child = getChildAt(i);
            if (child.getVisibility() != GONE) {
                LayoutParams lp = child.getLayoutParams();

                final int width = child.getMeasuredWidth();
                final int height = child.getMeasuredHeight();

                int childLeft;
                int childTop;

                int gravity = 0;
                if (gravity == -1) {
                    gravity = DEFAULT_CHILD_GRAVITY;
                }

                int layoutDirection = 0;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    layoutDirection = getLayoutDirection();
                }
                int absoluteGravity = Gravity.getAbsoluteGravity(gravity, layoutDirection);
                final int verticalGravity = gravity & Gravity.VERTICAL_GRAVITY_MASK;

                switch (absoluteGravity & Gravity.HORIZONTAL_GRAVITY_MASK) {
                    case Gravity.CENTER_HORIZONTAL:
                        childLeft = parentLeft + (parentRight - parentLeft - width) / 2;

                        break;
                    case Gravity.RIGHT:
                        if (!forceLeftGravity) {
                            childLeft = parentRight - width;
                            break;
                        }
                    case Gravity.LEFT:
                    default:
                        childLeft = parentLeft;
                }

                switch (verticalGravity) {
                    case Gravity.TOP:
                        childTop = parentTop;
                        break;
                    case Gravity.CENTER_VERTICAL:
                        childTop = parentTop + (parentBottom - parentTop - height) / 2;
                        break;
                    case Gravity.BOTTOM:
                        childTop = parentBottom - height;
                        break;
                    default:
                        childTop = parentTop;
                }

                child.layout(childLeft, childTop, childLeft + width, childTop + height);
            }
        }
    }

    public static int getAbsoluteGravity(int gravity, int layoutDirection) {
        int result = gravity;
        // If layout is script specific and gravity is horizontal relative (START or END)
        if ((result & Gravity.RELATIVE_LAYOUT_DIRECTION) > 0) {
            if ((result & Gravity.START) == Gravity.START) {
                // Remove the START bit
                result &= ~Gravity.START;
                if (layoutDirection == View.LAYOUT_DIRECTION_RTL) {
                    // Set the RIGHT bit
                    result |= Gravity.RIGHT;
                } else {
                    // Set the LEFT bit
                    result |= Gravity.LEFT;
                }
            } else if ((result & Gravity.END) == Gravity.END) {
                // Remove the END bit
                result &= ~Gravity.END;
                if (layoutDirection == View.LAYOUT_DIRECTION_RTL) {
                    // Set the LEFT bit
                    result |= Gravity.LEFT;
                } else {
                    // Set the RIGHT bit
                    result |= Gravity.RIGHT;
                }
            }
            // Don't need the script specific bit any more, so remove it as we are converting to
            // absolute values (LEFT or RIGHT)
            result &= ~Gravity.RELATIVE_LAYOUT_DIRECTION;
        }
        return result;
    }

    public int getSpacing() {
        return spacing;
    }

    public void setSpacing(int spacing) {
        this.spacing = spacing;
    }
}