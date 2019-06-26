package com.dvsnier.view;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;

import com.dvsnier.R;
import com.dvsnier.adapter.MainAdapter;
import com.dvsnier.base.flavor.activity.BaseActivity;
import com.dvsnier.base.task.ITask;
import com.dvsnier.bean.CategoryBean;
import com.dvsnier.bean.ComponentBean;
import com.dvsnier.presenter.MainPresenter;
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

    @BindView(R.id.testContainer)
    ExpandableListView listView;
    protected List<CategoryBean> dataSet = new ArrayList<>();
    protected MainAdapter adapter;
    protected TransferStationWrapper transferStationWrapper;

    @SuppressLint("ObsoleteSdkInt")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            hideSystemUI();
            getWindow().getDecorView().setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
                @Override
                public void onSystemUiVisibilityChange(int visibility) {
                    if (visibility == View.VISIBLE) {
                        hideSystemUI();
                    }
                }
            });
        }
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @SuppressLint("ObsoleteSdkInt")
    protected void hideSystemUI() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
            } else {
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
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
