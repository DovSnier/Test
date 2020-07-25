package com.dovsnier.java.sample;

import com.dovsnier.java.sample.annotation.Bean;
import com.dovsnier.java.sample.annotation.Type;
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
        // 成员属性
//        test_class_field();
        // 成员方法
//        test_class_method();
        // 注解
//        test_class_annotation();
    }

    public static void print(String msg) {
        System.out.println(String.format("%s", msg));
    }

    public static void print(String arg1, String arg2) {
        System.out.println(String.format("\n-> %s. 测试%s:", arg1, arg2));
    }

    public static void printHolder() {
        System.out.println(String.format("%s", "---"));
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
        reflectCase.reflect_case_class_constructor(BaseBean.class, int.class);
//        printHolder();
        reflectCase.reflect_case_class_constructor(ValueBean.class);
        reflectCase.reflect_case_class_constructor(ValueBean.class, int.class);
//        printHolder();
        reflectCase.reflect_case_class_constructor(ExtBean.class);
        reflectCase.reflect_case_class_constructor(ExtBean.class, int.class);
    }

    protected static void test_class_field() {
        print("三", "获取成员属性信息");
        reflectCase.reflect_case_class_field(BaseBean.class, "author");
        reflectCase.reflect_case_class_field(BaseBean.class, "name");
        reflectCase.reflect_case_class_field(BaseBean.class, "type");
        reflectCase.reflect_case_class_field(BaseBean.class, "anonym");
        printHolder();
        reflectCase.reflect_case_class_field(ValueBean.class, "name");
        printHolder();
        reflectCase.reflect_case_class_field(ExtBean.class, "name");
    }

    protected static void test_class_method() {
        print("四", "获取成员方法信息");
        reflectCase.reflect_case_class_method(BaseBean.class, "getName");
        reflectCase.reflect_case_class_method(BaseBean.class, "setName",
                String.class);
        reflectCase.reflect_case_class_method(BaseBean.class, "setType()",
                int.class);
        reflectCase.reflect_case_class_method(BaseBean.class, "setAnonym()",
                int.class);
        printHolder();
        reflectCase.reflect_case_class_method(ValueBean.class, "getName()");
        printHolder();
        reflectCase.reflect_case_class_method(ExtBean.class, "getName()");
    }

    protected static void test_class_annotation() {
        print("五", "获取注解信息");
        reflectCase.reflect_case_class_annotation(BaseBean.class, Bean.class);
        reflectCase.reflect_case_class_annotation(ValueBean.class, Bean.class);
        reflectCase.reflect_case_class_annotation(ValueBean.class, Type.class);
        reflectCase.reflect_case_class_annotation(ExtBean.class, Bean.class);
    }
}
