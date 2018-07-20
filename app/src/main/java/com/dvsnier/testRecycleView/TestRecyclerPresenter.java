package com.dvsnier.testRecycleView;

import com.dvsnier.common.presenter.BaseCompatPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * TestRecyclerPresenter
 * Created by dovsnier on 2018/7/12.
 */
public class TestRecyclerPresenter extends BaseCompatPresenter<TestRecyclerActivity> {

    public List<String> request() {
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
