package com.dvsnier.testScroll;

import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.dvsnier.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TestScrollActivity extends AppCompatActivity {

    protected static final String TAG = TestScrollActivity.class.getSimpleName();
    @Bind(R.id.scroll_container)
    LinearLayout scrollContainer;
    @Bind(R.id.test_scroll)
    TextView testScroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_scroll);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            scrollContainer.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                @Override
                public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                        v.setScrollIndicators(View.SCROLL_INDICATOR_TOP);
//                    }
                    if (v instanceof LinearLayout) {
                        ViewParent parent = v.getParent();
                        LinearLayout linearLayout = (LinearLayout) v;
                        final int childCount = linearLayout.getChildCount();
                        View childAtLast = linearLayout.getChildAt(childCount - 1);
                        if (childCount > 0 && null != childAtLast) {
                            int componentHight = childAtLast.getBottom() - childAtLast.getTop();
                            int componentWidth = childAtLast.getRight() - childAtLast.getLeft();
                            if (parent instanceof ScrollView) {
                                ScrollView scrollView = (ScrollView) parent;
                                int distance = childCount * componentHight;
                                scrollView.scrollTo(0, distance);
                                String msg = "total scroll distance:" + distance + "\tcomponentWidth: " + componentWidth + " & componentHight: " + componentHight;
                                Log.d(TAG, msg);
                                String toast = "total scroll distance:" + distance + "\ncomponentWidth: " + componentWidth + "\n componentHight: " + componentHight;
                                Toast.makeText(TestScrollActivity.this, toast, Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
            });
        } else {
//            scrollContainer.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//                @Override
//                public void onGlobalLayout() {
//
//                }
//            });
        }
    }

    protected TextView obtainView() {
        TextView textView = new TextView(this);
        int dimension = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, getResources().getDisplayMetrics());
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dimension);
        textView.setLayoutParams(params);
        textView.setText("测试" + SystemClock.currentThreadTimeMillis());
        textView.setGravity(Gravity.CENTER);
        return textView;
    }

    @OnClick(R.id.test_scroll)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.test_scroll:
                scrollContainer.addView(obtainView());
                break;
        }
    }
}
