package com.dvsnier.test.widget.nested;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.dvsnier.base.adapter.IAdapter;
import com.dvsnier.base.flavor.activity.BaseActivity;
import com.dvsnier.base.task.UIRunnable;
import com.dvsnier.base.view.IRecyclerView;
import com.dvsnier.common.listener.IOnItemClickListener;
import com.dvsnier.test.widget.R;
import com.dvsnier.test.widget.R2;
import com.dvsnier.test.widget.adapter.ArrayViewAdapter;
import com.dvsnier.test.widget.presenter.TestNestedScrollingParentPresenter;
import com.dvsnier.utils.ThreadUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * TestNestedScrollingParentActivity
 * Created by dovsnier on 2020/3/25.
 */
public class TestNestedScrollingParentActivity extends BaseActivity<TestNestedScrollingParentPresenter> implements IRecyclerView, IAdapter,
        IOnItemClickListener<String> {

    @BindView(R2.id.tv_content)
    TextView tvContent;
    @BindView(R2.id.recycler_view)
    RecyclerView recyclerView;
    protected ArrayViewAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_nested_scrolling_parent);
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
        //noinspection
        adapter = new ArrayViewAdapter(this);
        adapter.setOnClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void initData() {
        super.initData();
        final List<String> dataSet = new ArrayList<>();
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
