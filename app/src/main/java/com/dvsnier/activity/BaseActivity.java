package com.dvsnier.activity;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import com.orhanobut.logger.Logger;

/**
 * Created by lzw on 2017/9/7.
 */

public abstract class BaseActivity extends AppCompatActivity {

  protected String TAG = this.getClass().getSimpleName();
  protected ProgressDialog mProgressDialog;

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

  public void onToast(@NonNull final String msg) {
    runOnUiThread(new Runnable() {
      @Override public void run() {
        onDismissProgress();
        Toast.makeText(BaseActivity.this, msg, Toast.LENGTH_SHORT).show();
      }
    });
  }

  public void onLog(String message) {
      onLog(message, new Object[]{});
  }

  public void onLog(@NonNull String message, Object... args) {
    Logger.wtf(message, args);
  }
}
