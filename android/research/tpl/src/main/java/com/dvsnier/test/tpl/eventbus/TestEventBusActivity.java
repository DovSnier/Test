package com.dvsnier.test.tpl.eventbus;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dvsnier.base.flavor.activity.BaseActivity;
import com.dvsnier.bean.MessageEvent;
import com.dvsnier.test.tpl.R;
import com.dvsnier.test.tpl.R2;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TestEventBusActivity extends BaseActivity {

    @BindView(R2.id.start)
    Button start;
    @BindView(R2.id.stop)
    Button stop;
    @BindView(R2.id.cancel)
    Button cancel;
    @BindView(R2.id.content)
    TextView content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_event_bus);
        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessage(MessageEvent event) {
        final String text = event.getMessage() + " -> 接受完成";
        setContent(text);
        final String finished = "完成测试";
        onToast(finished);
        onLog(finished);
    }

    protected void setContent(String text) {
        content.setText(text);
    }

    protected void post() {
        final String msg = "开始测试";
        onLog(msg);
        task.execute(msg);
    }

    @SuppressLint("StaticFieldLeak")
    private final AsyncTask<String, Void, Void> task = new AsyncTask<String, Void, Void>() {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            onShowProgress();
        }

        @Override
        protected Void doInBackground(String... params) {
            final String msg = params[0] + " -> 准备运行中...";
            final MessageEvent messageEvent = new MessageEvent();
            messageEvent.setMessage(msg);
            try {
                Thread.sleep(2500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            MessageEvent.post(messageEvent);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            onDismissProgress();
        }
    };

    @OnClick({R2.id.start, R2.id.stop, R2.id.cancel})
    public void onViewClicked(View view) {
        int viewId = view.getId();
        if (viewId == R.id.start) {
            if (AsyncTask.Status.FINISHED == task.getStatus()
                    || AsyncTask.Status.PENDING == task.getStatus()) {
                onToast("3s 后开始测试");
                getWindow().getDecorView().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        post();
                    }
                }, 3000);
            }
        } else if (viewId == R.id.stop) {
        } else if (viewId == R.id.cancel) {
            final String msg = "已取消";
            onToast(msg);
            setContent(msg);
            onLog(msg);
            EventBus.getDefault().cancelEventDelivery(MessageEvent.class);
        }
    }
}
