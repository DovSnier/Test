package com.dvsnier.testCrash;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dvsnier.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TestCrashHandleActivity extends AppCompatActivity {

    public static final int MESSAGE_TEST_EXCEPTION = 100;
    public static final int MESSAGE_TEST_OTHER = 1000;

    @BindView(R.id.btn_exception)
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
            switch (view.getId()) {
                case R.id.btn_exception:
                    Toast.makeText(TestCrashHandleActivity.this, "2s 后开始测试", Toast.LENGTH_SHORT).show();
                    handler.sendMessageDelayed(handler.obtainMessage(MESSAGE_TEST_EXCEPTION), 3 * 1000);
                    break;
                default:
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_crash_handle);
        ButterKnife.bind(this);
        btnException.setOnClickListener(onClickListener);
    }

    private void testException() {
        Integer integer = new Integer("str123");
    }

}
