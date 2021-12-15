package com.dvsnier.test.widget.coordinator;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.dvsnier.test.widget.R;
import com.dvsnier.test.widget.R2;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TestCoordinatorLayoutActivity extends AppCompatActivity {

    @BindView(R2.id.floating_action_button)
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_coordinator_layout);
        ButterKnife.bind(this);
    }

    @OnClick(R2.id.floating_action_button)
    public void onViewClicked(View view) {
        Snackbar.make(view, "Test", Snackbar.LENGTH_LONG).setAction("done", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TestCoordinatorLayoutActivity.this, "TestCoordinatorLayoutActivity",
                        Toast.LENGTH_SHORT).show();
            }
        }).show();
    }
}
