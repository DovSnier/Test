package com.dvsnier.test.common.aidl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.dvsnier.test.common.R;
import com.dvsnier.test.common.service.MessageManagerService;

public class TestAIDLActivity extends AppCompatActivity {

    protected static final String TAG = TestAIDLActivity.class.getSimpleName();
    protected ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            IMessageManager messageManager = IMessageManager.Stub.asInterface(service);
            try {
                String value = "" + System.currentTimeMillis();
                int count = (int) (Math.random() * 100);
                for (int i = 0; i < count; i++) {
                    messageManager.updateMsg(value + i);
                }
                String msg = messageManager.getMsg();
                Log.d(TAG, "the current(sum:" + count + ") obtain msg(" + value + ") is " + msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_aidl);
        Intent intent = new Intent(this, MessageManagerService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(TestAIDLActivity.this, "开始测试 AIDL", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        unbindService(serviceConnection);
        super.onDestroy();
    }
}
