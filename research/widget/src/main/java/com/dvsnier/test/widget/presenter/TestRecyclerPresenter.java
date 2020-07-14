package com.dvsnier.test.widget.presenter;

import com.dvsnier.common.presenter.BaseCompatPresenter;
import com.dvsnier.test.widget.R;
import com.dvsnier.test.widget.bean.BaseRecycleBean;
import com.dvsnier.test.widget.recycleview.IRecycleType;
import com.dvsnier.test.widget.recycleview.TestRecyclerActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * TestRecyclerPresenter
 * Created by dovsnier on 2018/7/12.
 */
public class TestRecyclerPresenter extends BaseCompatPresenter<TestRecyclerActivity> {

    public List<BaseRecycleBean> request() {
        List<BaseRecycleBean> dataSet = new ArrayList<>();
        if (null != getContext()) {
            String[] value = getContext().getResources().getStringArray(R.array.data_set_with_recycle);
            //noinspection ConstantConditions
            if (null != value && value.length > 0) {
                for (String item : value) {
                    BaseRecycleBean baseRecycleBean = new BaseRecycleBean();
                    if (isDigit(item)) {
                        baseRecycleBean.setItemType(IRecycleType.TYPE_DEFAULT);
                    } else {
                        baseRecycleBean.setItemType(IRecycleType.TYPE_LEFT_CONTENT_RIGHT_PICTURE);
                    }
                    baseRecycleBean.setContent(item);
                    dataSet.add(baseRecycleBean);
                }
            }
        }
        return dataSet;
    }

    public static boolean isDigit(String value) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }
}
