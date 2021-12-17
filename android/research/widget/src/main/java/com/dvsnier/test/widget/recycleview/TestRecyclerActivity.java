package com.dvsnier.test.widget.recycleview;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dvsnier.base.adapter.IAdapter;
import com.dvsnier.base.flavor.activity.BaseActivity;
import com.dvsnier.base.task.UIRunnable;
import com.dvsnier.base.view.IRecyclerView;
import com.dvsnier.common.listener.IOnItemClickListener;
import com.dvsnier.test.widget.R;
import com.dvsnier.test.widget.R2;
import com.dvsnier.test.widget.adapter.RecyclerViewAdapter;
import com.dvsnier.test.widget.bean.BaseRecycleBean;
import com.dvsnier.test.widget.presenter.TestRecyclerPresenter;
import com.dvsnier.utils.ThreadUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TestRecyclerActivity extends BaseActivity<TestRecyclerPresenter> implements IRecyclerView, IAdapter,
        IOnItemClickListener<String> {

    @BindView(R2.id.recycler)
    RecyclerView recyclerView;
    protected RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_recycler);
        ButterKnife.bind(this);
        performScheduledInternal();
    }

    @Override
    public void initView() {
        super.initView();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        //noinspection ConstantConditions
        adapter = new RecyclerViewAdapter(this);
        adapter.setOnClickListener(this);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                /**
                 * 1. dx, dy 针对的是View , 不是操作(手势触摸)
                 * 2. canScrollHorizontally/canScrollVertically(direction) 针对的是操作(手势触摸), 不是View
                 */
                if (dy > 0) { // 手指上滑(direction: 1)，View 向下滚动，加载
                    Log.w(TAG, String.format("手指上滑，View 向下滚动 -> %s", dy));
                } else { // 手指下滑(direction: -1)，View 向上滚动，下拉
                    Log.w(TAG, String.format("手指下滑，View 向上滚动 -> %s", dy));
                }
            }
        });
    }

    @Override
    public void initData() {
        super.initData();
        final List<BaseRecycleBean> dataSet = new ArrayList<>();
        // TODO: 2019/6/18 the handle to optimization
        ThreadUtil.execute(new Runnable() {
            @Override
            public void run() {
                if (null != getPresenter()) {
                    dataSet.addAll(getPresenter().request());
                    postDelayed(new UIRunnable() {
                        @Override
                        public void uiRun() {
                            adapter.setData(dataSet);
                            adapter.notifyDataSetChanged();
                        }
                    }, 100);
                }
            }
        });
    }

    @Override
    public RecyclerView.Adapter getAdapter() {
        return adapter;
    }

    @Override
    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    @Override
    public void onItemClick(View view, int position, String value) {
        Toast.makeText(this, String.format("position: %1$s,value: %2$s", position, value), Toast.LENGTH_SHORT).show();
    }
}
