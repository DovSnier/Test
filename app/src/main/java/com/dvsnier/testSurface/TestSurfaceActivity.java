package com.dvsnier.testSurface;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.dvsnier.view.MySurfaceView;

public class TestSurfaceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_test_surface);
        setContentView(new MySurfaceView(this));
    }
}
