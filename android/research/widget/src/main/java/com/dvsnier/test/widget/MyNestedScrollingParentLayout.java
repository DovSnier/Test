package com.dvsnier.test.widget;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.view.NestedScrollingParent;

/**
 * MyNestedScrollingParentLayout
 * Created by dovsnier on 2020/3/25.
 */
public class MyNestedScrollingParentLayout extends LinearLayout implements NestedScrollingParent {

    protected int titleBarHeight;

    public MyNestedScrollingParentLayout(Context context) {
        super(context);
    }

    public MyNestedScrollingParentLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyNestedScrollingParentLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MyNestedScrollingParentLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    public boolean onStartNestedScroll(@NonNull View child, @NonNull View target, int axes) {
        onLog(String.format("onStartNestedScroll(child: %s, target: %s, axes: %s)", child.getId(), target.getId(), axes));
//        return (getNestedScrollAxes() & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onNestedScrollAccepted(@NonNull View child, @NonNull View target, int axes) {
        onLog(String.format("onNestedScrollAccepted(child: %s, target: %s, axes: %s)", child.getId(), target.getId(), axes));
        super.onNestedScrollAccepted(child, target, axes);
    }

    @Override
    public void onStopNestedScroll(@NonNull View target) {
        onLog(String.format("onStopNestedScroll(target: %s)", target.getId()));
        super.onStopNestedScroll(target);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onNestedScroll(@NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        if (titleBarHeight > 0) {
            // Child View 滚动完成, Parent View 继续滚动Child View 剩下尚未滚动的部分
            if (dyUnconsumed < 0) {
                int dyConsumedAlready = 0;
                int dyUnConsumedAlready = 0;
                int startScrollY = getScrollY();
                if (getScrollY() > 0) {
                    if (Math.abs(dyUnconsumed) >= titleBarHeight) {
                        scrollBy(0, titleBarHeight);
                    } else {
                        scrollBy(0, dyUnconsumed);
                    }
                    dyConsumedAlready = getScrollY() - startScrollY;
                    dyUnConsumedAlready = dyUnconsumed - dyConsumedAlready;
                    onLog(String.format("onNestedScroll(target: %s, dxConsumed: %s, dyConsumed: %s, dxUnconsumed: %s, dyUnconsumed: %s, scrollY: %s)", target.getId(), dxConsumed, dyConsumedAlready, dxUnconsumed, dyUnConsumedAlready, getScrollY()));
                    super.onNestedScroll(target, dxConsumed, dyConsumedAlready, dxUnconsumed, dyUnConsumedAlready);
                } else {
                    if (getChildCount() > 1) {
                        final View child = getChildAt(0);
                        if (child instanceof TextView) {
                            scrollBy(0, child.getTop());
                            dyConsumedAlready = getScrollY() - startScrollY;
                            dyUnConsumedAlready = dyUnconsumed - dyConsumedAlready;
                            onLog(String.format("onNestedScroll(target: %s, dxConsumed: %s, dyConsumed: %s, dxUnconsumed: %s, dyUnconsumed: %s, scrollY: %s)", target.getId(), dxConsumed, dyConsumedAlready, dxUnconsumed, dyUnConsumedAlready, getScrollY()));
                            super.onNestedScroll(target, dxConsumed, dyConsumedAlready, dxUnconsumed, dyUnConsumedAlready);
                        } else {
                            onLog(String.format("onNestedScroll(target: %s, dxConsumed: %s, dyConsumed: %s, dxUnconsumed: %s, dyUnconsumed: %s, scrollY: %s)", target.getId(), dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, getScrollY()));
                            super.onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
                        }
                    } else {
                        onLog(String.format("onNestedScroll(target: %s, dxConsumed: %s, dyConsumed: %s, dxUnconsumed: %s, dyUnconsumed: %s, scrollY: %s)", target.getId(), dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, getScrollY()));
                        super.onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
                    }
                }
            } else {
                onLog(String.format("onNestedScroll(target: %s, dxConsumed: %s, dyConsumed: %s, dxUnconsumed: %s, dyUnconsumed: %s, scrollY: %s)", target.getId(), dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, getScrollY()));
                super.onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
            }
        }
    }

    /**
     * Delta: Up, View: ↓, G: Up
     * Delta: Down, View: ↑, G: Down
     *
     * @param target
     * @param dx
     * @param dy
     * @param consumed
     */
    @Override
    public void onNestedPreScroll(@NonNull View target, int dx, int dy, @NonNull int[] consumed) {
        if (titleBarHeight > 0) {
            if (dy > 0) {
                // Parent View 和Child View 同步滚动
                // Parent View 滚动到给定值,不再同步滚动
                // Child View 持续性滚动
                if (Math.abs(getScrollY()) >= titleBarHeight) {
                    consumed[1] = 0;
                } else {
                    scrollBy(0, dy);
                    consumed[1] = dy;
                }
            } else {
                // Child View 先滚动, Parent View 后续滚动 Child View 未滚动完的量
            }
        }
//        if (titleBarHeight > 0) {
//            if (dy > 0) { // dy > 0, View: ↓, G: Up
//                if (getScrollY() > titleBarHeight) {
//                    consumed[1] = 0;
//                } else {
//                    scrollBy(0, dy);
//                    consumed[1] = dy;
//                }
//            } else if (dy < 0) { // dy < 0, View: ↑, G: Down
//                if (getScrollY() > titleBarHeight) {
//                    consumed[1] = 0;
//                } else {
//                    scrollBy(0, dy);
//                    consumed[1] = dy;
//                }
//            } else {
//                consumed[1] = 0;
//            }
//        }
        onLog(String.format("onNestedPreScroll(target: %s, dx: %s, dy: %s, consumed: %s, scrollY: %s)", target.getId(), dx, dy, consumed[0] + "," + consumed[1], getScrollY()));
        super.onNestedPreScroll(target, dx, dy, consumed);
    }

    @Override
    public boolean onNestedFling(@NonNull View target, float velocityX, float velocityY, boolean consumed) {
        onLog(String.format("onNestedFling(target: %s, velocityX: %s, velocityY: %s, consumed: %s)", target.getId(), velocityX, velocityY, consumed));
        return super.onNestedFling(target, velocityX, velocityY, consumed);
    }

    @Override
    public boolean onNestedPreFling(@NonNull View target, float velocityX, float velocityY) {
        onLog(String.format("onNestedPreFling(target: %s, velocityX: %s, velocityY: %s)", target.getId(), velocityX, velocityY));
        return super.onNestedPreFling(target, velocityX, velocityY);
    }

    @Override
    public int getNestedScrollAxes() {
        int nestedScrollAxes = super.getNestedScrollAxes();
//        onLog(String.format("getNestedScrollAxes(nestedScrollAxes: %s)", nestedScrollAxes));
        return nestedScrollAxes;
    }

    public void onLog(String msg) {
        Log.w(this.getClass().getSimpleName(), msg);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() > 1) {
            final View child = getChildAt(0);
            if (child instanceof TextView) {
                child.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            child.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        }
                        titleBarHeight = child.getHeight();
                    }
                });
            }
        }
    }
}
