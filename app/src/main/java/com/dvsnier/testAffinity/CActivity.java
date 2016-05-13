package com.dvsnier.testAffinity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.dvsnier.test.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CActivity extends AppCompatActivity {

    @Bind(R.id.btn_affinity_a)
    Button btnAffinityA;
    @Bind(R.id.btn_affinity_b)
    Button btnAffinityB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_affinity_a, R.id.btn_affinity_b})
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.btn_affinity_a:
                intent = new Intent(this, AActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
            case R.id.btn_affinity_b:
                intent = new Intent(this, BActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
        }
    }
}
