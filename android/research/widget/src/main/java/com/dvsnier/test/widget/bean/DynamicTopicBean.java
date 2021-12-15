package com.dvsnier.test.widget.bean;

import com.dvsnier.base.bean.BaseBean;

/**
 * DynamicTopicBean
 * Created by dovsnier on 2020/4/17.
 */
public class DynamicTopicBean extends BaseBean implements ITopicBean {

    protected String topicName;
    protected Integer topicId;

    @Override
    public String getTopicName() {
        return topicName;
    }

    @Override
    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    @Override
    public Integer getTopicId() {
        return topicId;
    }

    @Override
    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }
}