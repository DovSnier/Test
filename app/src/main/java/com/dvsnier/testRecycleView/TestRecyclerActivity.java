package com.dvsnier.testRecycleView;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.dvsnier.R;
import com.dvsnier.activity.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TestRecyclerActivity extends BaseActivity<TestRecyclerPresenter> implements OnItemClickListener {

    @Bind(R.id.recycler)
    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_recycler);
        ButterKnife.bind(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        //noinspection ConstantConditions
        adapter = new RecyclerViewAdapter(this, getPresenter().request());
        adapter.setOnClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position, String value) {
        Toast.makeText(this, String.format("position: %1$s,value: %2$s", position, value), Toast.LENGTH_SHORT).show();
    }
}
