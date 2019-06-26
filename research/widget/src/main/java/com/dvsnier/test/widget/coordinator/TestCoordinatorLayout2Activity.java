package com.dvsnier.test.widget.coordinator;

import android.os.Bundle;

import com.dvsnier.base.flavor.activity.BaseActivity;
import com.dvsnier.test.widget.R;

import butterknife.ButterKnife;

public class TestCoordinatorLayout2Activity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_coordinator_layout_2);
        ButterKnife.bind(this);
    }

}
