package com.dvsnier.test.view.affinity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.dvsnier.base.flavor.activity.BaseActivity;
import com.dvsnier.test.view.R;
import com.dvsnier.test.view.R2;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AActivity extends BaseActivity {

    @BindView(R2.id.btn_affinity_b)
    Button btnAffinityB;
    @BindView(R2.id.btn_c)
    Button btnC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);
        ButterKnife.bind(this);
    }

    @OnClick({R2.id.btn_affinity_b, R2.id.btn_c})
    public void onClick(View view) {
        Intent intent = null;
        int viewId = view.getId();
        if (viewId == R.id.btn_affinity_b) {
            intent = new Intent(this, BActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else if (viewId == R.id.btn_c) {
            intent = new Intent(this, CActivity.class);
            startActivity(intent);
        } else {
            // nothing to do
        }
    }
}
