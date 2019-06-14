package com.dvsnier.testOkhttp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.dvsnier.DvsnierApplication;
import com.dvsnier.R;
import com.facebook.stetho.common.LogUtil;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TestOkhttpActivity extends AppCompatActivity {

  @BindView(R.id.start) Button start;
  @BindView(R.id.stop) Button stop;
  @BindView(R.id.cancel) Button cancel;
  @BindView(R.id.itemContainer) LinearLayout itemContainer;
  @BindView(R.id.container) ConstraintLayout container;
  protected static final String TAG = TestOkhttpActivity.class.getSimpleName();
  protected ProgressDialog mProgressDialog;
  protected OkHttpClient okHttpClient;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_test_okhttp);
    ButterKnife.bind(this);
    okHttpClient = DvsnierApplication.getHttpClient();
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    onDismissProgress();
  }

  public void onShowProgress() {
    onShowProgress(null);
  }

  public void onShowProgress(String message) {
    if (null == mProgressDialog) mProgressDialog = new ProgressDialog(this);
    mProgressDialog.setMessage(message);
    mProgressDialog.setCanceledOnTouchOutside(false);
    mProgressDialog.setCancelable(false);
    if (!mProgressDialog.isShowing()) mProgressDialog.show();
  }

  public void onDismissProgress() {
    if (null != mProgressDialog && mProgressDialog.isShowing()) {
      mProgressDialog.dismiss();
    }
    mProgressDialog = null;
  }

  @OnClick({ R.id.start, R.id.stop, R.id.cancel }) public void onViewClicked(View view) {
    switch (view.getId()) {
      case R.id.start:
        onShowProgress();
        onRequestStart();
        break;
      case R.id.stop:
        break;
      case R.id.cancel:
        break;
    }
  }

  protected void onRequestStart() {
    final String url = "http://www.baidu.com";
    //视测如恐,待测如初
    Request request = new Request.Builder().url(url).build();
    okHttpClient.newCall(request).enqueue(new Callback() {
      @Override public void onFailure(Call call, IOException e) {
        showToast(e.getMessage());
      }

      @Override public void onResponse(Call call, Response response) throws IOException {
        final String body = response.body().string();
        LogUtil.w(body);
        showToast(body);
      }
    });
  }

  protected void showToast(@NonNull final String msg) {
    runOnUiThread(new Runnable() {
      @Override public void run() {
        onDismissProgress();
        Toast.makeText(TestOkhttpActivity.this, msg, Toast.LENGTH_SHORT).show();
      }
    });
  }
}
