package com.dvsnier.testEventBus;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.dvsnier.R;
import com.dvsnier.base.activity.BaseActivity;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class TestEventBusActivity extends BaseActivity {

  @Bind(R.id.start) Button start;
  @Bind(R.id.stop) Button stop;
  @Bind(R.id.cancel) Button cancel;
  @Bind(R.id.content) TextView content;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_test_event_bus);
    ButterKnife.bind(this);
  }

  @Override protected void onStart() {
    super.onStart();
    EventBus.getDefault().register(this);
  }

  @Override protected void onStop() {
    super.onStop();
    EventBus.getDefault().unregister(this);
  }

  @Subscribe(threadMode = ThreadMode.MAIN) public void onMessage(MessageEvent event) {
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

  private final AsyncTask<String, Void, Void> task = new AsyncTask<String, Void, Void>() {

    @Override protected void onPreExecute() {
      super.onPreExecute();
      onShowProgress();
    }

    @Override protected Void doInBackground(String... params) {
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

    @Override protected void onPostExecute(Void aVoid) {
      super.onPostExecute(aVoid);
      onDismissProgress();
    }
  };

  @OnClick({ R.id.start, R.id.stop, R.id.cancel }) public void onViewClicked(View view) {
    switch (view.getId()) {
      case R.id.start:
        if (AsyncTask.Status.FINISHED == task.getStatus()
            || AsyncTask.Status.PENDING == task.getStatus()) {
          onToast("3s 后开始测试");
          getWindow().getDecorView().postDelayed(new Runnable() {
            @Override public void run() {
              post();
            }
          }, 3000);
        }
        break;
      case R.id.stop:
        break;
      case R.id.cancel:
        final String msg = "已取消";
        onToast(msg);
        setContent(msg);
        onLog(msg);
        EventBus.getDefault().cancelEventDelivery(MessageEvent.class);
        break;
    }
  }
}
