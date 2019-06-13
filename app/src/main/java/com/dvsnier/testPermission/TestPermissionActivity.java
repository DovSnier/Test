package com.dvsnier.testPermission;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.dvsnier.R;
import com.dvsnier.base.flavor.activity.BaseActivity;

public class TestPermissionActivity extends BaseActivity
    implements ActivityCompat.OnRequestPermissionsResultCallback {

  public static final int REQUEST_CALL_PHONE_CODE = 10;
  @Bind(R.id.start) Button start;
  @Bind(R.id.stop) Button stop;
  @Bind(R.id.cancel) Button cancel;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_test_permission);
    ButterKnife.bind(this);
    stop.setText("P-Rationale");
    cancel.setText("Settings");
  }

  @OnClick({ R.id.start, R.id.stop, R.id.cancel }) public void onViewClicked(View view) {
    switch (view.getId()) {
      case R.id.start:
        checkPermission();
        break;
      case R.id.stop:
        testShouldShowRequestPermissionRationale();
        break;
      case R.id.cancel:
        requestSettingDetail();
        break;
    }
  }

  protected void checkPermission() {
    final int permission = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);
    if (PackageManager.PERMISSION_GRANTED != permission) {
      requestPermission();
    } else {
      onToast(String.format("APP 具有PackageManager.PERMISSION_GRANTED: %s权限",
          PackageManager.PERMISSION_GRANTED));
    }
  }

  protected void testShouldShowRequestPermissionRationale() {
    onToast("如果你想打电话,那么请选择Yes");
    if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CALL_PHONE)) {
      AlertDialog.Builder builder = new AlertDialog.Builder(this);
      builder.setTitle("Need Permission!");
      builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
        @Override public void onClick(DialogInterface dialog, int which) {
          onToast("cancel,呜呜┭┮﹏┭┮,你没通过权限,电话功能我无法使用");
          requestPhonePermission();
        }
      });
      builder.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
        @Override public void onClick(DialogInterface dialog, int which) {
          requestSettingDetail();
        }
      });
      builder.create().show();
    }
  }

  protected void requestPermission() {
    if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CALL_PHONE)) {
      onToast("如果你想打电话,那么请选择Yes");
      AlertDialog.Builder builder = new AlertDialog.Builder(this);
      builder.setTitle("拨打电话需要权限哦!");
      builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
        @Override public void onClick(DialogInterface dialog, int which) {
          if (TestPermissionActivity.this.getResources().getBoolean(R.bool.debug_permission)) {
            onToast("cancel,我准备继续请求权限...");
            requestPhonePermission();
          } else {
            onToast("cancel,呜呜┭┮﹏┭┮,你没通过权限,电话功能我无法使用");
          }
        }
      });
      builder.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
        @Override public void onClick(DialogInterface dialog, int which) {
          requestSettingDetail();
        }
      });
      builder.create().show();
    } else {
      onToast("来自APP 的权限请求...");
      requestPhonePermission();
    }
  }

  protected void requestPhonePermission() {
    ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.CALL_PHONE },
        REQUEST_CALL_PHONE_CODE);
  }

  protected void requestSettingDetail() {
    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
    intent.setData(Uri.parse("package:" + getPackageName())); // 根据包名打开对应的设置界面
    startActivity(intent);
  }

  @Override public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
      @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    switch (requestCode) {
      case REQUEST_CALL_PHONE_CODE:
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
          onToast("O(∩_∩)O哈哈~,谢谢通过,我可以打电话了");
        } else {
          onToast("呜呜┭┮﹏┭┮,你没通过权限,电话功能我无法使用");
        }
        break;
      default:
        break;
    }
  }
}
