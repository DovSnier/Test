package com.dvsnier.testRecycleView;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.dvsnier.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TestRecyclerActivity extends AppCompatActivity {

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
        adapter = new RecyclerViewAdapter(this, initData());
        recyclerView.setAdapter(adapter);
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
}
