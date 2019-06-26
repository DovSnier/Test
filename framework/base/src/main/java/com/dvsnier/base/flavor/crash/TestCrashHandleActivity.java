package com.dvsnier.base.flavor.crash;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dvsnier.base.flavor.R;
import com.dvsnier.base.flavor.R2;
import com.dvsnier.base.flavor.activity.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TestCrashHandleActivity extends BaseActivity {

    public static final int MESSAGE_TEST_EXCEPTION = 100;
    public static final int MESSAGE_TEST_OTHER = 1000;

    @BindView(R2.id.btn_exception)
    Button btnException;
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MESSAGE_TEST_EXCEPTION:
                    //test exception example
                    testException();
                    break;
                case MESSAGE_TEST_OTHER:
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    };


    private View.OnClickListener onClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            int viewId = view.getId();
            if (viewId == R.id.btn_exception) {
                Toast.makeText(TestCrashHandleActivity.this, "2s 后开始测试", Toast.LENGTH_SHORT).show();
                handler.sendMessageDelayed(handler.obtainMessage(MESSAGE_TEST_EXCEPTION), 3 * 1000);
            } else {
                // nothing to do
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_crash_handle);
        ButterKnife.bind(this);
        performScheduledInternal();
    }

    @Override
    public void initView() {
        super.initView();
        btnException.setOnClickListener(onClickListener);
    }

    private void testException() {
        Integer integer = new Integer("str123");
    }

}
