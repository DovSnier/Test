package com.dvsnier.test.widget.view;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.dvsnier.base.flavor.activity.BaseActivity;
import com.dvsnier.test.widget.R;
import com.dvsnier.test.widget.R2;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * TestViewSizeActivity
 * Created by machine code on 2020/2/5.
 */
public class TestViewSizeActivity extends BaseActivity {

    @BindView(R2.id.content)
    TextView content;
    @BindView(R2.id.tv_one)
    TextView tvOne;
    @BindView(R2.id.tv_two)
    TextView tvTwo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_view_size);
        ButterKnife.bind(this);
        performScheduledInternal();
    }


    @Override
    public void initView() {
        super.initView();
        tvOne.setText("职能赋能里正式");
        tvTwo.setText("管理员");
    }

    @Override
    public void initData() {
        super.initData();
        float measureTextOne = tvOne.getPaint().measureText(tvOne.getText().toString());
        float measureTextTwo = tvTwo.getPaint().measureText(tvTwo.getText().toString());
        Rect measuredSizeOne = getMeasuredSize(tvOne);
        Rect measuredSizeTwo = getMeasuredSize(tvTwo);
        int widthOne = measuredSizeOne.width();
        int widthTwo = measuredSizeTwo.width();
        String msg = "measureTextOne: " + measureTextOne + "\n"
                + "measureTextTwo: " + measureTextTwo + "\n"
                + "widthOne: " + widthOne + "\n"
                + "widthTwo: " + widthTwo + "\n";
        content.setText(msg);
        onTips("the test view size.");
    }

    public void onTips(@NonNull String msg) {
        if (!TextUtils.isEmpty(msg)) {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }
    }


    public Rect getMeasuredSize(View view) {
        Rect rect = new Rect();
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(w, h);
        int width = view.getMeasuredWidth();
        int height = view.getMeasuredHeight();
        rect.right = width;
        rect.bottom = height;
        return rect;
    }

}