package com.dvsnier.test.common.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.TextView;

import com.dvsnier.base.flavor.activity.BaseActivity;
import com.dvsnier.base.flavor.crash.TestCrashHandleActivity;
import com.dvsnier.test.common.R;
import com.dvsnier.test.common.R2;
import com.dvsnier.test.common.permission.TestPermissionActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * TestNotificationActivity
 * Created by dovsnier on 2020/7/13.
 */
public class TestNotificationActivity extends BaseActivity {

    @BindView(R2.id.tv_1)
    TextView tv1;
    @BindView(R2.id.tv_2)
    TextView tv2;
    protected Unbinder unbinder;
    protected int identifier;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_notification);
        unbinder = ButterKnife.bind(this);
        performScheduledInternal();
    }

    @Override
    public void initView() {
    }


    @OnClick({R2.id.tv_1, R2.id.tv_2})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.tv_1) {
            buildChatNotification();
        } else if (id == R.id.tv_2) {
            buildExceptionNotification();
        }
    }

    public void buildChatNotification() {
        Intent intent = new Intent(this, TestPermissionActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        int icon = getResources().getIdentifier("ic_launcher", "mipmap", getPackageName());
//        String title = getString(R.string.chat_title);
        String title = getString(getResources().getIdentifier("app_name", "string", getPackageName()));
        String channel_name = getString(R.string.channel_chat_name);
        String channel_id = getString(R.string.channel_chat_id);
        String group_name = getString(R.string.channel_chat_group_name);
        String group_id = getString(R.string.channel_chat_group_id);
        String channel_describe = getString(R.string.channel_chat_describe);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(channel_id, channel_name,
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription(channel_describe);
            notificationChannel.enableLights(true);
//            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            NotificationChannelGroup notificationChannelGroup = new NotificationChannelGroup(group_id, group_name);
            notificationChannel.setGroup(group_id);

            if (null != notificationManager) {
                notificationManager.createNotificationChannelGroup(notificationChannelGroup);
                notificationManager.createNotificationChannel(notificationChannel);
            }
        }
        Notification notification = new NotificationCompat.Builder(this, channel_id)
                .setSmallIcon(icon)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), icon))
                .setAutoCancel(true)
                .setBadgeIconType(NotificationCompat.BADGE_ICON_SMALL)
//                .setChannelId(channel_id)
                .setContentTitle(title)
                .setContentText(getString(R.string.chat_content))
                .setContentIntent(pendingIntent)
                .setWhen(System.currentTimeMillis())
//                .setNumber(1)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .build();
        if (null != notificationManager) {
            notificationManager.notify(identifier++, notification);
        }
    }

    public void buildExceptionNotification() {
        Intent intent = new Intent(this, TestCrashHandleActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        int icon = getResources().getIdentifier("ic_launcher", "mipmap", getPackageName());
//        String title = getString(R.string.exception_title);
        String title = getString(getResources().getIdentifier("app_name", "string", getPackageName()));
        String channel_name = getString(R.string.channel_exception_name);
        String channel_id = getString(R.string.channel_exception_id);
        String group_name = getString(R.string.channel_exception_group_name);
        String group_id = getString(R.string.channel_exception_group_id);
        String channel_describe = getString(R.string.channel_exception_describe);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(channel_id, channel_name,
                    NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.setDescription(channel_describe);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            NotificationChannelGroup notificationChannelGroup = new NotificationChannelGroup(group_id, group_name);
            notificationChannel.setGroup(group_id);

            if (null != notificationManager) {
                notificationManager.createNotificationChannelGroup(notificationChannelGroup);
                notificationManager.createNotificationChannel(notificationChannel);
            }
        }
        Notification notification = new NotificationCompat.Builder(this, channel_id)
                .setSmallIcon(icon)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), icon))
                .setAutoCancel(true)
                .setBadgeIconType(NotificationCompat.BADGE_ICON_SMALL)
//                .setChannelId(channel_id)
                .setContentTitle(title)
                .setContentText(getString(R.string.exception_content))
                .setContentIntent(pendingIntent)
                .setWhen(System.currentTimeMillis())
//                .setNumber(1)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .build();
        if (null != notificationManager) {
            notificationManager.notify(identifier++, notification);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}