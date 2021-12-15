package com.dvsnier.test.widget.view;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.dvsnier.base.flavor.activity.BaseActivity;
import com.dvsnier.test.widget.R;
import com.dvsnier.test.widget.presenter.TestViewGroupPresenter;

/**
 * TestViewGroupActivity
 * Created by dovsnier on 2020/6/19.
 */
public class TestViewGroupActivity extends BaseActivity<TestViewGroupPresenter> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_view_group);
        initView();
        initData();
    }

    @Override
    public void initView() {
        super.initView();
    }

    @Override
    public void initData() {
        super.initData();
    }
}