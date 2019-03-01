package com.dvsnier.testRecycleView;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.dvsnier.R;
import com.dvsnier.activity.BaseActivity;
import com.dvsnier.base.task.UIRunnable;
import com.dvsnier.common.compat.ICompatBaseView;
import com.dvsnier.common.listener.IOnItemClickListener;
import com.dvsnier.utils.runnable.ThreadUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TestRecyclerActivity extends BaseActivity<TestRecyclerPresenter> implements IOnItemClickListener<String>, ICompatBaseView {

    @Bind(R.id.recycler)
    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_recycler);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    public void initView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        //noinspection ConstantConditions
        adapter = new RecyclerViewAdapter(this);
        adapter.setOnClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void initData() {
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
    public void onItemClick(View view, int position, String value) {
        Toast.makeText(this, String.format("position: %1$s,value: %2$s", position, value), Toast.LENGTH_SHORT).show();
    }
}
