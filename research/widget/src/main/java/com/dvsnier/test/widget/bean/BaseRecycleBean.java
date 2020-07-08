package com.dvsnier.test.widget.bean;

import com.dvsnier.base.bean.IndexBaseBean;

/**
 * BaseRecycleBean
 * Created by dovsnier on 2020/7/8.
 */
public class BaseRecycleBean extends IndexBaseBean {

    protected String content;

    public BaseRecycleBean() {
    }

    public BaseRecycleBean(int itemType) {
        super(itemType);
    }

    public BaseRecycleBean(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}