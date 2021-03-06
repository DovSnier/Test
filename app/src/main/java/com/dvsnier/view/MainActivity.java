package com.dvsnier.view;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dvsnier.R;
import com.dvsnier.adapter.MainAdapter;
import com.dvsnier.base.flavor.activity.BaseActivity;
import com.dvsnier.base.task.ITask;
import com.dvsnier.bean.CategoryBean;
import com.dvsnier.bean.ComponentBean;
import com.dvsnier.presenter.MainPresenter;
import com.dvsnier.test.utils.WindowUtil;
import com.dvsnier.viewholder.OnItemClickListener;
import com.dvsnier.wrapper.TransferStationWrapper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * the test main activity
 *
 * @author dvsnier
 * @since JDK 1.8
 */
public class MainActivity extends BaseActivity<MainPresenter> implements ITask,
        OnItemClickListener<CategoryBean, ComponentBean> {

    @BindView(R.id.ll_container)
    LinearLayout llContainer;
    @BindView(R.id.menu_content)
    TextView menuContent;
    @BindView(R.id.testContainer)
    ExpandableListView listView;
    protected List<CategoryBean> dataSet = new ArrayList<>();
    protected MainAdapter adapter;
    protected TransferStationWrapper transferStationWrapper;

    @SuppressLint("ObsoleteSdkInt")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
//            hideSystemUI();
//            getWindow().getDecorView().setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
//                @Override
//                public void onSystemUiVisibilityChange(int visibility) {
//                    // Note that system bars will only be "visible" if none of the
//                    // LOW_PROFILE, HIDE_NAVIGATION, or FULLSCREEN flags are set.
//                    if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
//                        hideSystemUI();
//                    }
//                }
//            });
//        }
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    protected void hideSystemUI() {
        View decorView = getWindow().getDecorView();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 非后置式
//            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                    | View.SYSTEM_UI_FLAG_FULLSCREEN
//                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
            // 后置全屏式，状态栏透明，搭配 View.SYSTEM_UI_FLAG_LAYOUT_STABLE & fitsSystemWindows=false
            //noinspection ConstantConditions
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//                getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
//                        WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().setStatusBarColor(Color.TRANSPARENT);
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                            | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
                } else {
                    decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
                }
            } else {
                decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            }
            int statusBarHeight = WindowUtil.getStatusBarHeight(this);
            llContainer.setPadding(0, statusBarHeight, 0, 0);
            onLog(String.format("the current status bar height is %s px.", statusBarHeight));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                if (Math.random() * 100 > 50) {
                    getWindow().setStatusBarColor(Color.RED);
                } else {
                    getWindow().setStatusBarColor(Color.TRANSPARENT);
                }
            }
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus)
            hideSystemUI();
    }

    @SuppressLint("ObsoleteSdkInt")
    @Override
    public void initView() {
        super.initView();
        menuContent.setText(String.format("%s %s", getString(R.string.app_name), "测试清单"));
        transferStationWrapper = new TransferStationWrapper(this);
//        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, dataSet);
        adapter = new MainAdapter(this, getDataSet());
//        adapter.setOnClickListener(this);
        listView.setAdapter(adapter);
        listView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                int size = adapter.getDataSet().size();
                for (int i = 0; i < size; i++) {
                    if (i != groupPosition) {
                        listView.collapseGroup(i);
                    }
                }
            }
        });
        listView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                if (listView.isGroupExpanded(groupPosition)) {
                    listView.collapseGroup(groupPosition);
                }
            }
        });
        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                ComponentBean componentBean = adapter.getChild(groupPosition, childPosition);
                if (null != componentBean) {
                    transferStationWrapper.onItemClick(parent, v, groupPosition, childPosition, componentBean);
                }
                return false;
            }
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            listView.setAlpha(0.6f);
        }
    }

    @Override
    public void initData() {
        super.initData();
        execute();
    }

    @Override
    public void execute() {
        if (null != getPresenter()) {
            getPresenter().request();
        }
    }

    @Override
    public void onItemCategoryClick(View view, int position, CategoryBean bean) {
        onToast(bean.getName());
    }

    @Override
    public void onItemComponentClick(View view, int position, ComponentBean bean) {
        onToast(bean.getName());
    }

    public List<CategoryBean> getDataSet() {
        return dataSet;
    }

    public void notifyDataSetChanged() {
        if (null != adapter) {
            adapter.setDataSet(getDataSet());
            adapter.notifyDataSetChanged();
        }
    }
}
