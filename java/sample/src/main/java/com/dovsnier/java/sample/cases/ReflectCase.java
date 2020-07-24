package com.dovsnier.java.sample.cases;

import com.dovsnier.java.sample.bean.BaseBean;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

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

    public static void print(String msg) {
        System.out.println(String.format("%s", msg));
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
            //noinspection unchecked
            Constructor<?> constructor = clazz.getConstructor(null);
            if (null != constructor) {
                String constructorName = constructor.getName();
                String toGenericString = constructor.toGenericString();
                System.out.println(String.format("%s(no param) -> \nconstructorName: %s\ntoGenericString: %s"
                        , "getConstructor", constructorName, toGenericString));
            } else {
                print("getConstructor(no param) is null.");
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
                System.out.println(String.format("%s(no param) -> \ndeclaredConstructorName: %s\ntoGenericString: %s"
                        , "getDeclaredConstructor", declaredConstructorName, toGenericString));
            } else {
                print("getDeclaredConstructor(no param) is null.");
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

    public void reflect_case_class_field(Class clazz, String field_name) {
        try {
            // 1. getField()
            Field field = clazz.getField(field_name);
            if (null != field) {
                String fieldName = field.getName();
                String toGenericString = field.toGenericString();
                System.out.println(String.format("%s(%s %s %s) -> \nfieldName: %s\ntoGenericString: %s"
                        , "getField", Modifier.toString(field.getModifiers()),
                        field.getType().getSimpleName(), field_name, fieldName,
                        toGenericString));
            } else {
                print(String.format("getField(%s) is null.", field_name));
            }
        } catch (NoSuchFieldException | NullPointerException e) {
            e.printStackTrace();
        }
        print("");
        try {
            // 2. getFields()
            Field[] fields = clazz.getFields();
            //noinspection ConstantConditions
            if (null != fields) {
                int length = fields.length;
                print(String.format("ths current getFields() is not empty that is %s length.", length));
                for (int i = 0; i < length; i++) {
                    String fieldName = fields[i].getName();
                    String toGenericString = fields[i].toGenericString();
                    System.out.println(String.format("index: %s\nfieldName: %s %s %s\ntoGenericString: %s"
                            , i, Modifier.toString(fields[i].getModifiers()),
                            fields[i].getType().getSimpleName(), fieldName, toGenericString));
                }
            } else {
                print("getFields() is null.");
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        print("");
        try {
            // 3. getDeclaredField()
            Field declaredField = clazz.getDeclaredField(field_name);
            if (null != declaredField) {
                String fieldName = declaredField.getName();
                String toGenericString = declaredField.toGenericString();
                System.out.println(String.format("%s(%s %s %s) -> \nfieldName: %s\ntoGenericString: %s"
                        , "declaredField", Modifier.toString(declaredField.getModifiers()),
                        declaredField.getType().getSimpleName(), field_name, fieldName,
                        toGenericString));
            } else {
                print(String.format("getDeclaredField(%s) is null.", field_name));
            }
        } catch (NoSuchFieldException | NullPointerException e) {
            e.printStackTrace();
        }
        print("");
        try {
            // 4. getDeclaredFields()
            Field[] declaredFields = clazz.getDeclaredFields();
            //noinspection ConstantConditions
            if (null != declaredFields) {
                int length = declaredFields.length;
                print(String.format("ths current declaredFields() is not empty that is %s length.", length));
                for (int i = 0; i < length; i++) {
                    String fieldName = declaredFields[i].getName();
                    String toGenericString = declaredFields[i].toGenericString();
                    System.out.println(String.format("index: %s\nfieldName: %s %s %s\ntoGenericString: %s"
                            , i, Modifier.toString(declaredFields[i].getModifiers()),
                            declaredFields[i].getType().getSimpleName(), fieldName,
                            toGenericString));
                }
            } else {
                print("declaredFields() is null.");
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

}
