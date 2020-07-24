package com.dovsnier.java.sample.cases;

import com.dovsnier.java.sample.bean.BaseBean;

import java.lang.reflect.Constructor;

/**
 * ReflectCase
 * Created by dovsnier on 2020/7/23.
 */
public class ReflectCase {

    public ReflectCase() {
        // 匿名内部类
//        new BaseBean() {
//
//        };
    }

    private ReflectCase(int what) {
    }

    /**
     * 获取类类型有三种方式
     */
    public void reflect_case_class() {
        String className = "";
        // 1. x.class
        className = BaseBean.class.getSimpleName();
        System.out.println(String.format("className: %s", className));
        // 2. x.getClass()
        className = BaseBean.newInstance().getClass().getSimpleName();
        System.out.println(String.format("className: %s", className));
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

    public void reflect_case_class2(Class clazz) {
        // 1. 获取类的父类型
        Class<?> clazz_clazz = clazz.getSuperclass();
        if (null != clazz_clazz)
            System.out.println(String.format("super className: %s", clazz_clazz.getSimpleName()));
        // 2. getDeclaringClass()
        Class<?> declaringClass = clazz.getDeclaringClass();
        if (null != declaringClass)
            System.out.println(String.format("declaring className: %s", declaringClass.getSimpleName()));
        // 3. getEnclosingClass()
        Class<?> enclosingClass = clazz.getEnclosingClass();
        if (null != enclosingClass)
            System.out.println(String.format("enclosing className: %s", enclosingClass.getSimpleName()));
    }

    public void reflect_case_class_constructor(Class clazz) {
        try {
            // suggestFirstVariableName("Object")
            // 1. 无参构造器
            Constructor<?> constructor = clazz.getConstructor(null);
            if (null != constructor) {
                String constructorName = constructor.getName();
                String toGenericString = constructor.toGenericString();
                System.out.println(String.format("getConstructor(no param) -> \nconstructorName: %s\ntoGenericString: %s"
                        , constructorName, toGenericString));
            } else {
                print("getConstructor() is null.");
            }
            print("");
            // 2. 获取构造器集合
            Constructor<?>[] constructors = clazz.getConstructors();
            //noinspection ConstantConditions
            if (null != constructors) {
                int length = constructors.length;
                print(String.format("ths current getConstructors() is not empty that is %s length.", length));
                for (int i = 0; i < length; i++) {
                    String constructorName = constructors[i].getName();
                    String toGenericString = constructors[i].toGenericString();
                    System.out.println(String.format("index: %s\nconstructorName: %s\ntoGenericString: %s"
                            , i, constructorName, toGenericString));
                }
            } else {
                print("getConstructors() is null.");
            }
            print("");
            // 3. getDeclaredConstructor()
            //noinspection unchecked,ConfusingArgumentToVarargsMethod
            Constructor<?> declaredConstructor = clazz.getDeclaredConstructor(null);
            if (null != declaredConstructor) {
                String declaredConstructorName = declaredConstructor.getName();
                String toGenericString = declaredConstructor.toGenericString();
                System.out.println(String.format("getDeclaredConstructor(no param) -> \ndeclaredConstructorName: %s\ntoGenericString: %s"
                        , declaredConstructorName, toGenericString));
            } else {
                print("getDeclaredConstructor() is null.");
            }
            print("");
            // 4. getDeclaredConstructors()
            Constructor<?>[] declaredConstructors = clazz.getDeclaredConstructors();
            //noinspection ConstantConditions
            if (null != declaredConstructors) {
                int length = declaredConstructors.length;
                print(String.format("ths current getDeclaredConstructors() is not empty that is %s length.", length));
                for (int i = 0; i < length; i++) {
                    String constructorName = declaredConstructors[i].getName();
                    String toGenericString = declaredConstructors[i].toGenericString();
                    System.out.println(String.format("index: %s\nconstructorName: %s\ntoGenericString: %s"
                            , i, constructorName, toGenericString));
                }
            } else {
                print("getDeclaredConstructors() is null.");
            }
            print("");
            // 5. getEnclosingConstructor()
            Constructor<?> enclosingConstructor = clazz.getEnclosingConstructor();
            if (null != enclosingConstructor) {
                String declaredConstructorName = enclosingConstructor.getName();
                String toGenericString = enclosingConstructor.toGenericString();
                System.out.println(String.format("getEnclosingConstructor() -> \nenclosingConstructor: %s\ntoGenericString: %s"
                        , declaredConstructorName, toGenericString));
            } else {
                print("getEnclosingConstructor() is null.");
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public static void print(String msg) {
        System.out.println(String.format("%s", msg));
    }
}
