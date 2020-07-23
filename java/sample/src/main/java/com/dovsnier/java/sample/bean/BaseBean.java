package com.dovsnier.java.sample.bean;

/**
 * BaseBean
 * Created by dovsnier on 2020/7/23.
 */
public class BaseBean {

    protected String name;
    protected int what;
    protected int arg1;
    protected int arg2;
    protected Object object;
    private int type;

    public BaseBean() {
    }

    public BaseBean(int type) {
        this.type = type;
    }

    public BaseBean(int what, int type) {
        this.what = what;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWhat() {
        return what;
    }

    public void setWhat(int what) {
        this.what = what;
    }

    public int getArg1() {
        return arg1;
    }

    public void setArg1(int arg1) {
        this.arg1 = arg1;
    }

    public int getArg2() {
        return arg2;
    }

    public void setArg2(int arg2) {
        this.arg2 = arg2;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "BaseBean{" +
                "name='" + name + '\'' +
                ", what=" + what +
                ", arg1=" + arg1 +
                ", arg2=" + arg2 +
                ", object=" + object +
                ", type=" + type +
                '}';
    }
}
