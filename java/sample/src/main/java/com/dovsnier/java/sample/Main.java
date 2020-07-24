package com.dovsnier.java.sample;

import com.dovsnier.java.sample.bean.BaseBean;
import com.dovsnier.java.sample.bean.ExtBean;
import com.dovsnier.java.sample.bean.ValueBean;
import com.dovsnier.java.sample.cases.ReflectCase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Main {

    protected static ReflectCase reflectCase = new ReflectCase();


    public static void main(String[] args) {
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS", Locale.CHINA)
                .format(Calendar.getInstance().getTime()));

        // 类
//        test_class();
        // 构造器
        test_class_constructor();
    }

    protected static void test_class() {
        print("一", "类信息");
        reflectCase.reflect_case_class();
        print("1.1", "类");
        reflectCase.reflect_case_class2(ReflectCase.class);
        reflectCase.reflect_case_class2(ValueBean.class);
        reflectCase.reflect_case_class2(ExtBean.class);
    }

    protected static void test_class_constructor() {
        print("二", "获取构造器信息");
//        reflectCase.reflect_case_constructor(ReflectCase.class);
//        printHolder();
        reflectCase.reflect_case_class_constructor(BaseBean.class);
        printHolder();
        reflectCase.reflect_case_class_constructor(ValueBean.class);
        printHolder();
        reflectCase.reflect_case_class_constructor(ExtBean.class);
    }

    public static void print(String arg1, String arg2) {
        System.out.println(String.format("\n-> %s. 测试%s:", arg1, arg2));
    }

    public static void printHolder() {
        System.out.println(String.format("%s", "---"));
    }
}
