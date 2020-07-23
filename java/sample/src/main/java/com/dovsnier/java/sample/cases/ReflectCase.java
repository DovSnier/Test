package com.dovsnier.java.sample.cases;

import com.dovsnier.java.sample.bean.BaseBean;

/**
 * ReflectCase
 * Created by dovsnier on 2020/7/23.
 */
public class ReflectCase {

    public ReflectCase() {
    }

    /**
     * 获取类类型有三种方式
     */
    public void reflect_case_class() {
        String className = "";
        // 1. x.class
        className = BaseBean.class.getSimpleName();
        // 2. x.getClass()
        className = BaseBean.newInstance().getClass().getSimpleName();
        // 3. Class.forName()
        try {
            Class<?> clazz = Class.forName("com.dovsnier.java.sample.bean.BaseBean");
            if (null != clazz) {
                className = clazz.getSimpleName();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(String.format("className: %s", className));
    }
}
