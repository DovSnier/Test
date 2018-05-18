package com.dvsnier.testCoordinatorLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.dvsnier.R;
import com.dvsnier.activity.BaseActivity;
import com.dvsnier.testRecycleView.RecyclerViewAdapter;
import com.dvsnier.utils.AppBarStateChangeListener;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TestCoordinatorLayout3Activity extends BaseActivity {


    @Bind(R.id.app_bar_layout)
    AppBarLayout appBarLayout;
    @Bind(R.id.listView)
    RecyclerView recyclerView;
    @Bind(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private AppBarStateChangeListener.State currentState;
//    private int scaledTouchSlop;
//    private MotionEvent motionEvent;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_coordinator_layout_4);
        ButterKnife.bind(this);
//        scaledTouchSlop = ViewConfiguration.get(this).getScaledTouchSlop();
        refreshLayout.setEnableRefresh(false);
        refreshLayout.setEnableAutoLoadMore(false);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
                refreshLayout.autoRefresh();
                getWindow().getDecorView().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.finishRefresh();
                    }
                }, 2000);
            }
        });
        appBarLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                currentState = state;
                switch (state) {
                    case EXPANDED:
//                        Toast.makeText(TestCoordinatorLayout3Activity.this, "EXPANDED", Toast.LENGTH_SHORT).show();
                        refreshLayout.setEnableRefresh(true);
                        break;
                    case COLLAPSED:
//                        Toast.makeText(TestCoordinatorLayout3Activity.this, "COLLAPSED", Toast.LENGTH_SHORT).show();
                        setDefaultViewState();
                        break;
                    default:
//                        Toast.makeText(TestCoordinatorLayout3Activity.this, "IDLE", Toast.LENGTH_SHORT).show();
                        setDefaultViewState();
                        break;
                }
            }
        });
//        refreshLayout.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                switch (event.getAction() & event.getActionMasked()) {
//                    case MotionEvent.ACTION_DOWN:
//                        motionEvent = event;
//                        break;
//                    case MotionEvent.ACTION_MOVE:
//                        if (currentState == AppBarStateChangeListener.State.COLLAPSED) {
//                        } else if (currentState == AppBarStateChangeListener.State.EXPANDED) {
//
//                        }
//                        float detalY = event.getY() - motionEvent.getY();
//                        if (Math.abs(detalY) > Math.abs(event.getX() - motionEvent.getX()) && Math.abs(detalY) > scaledTouchSlop) {
//
//                            android.support.design.widget.CoordinatorLayout.Behavior behavior = ((android.support.design.widget.CoordinatorLayout.LayoutParams) appBarLayout.getLayoutParams()).getBehavior();
//                            //noinspection unchecked
//                            behavior.onNestedPreScroll((CoordinatorLayout) appBarLayout.getParent(), appBarLayout, refreshLayout, 0, Float.valueOf(detalY).intValue(), new int[]{0, 0});
//                        } else {
//                            return false;
//                        }
//                        break;
//                }
//                return true;
//            }
//        });

        //noinspection unchecked
//        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_activated_1, android.R.id.text1, getResources().getStringArray(R.array.dataSet));
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new RecyclerViewAdapter(this, initData()));
    }

    private List<String> initData() {
        List<String> dataSet = new ArrayList<>();
        dataSet.add("0");
        dataSet.add("1");
        dataSet.add("2");
        dataSet.add("3");
        dataSet.add("4");
        dataSet.add("5");
        dataSet.add("6");
        dataSet.add("7");
        dataSet.add("8");
        dataSet.add("9");
        dataSet.add("我");
        dataSet.add("是");
        dataSet.add("地");
        dataSet.add("球");
        dataSet.add("人");
        dataSet.add(",");
        dataSet.add("我");
        dataSet.add("爱");
        dataSet.add("这");
        dataSet.add("个");
        dataSet.add("国");
        dataSet.add("家");
        dataSet.add("0");
        dataSet.add("1");
        dataSet.add("2");
        dataSet.add("3");
        dataSet.add("4");
        dataSet.add("5");
        dataSet.add("6");
        dataSet.add("7");
        dataSet.add("8");
        dataSet.add("9");
        return dataSet;
    }

    private void setDefaultViewState() {
        if (refreshLayout.isEnableRefresh())
            refreshLayout.finishRefresh();
        if (refreshLayout.isEnableLoadMore())
            refreshLayout.finishLoadMore();
        refreshLayout.setEnableRefresh(false);
        refreshLayout.setEnableAutoLoadMore(false);
    }

}
