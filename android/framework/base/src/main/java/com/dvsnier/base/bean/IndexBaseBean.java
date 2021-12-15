package com.dvsnier.base.bean;

/**
 * IndexBaseBean
 * Created by dovsnier on 2020/7/8.
 */
public class IndexBaseBean extends BaseBean {

    /* -- start -- */
    protected int itemType;
    protected int itemPosition;
    protected boolean isFirst;
    protected boolean isLast;
    protected int flag;
    /* -- end -- */

    public IndexBaseBean() {
    }

    public IndexBaseBean(int itemType) {
        this.itemType = itemType;
    }

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public int getItemPosition() {
        return itemPosition;
    }

    public void setItemPosition(int itemPosition) {
        this.itemPosition = itemPosition;
    }

    public boolean isFirst() {
        return isFirst;
    }

    public void setFirst(boolean first) {
        isFirst = first;
    }

    public boolean isLast() {
        return isLast;
    }

    public void setLast(boolean last) {
        isLast = last;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "IndexBaseBean{" +
                "itemType=" + itemType +
                ", itemPosition=" + itemPosition +
                ", isFirst=" + isFirst +
                ", isLast=" + isLast +
                ", flag=" + flag +
                '}';
    }
}