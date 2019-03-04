package com.dvsnier.presenter;

import com.dvsnier.common.presenter.BaseCompatPresenter;
import com.dvsnier.view.MainActivity;

/**
 * MainPresenter
 * Created by dovsnier on 2019/3/4.
 */
public class MainPresenter extends BaseCompatPresenter<MainActivity> {

    public void request() {
        if (null != getView()) {
            getView().getDataSet().clear();
            getView().getDataSet().add("测试 Exception");
            getView().getDataSet().add("测试 Affinity");
            getView().getDataSet().add("测试 Cache");
            getView().getDataSet().add("测试 SQL");
            getView().getDataSet().add("测试 Theme");
            getView().getDataSet().add("测试 SurfaceView");
            getView().getDataSet().add("测试 AIDL");
            getView().getDataSet().add("测试 Scroll");
            getView().getDataSet().add("测试 Glide");
            getView().getDataSet().add("测试 RecyclerView");
            getView().getDataSet().add("测试 XUtils3");
            getView().getDataSet().add("测试 Animator");
            getView().getDataSet().add("测试 GreenDao");
            getView().getDataSet().add("测试 语音识别");
            getView().getDataSet().add("测试 CoordinatorLayout");
            //noinspection SpellCheckingInspection
            getView().getDataSet().add("测试 Okhttp");
            getView().getDataSet().add("测试 EventBus");
            getView().getDataSet().add("测试 Permission");
            getView().getDataSet().add("测试 AppBarLayout");
            getView().getDataSet().add("测试 CollapsingToolbarLayout");
            getView().getDataSet().add("测试 TouchEvent");
        }
    }
}
