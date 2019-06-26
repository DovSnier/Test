package com.dvsnier.bean;

/**
 * ComponentBean
 * Created by machine code on 2019/6/13.
 */
public class ComponentBean extends NodeBean {

    protected int parentType;

    public ComponentBean() {
    }

    public ComponentBean(int itemType) {
        super(itemType);
    }

    public ComponentBean(int itemType, int parentType) {
        super(itemType);
        this.parentType = parentType;
    }

    public int getParentType() {
        return parentType;
    }

    public void setParentType(int parentType) {
        this.parentType = parentType;
    }

    @Override
    public String toString() {
        return "ComponentBean{" +
                ", id=" + id +
                ", position=" + position +
                ", name='" + name + '\'' +
                ", itemType=" + itemType +
                "parentType=" + parentType +
                ", flag=" + flag +
                '}';
    }
}