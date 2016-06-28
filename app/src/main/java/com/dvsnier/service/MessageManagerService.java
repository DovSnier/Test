package com.dvsnier.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.dvsnier.aidl.IMessageManager;

import java.util.concurrent.CopyOnWriteArrayList;

public class MessageManagerService extends Service {

    protected static final String TAG = MessageManagerService.class.getSimpleName();
    protected CopyOnWriteArrayList<String> container = new CopyOnWriteArrayList<>();

    public MessageManagerService() {
    }

    protected Binder serviceBinder = new IMessageManager.Stub() {

        @Override
        public String getMsg() throws RemoteException {
            StringBuilder sb = new StringBuilder(System.currentTimeMillis() + "|");
            for (String item : container) {
                sb.append(item + " ");
            }
            return sb.toString();
        }

        @Override
        public void updateMsg(String msg) throws RemoteException {
            container.add(msg);
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return serviceBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "the " + TAG + " is create...");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "the " + TAG + " is destroy...");
    }
}
