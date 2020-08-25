package com.dvsnier.test.common.permission;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dvsnier.base.flavor.activity.BaseActivity;
import com.dvsnier.base.task.ITask;
import com.dvsnier.test.common.R;
import com.dvsnier.test.common.R2;
import com.dvsnier.test.common.permission.adapter.PermissionAdapter;
import com.dvsnier.test.common.permission.bean.PermissionBean;
import com.dvsnier.test.common.permission.holder.IOnItemClickListener;
import com.dvsnier.test.common.permission.holder.IType;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TestPermissionActivity extends BaseActivity implements
        ActivityCompat.OnRequestPermissionsResultCallback,
        IOnItemClickListener<PermissionBean>, ITask {

    public static final int REQUEST_CALL_PHONE_CODE = 10;
    @BindView(R2.id.start)
    Button start;
    @BindView(R2.id.stop)
    Button stop;
    @BindView(R2.id.cancel)
    Button cancel;
    @BindView(R2.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R2.id.tv_menu_toggle)
    TextView tvMenuToggle;
    protected PermissionAdapter adapter;
    protected List<PermissionBean> dataSets = new ArrayList<>();
    protected boolean isToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_permission);
        ButterKnife.bind(this);
        initView();
        execute();
    }

    @Override
    public void initView() {
        super.initView();
        stop.setText("P-Rationale");
        cancel.setText("Settings");
        adapter = new PermissionAdapter(this);
        adapter.setOnClickListener(this);
        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, RecyclerView.VERTICAL);
        //noinspection ConstantConditions
        itemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.bg_common_item_line));
        recyclerView.addItemDecoration(itemDecoration);
    }

    @Override
    public void initData() {
        super.initData();
        PackageManager packageManager = getPackageManager();
        List<PackageInfo> packageInfoList = packageManager.getInstalledPackages(0);
        int size = packageInfoList.size();
        dataSets.clear();
        for (int i = 0; i < size; i++) {
            PackageInfo packageInfo = packageInfoList.get(i);
            PermissionBean item = new PermissionBean();
            ApplicationInfo applicationInfo = packageInfo.applicationInfo;
            if (null != applicationInfo) {
                item.setIcon(applicationInfo.loadIcon(packageManager));
                item.setName(String.valueOf(applicationInfo.loadLabel(packageManager)));
                item.setItemType(IType.TYPE_ITEM_DEFAULT);
                item.setPackageName(packageInfo.packageName);
                item.setVersionName(packageInfo.versionName);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    item.setVersionCode(packageInfo.getLongVersionCode());
                } else {
                    item.setVersionCode(packageInfo.versionCode);
                }
                item.setFlag(applicationInfo.flags);
                if ((applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
                    item.setMsg("第三方应用");
                    dataSets.add(item);
                } else {
                    if (isToggle) {
                        item.setMsg("系统应用");
                        dataSets.add(item);
                    } else {
                        // nothing to do
                    }
                }
            }
        }
        PermissionBean END = new PermissionBean();
        END.setItemType(IType.TYPE_ITEM_DEFAULT);
        END.setPackageName("...End...");
        END.setFlag(0);
        dataSets.add(END);
        adapter.setData(dataSets);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void execute() {
        toggle();
        initData();
    }

    @OnClick({R2.id.start, R2.id.stop, R2.id.cancel, R2.id.tv_menu_toggle})
    public void onViewClicked(View view) {
        int viewId = view.getId();
        if (viewId == R.id.start) {
            checkPermission();
        } else if (viewId == R.id.stop) {
            testShouldShowRequestPermissionRationale();
        } else if (viewId == R.id.cancel) {
            requestSettingDetail(getPackageName());
        } else if (viewId == R.id.tv_menu_toggle) {
            isToggle = !isToggle;
            execute();
        } else {
            // nothing to do
        }
    }

    @Override
    public void onItemClick(View view, int position, PermissionBean bean) {
        if (null != bean) {
            int itemType = bean.getItemType();
            String packageName = bean.getPackageName();
            if (itemType == IType.TYPE_ITEM_DEFAULT) {
                if (adapter.getItemCount() - 1 != position) {
                    requestSettingDetail(packageName);
                }
            } else {
                // nothing to do
            }
        }
    }

    protected void toggle() {
        if (isToggle) {
            tvMenuToggle.setText(getString(R.string.menu_system_and_user_app));
        } else {
            tvMenuToggle.setText(getString(R.string.menu_user_app));
        }
    }

    protected void requestSettingDetail(String packageName) {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + packageName)); // 根据包名打开对应的设置界面
        startActivity(intent);
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
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    onToast("cancel,呜呜┭┮﹏┭┮,你没通过权限,电话功能我无法使用");
                    requestPhonePermission();
                }
            });
            builder.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    requestSettingDetail(getPackageName());
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
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (TestPermissionActivity.this.getResources().getBoolean(R.bool.debug_permission)) {
                        onToast("cancel,我准备继续请求权限...");
                        requestPhonePermission();
                    } else {
                        onToast("cancel,呜呜┭┮﹏┭┮,你没通过权限,电话功能我无法使用");
                    }
                }
            });
            builder.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    requestSettingDetail(getPackageName());
                }
            });
            builder.create().show();
        } else {
            onToast("来自APP 的权限请求...");
            requestPhonePermission();
        }
    }

    protected void requestPhonePermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL_PHONE_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
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
