package com.dovsnier.java.sample.bean;

/**
 * ExtBean
 * Created by dovsnier on 2020/7/24.
 */
public class ExtBean {

    protected BaseBean baseBean;

    public ExtBean() {
    }

    public ExtBean(BaseBean baseBean) {
        this.baseBean = baseBean;
    }

    public BaseBean getBaseBean() {
        return baseBean;
    }

    public void setBaseBean(BaseBean baseBean) {
        this.baseBean = baseBean;
    }
}
