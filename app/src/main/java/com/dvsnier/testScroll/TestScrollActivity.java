package com.dvsnier.testScroll;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dvsnier.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TestScrollActivity extends AppCompatActivity {

    @Bind(R.id.scroll_container)
    LinearLayout scrollContainer;
    @Bind(R.id.test_scroll)
    TextView testScroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_scroll);
        ButterKnife.bind(this);
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
