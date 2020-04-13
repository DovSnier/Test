package com.dvsnier.base.flavor.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.Toast;

import com.dvsnier.common.compat.ICompatBaseView;
import com.dvsnier.common.presenter.BaseCompatPresenter;
import com.dvsnier.common.view.BaseCompatActivity;

/**
 * Created by lzw on 2017/9/7.
 */

public abstract class BaseActivity<T extends BaseCompatPresenter> extends BaseCompatActivity<T> implements ICompatBaseView {

    public static final boolean DEBUG = false;
    protected ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (DEBUG) {
            onLog("onCreate()");
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (DEBUG) {
            onLog("onRestart()");
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (DEBUG) {
            onLog("onStart()");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (DEBUG) {
            onLog("onResume()");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (DEBUG) {
            onLog("onPause()");
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (DEBUG) {
            onLog("onStop()");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (DEBUG) {
            onLog("onDestroy()");
        }
        onDismissProgress();
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        if (DEBUG) {
            onLog("onAttachFragment()");
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (DEBUG) {
            onLog("onNewIntent()");
        }
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        if (DEBUG) {
            onLog("onResumeFragments()");
        }
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (DEBUG) {
            onLog("onAttachedToWindow()");
        }
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (DEBUG) {
            onLog("onDetachedFromWindow()");
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (DEBUG) {
            onLog("onRestoreInstanceState()");
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (DEBUG) {
            onLog("onSaveInstanceState()");
        }
    }


    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    protected final void performScheduledInternal() {
        initView();
        initData();
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
            @Override
            public void run() {
                onDismissProgress();
                Toast.makeText(BaseActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onLog(String message) {
        Log.i(TAG, String.format("%s", message));
    }

    @Deprecated
    public void onLog(@NonNull String message, Object... args) {
    }
}
