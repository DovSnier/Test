package com.dvsnier.test.tpl.reactnative;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import com.dvsnier.base.flavor.activity.BaseActivity;
import com.dvsnier.permission.OnSimpleResponsePermissionListener;
import com.dvsnier.permission.Permission;
import com.dvsnier.permission.PermissionWrapper;
import com.dvsnier.test.tpl.BuildConfig;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactRootView;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.soloader.SoLoader;

public class TestReactNativeActivity extends BaseActivity implements DefaultHardwareBackBtnHandler {

    private final int OVERLAY_PERMISSION_REQUEST_CODE = 2021;
    private ReactRootView mReactRootView;
    private ReactInstanceManager mReactInstanceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_test_react_native);
        SoLoader.init(this, false);
        mReactRootView = new ReactRootView(this);
//        List<ReactPackage> packages = new PackageList(getApplication()).getPackages();
        // 有一些第三方可能不能自动链接，对于这些包我们可以用下面的方式手动添加进来：
        // packages.add(new MyReactNativePackage());
        // 同时需要手动把他们添加到`settings.gradle`和 `app/build.gradle`配置文件中。

        mReactInstanceManager = ReactInstanceManager.builder()
                .setApplication(getApplication())
                .setCurrentActivity(this)
                .setBundleAssetName("index.android.bundle")
                .setJSMainModulePath("index")
//                .addPackages(packages)
                .setUseDeveloperSupport(BuildConfig.DEBUG)
                .setInitialLifecycleState(LifecycleState.RESUMED)
                .build();
        // 注意这里的MyReactNativeApp 必须对应"index.js"中的
        // "AppRegistry.registerComponent()"的第一个参数
        mReactRootView.startReactApplication(mReactInstanceManager, "MyReactNativeApp", null);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, OVERLAY_PERMISSION_REQUEST_CODE);
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mReactInstanceManager != null) {
            mReactInstanceManager.onHostPause(this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mReactInstanceManager != null) {
            mReactInstanceManager.onHostResume(this, this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mReactInstanceManager != null) {
            mReactInstanceManager.onHostDestroy(this);
        }
        if (mReactRootView != null) {
            mReactRootView.unmountReactApplication();
        }
    }

    @Override
    public void onBackPressed() {
        if (mReactInstanceManager != null) {
            mReactInstanceManager.onBackPressed();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void invokeDefaultOnBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU && mReactInstanceManager != null) {
            mReactInstanceManager.showDevOptionsDialog();
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == OVERLAY_PERMISSION_REQUEST_CODE) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (!Settings.canDrawOverlays(this)) {
                    // SYSTEM_ALERT_WINDOW permission not granted
                    // 如果应用面向 API 级别 23 或更高级别，应用用户必须通过权限管理屏幕向应用明确授予此权限。
                    // 该应用程序通过发送带有操作 Settings.ACTION_MANAGE_OVERLAY_PERMISSION 的意图来
                    // 请求用户的批准。应用程序可以通过调用 Settings.canDrawOverlays() 来检查它是否具有此授权。
                    PermissionWrapper.newInstance(this).requestPermission(
                            Manifest.permission.SYSTEM_ALERT_WINDOW,
                            new OnSimpleResponsePermissionListener() {
                                @Override
                                public void onPermissionCallback(Context context, boolean isGrant, Permission permission) {
                                    super.onPermissionCallback(context, isGrant, permission);
                                    String value = String.format("isGrant: %s , isNegatived: %s , isNegativedAndNoPresentation: %s , permission: %s"
                                            , isGrant, permission.isNegatived(),
                                            permission.isNegativedAndNoPresentation(), permission);
                                    Log.d(TAG, value);
                                    if (!isGrant)
                                        Toast.makeText(context, "请允于授权弹窗，否则您无法进行 RN 调试信息输出。", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }
        }
        mReactInstanceManager.onActivityResult(this, requestCode, resultCode, data);
    }
}
