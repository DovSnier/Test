package com.dvsnier.testCoordinatorLayout;

import android.os.Bundle;

import com.dvsnier.R;
import com.dvsnier.base.activity.BaseActivity;

import butterknife.ButterKnife;

public class TestCoordinatorLayout3Activity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_coordinator_layout_4);
        ButterKnife.bind(this);
    }

}
