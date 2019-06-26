package com.dvsnier.bean;

import com.dvsnier.base.bean.BaseBean;

/**
 * NodeBean
 * Created by machine code on 2019/6/13.
 */
public class NodeBean extends BaseBean {

    protected int id;
    protected int position;
    protected String name;
    protected int itemType;
    protected int flag;

    public NodeBean() {
    }

    public NodeBean(int itemType) {
        this.itemType = itemType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "NodeBean{" +
                "id=" + id +
                ", position=" + position +
                ", name='" + name + '\'' +
                ", itemType=" + itemType +
                ", flag=" + flag +
                '}';
    }
}