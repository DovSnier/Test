package com.dvsnier.testCoordinatorLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.ListView;
import android.widget.Toast;

import com.dvsnier.R;
import com.dvsnier.activity.BaseActivity;
import com.dvsnier.utils.AppBarStateChangeListener;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TestCoordinatorLayout3Activity extends BaseActivity {


    @Bind(R.id.app_bar_layout)
    AppBarLayout appBarLayout;
    @Bind(R.id.listView)
    ListView listView;
    @Bind(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private AppBarStateChangeListener.State currentState;
    private int scaledTouchSlop;
    private MotionEvent motionEvent;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_coordinator_layout_4);
        ButterKnife.bind(this);
        scaledTouchSlop = ViewConfiguration.get(this).getScaledTouchSlop();
        refreshLayout.setEnableRefresh(false);
        refreshLayout.setEnableAutoLoadMore(false);
        appBarLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                currentState = state;
                switch (state) {
                    case EXPANDED:
                        Toast.makeText(TestCoordinatorLayout3Activity.this, "EXPANDED", Toast.LENGTH_SHORT).show();
//                        refreshLayout.autoRefresh();
                        break;
                    case COLLAPSED:
                        Toast.makeText(TestCoordinatorLayout3Activity.this, "COLLAPSED", Toast.LENGTH_SHORT).show();
//                        refreshLayout.setEnableAutoLoadMore(false);
                        break;
                    default:
                        refreshLayout.setEnableRefresh(false);
                        refreshLayout.setEnableAutoLoadMore(false);
                        Toast.makeText(TestCoordinatorLayout3Activity.this, "IDLE", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        refreshLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction() & event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        motionEvent = event;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (currentState == AppBarStateChangeListener.State.COLLAPSED) {
                        } else if (currentState == AppBarStateChangeListener.State.EXPANDED) {

                        }
                        float detalY = event.getY() - motionEvent.getY();
                        if (Math.abs(detalY) > Math.abs(event.getX() - motionEvent.getX()) && Math.abs(detalY) > scaledTouchSlop) {

                            android.support.design.widget.CoordinatorLayout.Behavior behavior = ((android.support.design.widget.CoordinatorLayout.LayoutParams) appBarLayout.getLayoutParams()).getBehavior();
                            //noinspection unchecked
                            behavior.onNestedPreScroll((CoordinatorLayout) appBarLayout.getParent(), appBarLayout, refreshLayout, 0, Float.valueOf(detalY).intValue(), new int[]{0, 0});
                        } else {
                            return false;
                        }
                        break;
                }
                return true;
            }
        });
    }

}
