package com.dvsnier.test.common.touch;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.dvsnier.base.flavor.activity.BaseActivity;
import com.dvsnier.test.common.R;

public class TestTouchEventActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_touch_event);
    }
}
