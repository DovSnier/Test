package com.dvsnier.presenter;

import com.dvsnier.bean.CategoryBean;
import com.dvsnier.common.presenter.BaseCompatPresenter;
import com.dvsnier.utils.CompatD2;
import com.dvsnier.view.MainActivity;

import java.util.List;

/**
 * MainPresenter
 * Created by dovsnier on 2019/3/4.
 */
public class MainPresenter extends BaseCompatPresenter<MainActivity> {

    public void request() {
        if (null != getView()) {
            getView().getDataSet().clear();
            List<CategoryBean> categoryBeans = CompatD2.hookOfList(getView(), "launcher.json", CategoryBean.class);
            if (null != categoryBeans) {
                getView().getDataSet().addAll(categoryBeans);
                getView().notifyDataSetChanged();
            }
        }
    }
}
