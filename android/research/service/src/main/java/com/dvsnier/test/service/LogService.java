package com.dvsnier.test.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.dvsnier.ICacheType;
import com.dvsnier.cache.CacheManager;
import com.dvsnier.cache.base.TimeUnit;
import com.dvsnier.test.utils.MD5;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 日志服务
 */
public class LogService extends Service {

    public static final String TAG = "LogService";
    public static final String SERVICE_ID = "id";
    protected Map<String, Handler> dataSet = new HashMap<>();

    public LogService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        String value = String.format("the log server is %s.", "onCreate()");
        Log.d(TAG, value);
        write(value);
        dataSet.clear();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String value = String.format("the log server is %s.", "onStartCommand()");
        Log.d(TAG, value);
        write(value);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        String id = intent.getStringExtra(SERVICE_ID);
        String value = String.format("the log server is %s that the service id is %s.", "onBind()", id);
        Log.d(TAG, value);
        write(value);
        LogHandle target = new LogHandle(this, id);
        Messenger senderMessenger = new Messenger(target);
        if (TextUtils.isEmpty(id)) {
            Log.w(TAG, String.format("the log server id is %s.", "illegal operation"));
            id = MD5.encode(senderMessenger.toString());
            target.setId(id);
        }
        if (dataSet.containsKey(id)) {
            // nothing to do
        } else {
            dataSet.put(id, target);
        }
        return senderMessenger.getBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        String id = intent.getStringExtra(SERVICE_ID);
        String value = String.format("the log server is %s that the service id is %s.", "onUnbind()", id);
        Log.d(TAG, value);
        write(value);
//        此处无需停止服务
//        Handler handleForId = getHandleForId(id);
//        if (null != handleForId) {
//            handleForId.sendEmptyMessage(IServer.TYPE_STOP_PRINT_MSG);
//        }
        removeMessageForId(id);
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        String value = String.format("the log server is %s.", "onDestroy()");
        Log.d(TAG, value);
        write(value);
        clearServerMessage();
        super.onDestroy();
    }

    protected void write(String value) {
        CacheManager.getInstance().putString(ICacheType.TYPE_LOG_SERVICE, MD5.encode(getClass().getSimpleName() + System.currentTimeMillis()), value, 2, TimeUnit.WEEKS).commit();
    }

    protected Handler getHandleForId(String id) {
        if (dataSet.containsKey(id)) {
            return dataSet.get(id);
        }
        return null;
    }

    protected void removeMessageForId(String id) {
        if (dataSet.containsKey(id)) {
            Handler handler = dataSet.remove(id);
            if (null != handler) {
                handler.removeCallbacksAndMessages(null);
                handler = null;
            }
        }
    }

    protected void clearServerMessage() {
        if (!dataSet.isEmpty()) {
            Iterator<Handler> iterator = dataSet.values().iterator();
            while (iterator.hasNext()) {
                Handler handler = iterator.next();
                if (null != handler) {
                    handler.removeCallbacksAndMessages(null);
                    handler = null;
                }
            }
        }
    }

    public void onTips(String msg) {
        if (!TextUtils.isEmpty(msg)) {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }
    }

    class LogHandle extends Handler implements IServer {

        protected Context context;
        protected String id;
        protected Messenger receiverMessenger;

        public LogHandle(Context context) {
            this.context = context;
        }

        public LogHandle(Context context, String id) {
            this(context);
            this.id = id;
        }

        @Override
        public void handleMessage(Message msg) {
            if (null != msg.replyTo) {
                receiverMessenger = msg.replyTo;
            }
            Bundle bundle = (Bundle) msg.obj;
            String obj = null;
            if (null != bundle) {
                obj = bundle.getString("msg");
            }
            switch (msg.what) {
                case TYPE_SEND_MSG:
                    Log.d(TAG, String.format("the message is %s.", "TYPE_SEND_MSG"));
                    Log.d(TAG, String.format("the message is %s.", obj));
                    String value = String.format("the thread name: %s , time: %s , that from log server.", Thread.currentThread().getName(), System.currentTimeMillis());
                    sendMessage(TYPE_REPLY_TO_MSG, value);
                    write(value);
                    break;
                case TYPE_REPLY_TO_MSG:
                    Log.d(TAG, String.format("the message is %s.", "TYPE_REPLY_TO_MSG"));
                    Log.d(TAG, String.format("the message is %s.", obj));
                    Message message = Message.obtain();
                    message.what = TYPE_START_PRINT_MSG;
                    sendMessageDelayed(message, 1500);
                    write(obj);
                    break;
                case TYPE_START_PRINT_MSG:
                    Log.d(TAG, String.format("the message is %s.", "TYPE_START_PRINT_MSG"));
                    String startPrintMsg = "开始...打印...消息";
                    Log.d(TAG, startPrintMsg);
                    onTips(startPrintMsg);

                    Message startPrintMessage = Message.obtain();
                    startPrintMessage.what = TYPE_PRINT_MSG;
                    sendMessageDelayed(startPrintMessage, 1500);
                    write(startPrintMsg);
                    break;
                case TYPE_PRINT_MSG:
                    String printMsg = String.format("打印消息中(%s)...", System.currentTimeMillis());
                    Log.d(TAG, printMsg);
//                    onTips(printMsg);
                    Message printMessage = Message.obtain();
                    printMessage.what = TYPE_PRINT_MSG;
                    sendMessageDelayed(printMessage, 3000);
                    sendMessage(TYPE_PRINT_MSG, printMsg);
                    write(printMsg);
                    break;
                case TYPE_STOP_PRINT_MSG:
                    Log.d(TAG, String.format("the message is %s.", "TYPE_STOP_PRINT_MSG"));
                    String stopPrintMsg = "停止...打印...消息";
                    Log.d(TAG, stopPrintMsg);
                    onTips(stopPrintMsg);
                    postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            stopSelf();
                        }
                    }, 1500);
                    write(stopPrintMsg);
                    break;
                default:
                    super.handleMessage(msg);
            }
        }

        protected void sendMessage(int what, String msg) {
            if (null != receiverMessenger) {
                Message message = Message.obtain();
                message.what = what;

                Bundle sendBundle = new Bundle();
                sendBundle.putString("msg", msg);
                message.obj = sendBundle;
                try {
                    receiverMessenger.send(message);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }

        public Context getContext() {
            return context;
        }

        public void setContext(Context context) {
            this.context = context;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

    public static interface IServer {
        int TYPE_DEFAULT_MSG = 1;
        int TYPE_SEND_MSG = 200;
        int TYPE_REPLY_TO_MSG = 300;
        int TYPE_START_PRINT_MSG = 501;
        int TYPE_PRINT_MSG = 502;
        int TYPE_STOP_PRINT_MSG = 510;
    }
}
