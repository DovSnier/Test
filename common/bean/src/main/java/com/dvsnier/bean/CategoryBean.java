package com.dvsnier.bean;


import java.util.List;

/**
 * CategoryBean
 * Created by machine code on 2019/6/13.
 */
public class CategoryBean extends NodeBean {

    protected String describe;
    protected boolean checked;
    protected List<ComponentBean> child;

    public CategoryBean() {
    }

    public CategoryBean(int itemType) {
        super(itemType);
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public List<ComponentBean> getChild() {
        return child;
    }

    public void setChild(List<ComponentBean> child) {
        this.child = child;
    }

    @Override
    public String toString() {
        return "CategoryBean{" +
                ", id=" + id +
                ", position=" + position +
                ", name='" + name + '\'' +
                ", describe='" + describe + '\'' +
                ", itemType=" + itemType +
                "checked=" + checked +
                ", flag=" + flag +
                ", child=" + child +
                '}';
    }
}