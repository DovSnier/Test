package com.dvsnier.test.tpl.okhttp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.dvsnier.base.flavor.activity.BaseActivity;
import com.dvsnier.test.tpl.R;
import com.dvsnier.test.tpl.R2;
import com.dvsnier.test.tpl.okhttp.common.OkHttpWrapper;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

public class TestOkhttpActivity extends BaseActivity {

    @BindView(R2.id.start)
    Button start;
    @BindView(R2.id.stop)
    Button stop;
    @BindView(R2.id.cancel)
    Button cancel;
    @BindView(R2.id.itemContainer)
    LinearLayout itemContainer;
    @BindView(R2.id.container)
    ConstraintLayout container;
    protected ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_okhttp);
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        onDismissProgress();
        OkHttpWrapper.getInstance().onDestroy();
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

    @OnClick({R2.id.start, R2.id.stop, R2.id.cancel})
    public void onViewClicked(View view) {
        int i = view.getId();
        if (i == R.id.start) {
            onShowProgress();
            onRequestStart();
        } else if (i == R.id.stop) {
        } else if (i == R.id.cancel) {
        }
    }

    protected void onRequestStart() {
        final String url = "http://www.baidu.com";
        //视测如恐,待测如初
        Request request = new Request.Builder().url(url).build();
        OkHttpWrapper.getInstance().with(this).getOkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                showToast(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String body = response.body().string();
                Log.w(TAG, body);
                showToast(body);
            }
        });
    }

    protected void showToast(@NonNull final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                onDismissProgress();
                Toast.makeText(TestOkhttpActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
