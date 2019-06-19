package com.dvsnier.test.widget.surfaceview;

import android.os.Bundle;

import com.dvsnier.base.flavor.activity.BaseActivity;
import com.dvsnier.test.widget.MySurfaceView;

/**
 * TestSurfaceActivity
 */
public class TestSurfaceActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_test_surface);
        setContentView(new MySurfaceView(this));
    }
}
