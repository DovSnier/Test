package com.dvsnier;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.dvsnier.testAffinity.AffinityActivity;
import com.dvsnier.testCache.TestCacheActivity;
import com.dvsnier.testCrash.TestCrashHandleActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * the test main activity
 *
 * @author dvsnier
 * @since JDK 1.8
 */
public class MainActivity extends AppCompatActivity {

    @Bind(R.id.btn_exception)
    Button btn_exception;
    @Bind(R.id.btn_affinity)
    Button btn_affinity;
    @Bind(R.id.btn_cache)
    Button btn_cache;

    private View.OnClickListener onClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            Intent intent = null;
            switch (view.getId()) {
                case R.id.btn_exception: // TODO test exception component
                    intent = new Intent(MainActivity.this, TestCrashHandleActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent);
                    break;
                case R.id.btn_affinity: // TODO test activity affinity component
                    intent = new Intent(MainActivity.this, AffinityActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent);
                    break;
                case R.id.btn_cache: // TODO test activity cache component
                    intent = new Intent(MainActivity.this, TestCacheActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent);
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        btn_exception.setOnClickListener(onClickListener);
        btn_affinity.setOnClickListener(onClickListener);
        btn_cache.setOnClickListener(onClickListener);
    }


}
