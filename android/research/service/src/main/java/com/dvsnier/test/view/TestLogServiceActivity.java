package com.dvsnier.test.view;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dvsnier.base.flavor.activity.BaseActivity;
import com.dvsnier.test.adapter.MessageAdapter;
import com.dvsnier.test.presenter.TestLogServicePresenter;
import com.dvsnier.test.service.LogService;
import com.dvsnier.test.service.R;
import com.dvsnier.test.service.R2;
import com.dvsnier.test.utils.MD5;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * TestLogServiceActivity
 * Created by dovsnier on 2020/5/13.
 */
public class TestLogServiceActivity extends BaseActivity<TestLogServicePresenter> implements LogService.IServer {

    @BindView(R2.id.tv_start)
    TextView tvStart;
    @BindView(R2.id.tv_stop)
    TextView tvStop;
    @BindView(R2.id.recyclerView)
    RecyclerView recyclerView;
    protected MessageAdapter adapter;
    protected Messenger senderMessenger;
    protected Messenger receiverMessenger;
    protected Handler handler;
    protected ServiceConnection serviceConnection;
    protected boolean isServiceConnection;
    protected boolean isBindService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_log_service);
        ButterKnife.bind(this);
        performScheduledInternal();
    }

    @Override
    public void initView() {
        handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                Bundle bundle = (Bundle) msg.obj;
                String obj = null;
                if (null != bundle) {
                    obj = bundle.getString("msg");
                }
                switch (msg.what) {
                    case TYPE_REPLY_TO_MSG:
                        Log.d(TAG, String.format("the is %s.", "TYPE_REPLY_TO_MSG"));
                        onLog(obj);
                        onToast(obj);

                        Message message = Message.obtain();
                        message.what = TYPE_REPLY_TO_MSG;
                        Bundle sendBundle = new Bundle();
                        sendBundle.putString("msg", "the receive message.");
                        message.obj = sendBundle;
                        message.replyTo = receiverMessenger;
                        try {
                            senderMessenger.send(message);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                        break;
                    case TYPE_PRINT_MSG:
                        Log.d(TAG, String.format("the is %s.", "TYPE_PRINT_MSG"));
                        if (!TextUtils.isEmpty(obj)) {
                            onLog(obj);
                            if (null != adapter) {
                                adapter.getData().add(0, String.format("%s", obj));
                                adapter.notifyDataSetChanged();
                            }
                        }
                        break;
                    case TYPE_STOP_PRINT_MSG:
                        if (isBindService) {
                            unbindService(serviceConnection);
                            isBindService = false;
                        }
                        break;
                    default:
                        super.handleMessage(msg);
                }
            }
        };
        receiverMessenger = new Messenger(handler);
        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                isServiceConnection = true;
                isBindService = true;
                onLog(String.format("the current service(%s) has been established", "onServiceConnected()"));
                onLog(name.getShortClassName());
                senderMessenger = new Messenger(service);
                IBinder binder = senderMessenger.getBinder();
                Message message = Message.obtain();
                message.what = LogService.IServer.TYPE_SEND_MSG;
                Bundle bundle = new Bundle();
                bundle.putString("msg", "the Hello Log Server，my name is from test log server activity.");
                message.obj = bundle;
                message.replyTo = receiverMessenger;
                try {
                    senderMessenger.send(message);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                isServiceConnection = false;
                onLog("onServiceDisconnected()");
            }
        };
        adapter = new MessageAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(true);
        DividerItemDecoration decor = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        //noinspection ConstantConditions
        decor.setDrawable(ContextCompat.getDrawable(this, R.drawable.bg_divider_shape));
        recyclerView.addItemDecoration(decor);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        if (isBindService) {
            Message message = Message.obtain();
            message.what = TYPE_STOP_PRINT_MSG;
            handler.sendMessage(message);
        }
        getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                handler.removeCallbacksAndMessages(null);
                serviceConnection = null;
                receiverMessenger = null;
                senderMessenger = null;
                handler = null;
            }
        }, 50);
        super.onDestroy();
    }

    @OnClick({R2.id.tv_start, R2.id.tv_stop})
    public void onViewClicked(View view) {
        int viewId = view.getId();
        if (viewId == R.id.tv_start) {
            if (!isBindService) { // 单点连接服务
                Intent service = new Intent(this, LogService.class);
                service.putExtra(LogService.SERVICE_ID, MD5.encode(service.toString()));
                bindService(service, serviceConnection, BIND_AUTO_CREATE);
            }
        } else if (viewId == R.id.tv_stop) {
            if (isBindService) {
                Message message = Message.obtain();
                message.what = TYPE_STOP_PRINT_MSG;
                handler.sendMessageDelayed(message, 150);
            }
        }
    }
}