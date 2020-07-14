package com.dvsnier.test.common.notification;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.dvsnier.base.flavor.activity.BaseActivity;
import com.dvsnier.test.common.R;
import com.dvsnier.test.common.R2;

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
    private Unbinder unbinder;

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

    }

    public void buildExceptionNotification() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}