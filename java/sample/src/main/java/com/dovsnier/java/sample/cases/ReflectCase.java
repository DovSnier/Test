package com.dovsnier.java.sample.cases;

import com.dovsnier.java.sample.bean.BaseBean;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

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

    public static void print(String arg1, String arg2) {
        System.out.println(String.format(arg1, arg2));
    }

    /**
     * 获取类类型有三种方式
     */
    public void reflect_case_class() {
        String className = "";
        // 1. x.class
        className = BaseBean.class.getSimpleName();
        System.out.println(String.format("className:\n%s", className));
        print("");
        // 2. x.getClass()
        className = BaseBean.newInstance().getClass().getSimpleName();
        System.out.println(String.format("className:\n%s", className));
        print("");
        // 3. Class.forName()
        try {
            Class<?> clazz = Class.forName("com.dovsnier.java.sample.bean.BaseBean");
            if (null != clazz) {
                className = clazz.getSimpleName();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(String.format("className:\n%s", className));
    }

    public void reflect_case_class2(Class clazz) {
        // 1. 获取类的父类型
        Class<?> clazz_clazz = clazz.getSuperclass();
        if (null != clazz_clazz) {
            String simpleName = clazz_clazz.getSimpleName();
            print(String.format("%s.%s()\n%s", clazz.getSimpleName(), "getSuperclass",
                    simpleName));
        } else {
            print(String.format("%s.%s() is null.", clazz.getSimpleName(),
                    "getSuperclass"));
        }
        print("");
        // 2. getDeclaringClass()
        Class<?> declaringClass = clazz.getDeclaringClass();
        if (null != declaringClass) {
            String simpleName = declaringClass.getSimpleName();
            print(String.format("%s.%s()\n%s", clazz.getSimpleName(), "getDeclaringClass",
                    simpleName));
        } else {
            print(String.format("%s.%s() is null.", clazz.getSimpleName(),
                    "getDeclaringClass"));
        }
        print("");
        // 3. getEnclosingClass()
        Class<?> enclosingClass = clazz.getEnclosingClass();
        if (null != enclosingClass) {
            String simpleName = enclosingClass.getSimpleName();
            print(String.format("%s.%s()\n%s", clazz.getSimpleName(), "getEnclosingClass",
                    simpleName));
        } else {
            print(String.format("%s.%s() is null.", clazz.getSimpleName(),
                    "getEnclosingClass"));
        }
        print("");
    }

    public void reflect_case_class_constructor(Class clazz, Class... parameterTypes) {
        try {
            // suggestFirstVariableName("Object")
            // 1. getConstructor()
            //noinspection unchecked
            Constructor<?> constructor = clazz.getConstructor(parameterTypes);
            if (null != constructor) {
                String constructorName = constructor.getName();
                String toGenericString = constructor.toGenericString();
                System.out.println(String.format("%s(%s) -> \nconstructorName: %s\ntoGenericString: %s"
                        , "getConstructor", null != parameterTypes ?
                                parameterTypes.getClass().getName() : "no param", constructorName,
                        toGenericString));
            } else {
                print("getConstructor(%s) is null.", null != parameterTypes ?
                        parameterTypes.getClass().getName() : "no param");
            }
        } catch (NullPointerException | NoSuchMethodException e) {
            e.printStackTrace();
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
        try {
            // 3. getDeclaredConstructor()
            //noinspection unchecked
            Constructor<?> declaredConstructor = clazz.getDeclaredConstructor(parameterTypes);
            if (null != declaredConstructor) {
                String declaredConstructorName = declaredConstructor.getName();
                String toGenericString = declaredConstructor.toGenericString();
                System.out.println(String.format("%s(%s) -> \ndeclaredConstructorName: %s\ntoGenericString: %s"
                        , "getDeclaredConstructor", null != parameterTypes ?
                                parameterTypes.getClass().getName() : "no param", declaredConstructorName,
                        toGenericString));
            } else {
                print("getDeclaredConstructor(%s) is null.", null != parameterTypes ?
                        parameterTypes.getClass().getName() : "no param");
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
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

    public void reflect_case_class_method(Class clazz, String method_name, Class... parameterTypes) {
        try {
            // 1. getMethod()
            //noinspection unchecked
            Method method = clazz.getMethod(method_name, parameterTypes);
            if (null != method) {
                String methodName = method.getName();
                String toGenericString = method.toGenericString();
                System.out.println(String.format("%s(%s %s %s) -> \nmethodName: %s\ntoGenericString: %s"
                        , "getMethod", Modifier.toString(method.getModifiers()),
                        method.getReturnType().getSimpleName(), method_name, methodName,
                        toGenericString));
            } else {
                print(String.format("getMethod(%s) is null.", method_name));
            }
        } catch (NullPointerException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        print("");
        try {
            // 2. getMethods()
            Method[] methods = clazz.getMethods();
            //noinspection ConstantConditions
            if (null != methods) {
                int length = methods.length;
                print(String.format("ths current getMethods() is not empty that is %s length.", length));
                for (int i = 0; i < length; i++) {
                    String methodName = methods[i].getName();
                    String toGenericString = methods[i].toGenericString();
                    System.out.println(String.format("index: %s\nmethodName: %s %s %s\ntoGenericString: %s"
                            , i, Modifier.toString(methods[i].getModifiers()),
                            methods[i].getReturnType().getSimpleName(), methodName, toGenericString));
                }
            } else {
                print("getMethods() is null.");
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        print("");
        try {
            // 3. getDeclaredMethod()
            //noinspection unchecked
            Method declaredMethod = clazz.getDeclaredMethod(method_name, parameterTypes);
            if (null != declaredMethod) {
                String methodName = declaredMethod.getName();
                String toGenericString = declaredMethod.toGenericString();
                System.out.println(String.format("%s(%s %s %s) -> \nmethodName: %s\ntoGenericString: %s"
                        , "declaredMethod", Modifier.toString(declaredMethod.getModifiers()),
                        declaredMethod.getReturnType().getSimpleName(), method_name, methodName,
                        toGenericString));
            } else {
                print(String.format("getDeclaredMethod(%s) is null.", method_name));
            }
        } catch (NullPointerException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        print("");
        try {
            // 4. getDeclaredMethods()
            Method[] declaredMethods = clazz.getDeclaredMethods();
            //noinspection ConstantConditions
            if (null != declaredMethods) {
                int length = declaredMethods.length;
                print(String.format("ths current declaredMethods() is not empty that is %s length.", length));
                for (int i = 0; i < length; i++) {
                    String methodName = declaredMethods[i].getName();
                    String toGenericString = declaredMethods[i].toGenericString();
                    System.out.println(String.format("index: %s\nmethodName: %s %s %s\ntoGenericString: %s"
                            , i, Modifier.toString(declaredMethods[i].getModifiers()),
                            declaredMethods[i].getReturnType().getSimpleName(), methodName,
                            toGenericString));
                }
            } else {
                print("declaredMethods() is null.");
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    public void reflect_case_class_annotation(Class clazz, Class annotationClass) {
        try {
            // 1. getAnnotation()
            Annotation annotation = clazz.getAnnotation(annotationClass);
            if (null != annotation) {
                String name = annotation.annotationType().getName();
                //noinspection ConstantConditions
                System.out.println(String.format("%s.%s(%s) -> \n%s", clazz.getSimpleName(),
                        "getAnnotation", null != annotationClass ? annotationClass.getSimpleName() :
                                "no param", name));
            } else {
                //noinspection ConstantConditions
                print(String.format("%s.getAnnotation(%s) is null.", clazz.getSimpleName(),
                        null != annotationClass ? annotationClass.getSimpleName() : "no param"));
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        print("");
        // 2. getAnnotations()
        Annotation[] annotations = clazz.getAnnotations();
        if (null != annotations) {
            int length = annotations.length;
            print(String.format("ths current getAnnotations() is not empty that is %s length.", length));
            for (int i = 0; i < length; i++) {
                String name = annotations[i].annotationType().getName();
                System.out.println(String.format("%s %s.%s() -> \n%s", i,
                        clazz.getSimpleName(), "getAnnotations", name));
            }
        } else {
            print(String.format("%s.getAnnotations() is null.", clazz.getSimpleName()));
        }
        print("");
        // 3. getAnnotationsByType()
        Annotation[] annotationsByType = clazz.getAnnotationsByType(annotationClass);
        //noinspection ConstantConditions
        print(String.format("%s.getAnnotationsByType(%s)\n%s", clazz.getSimpleName(),
                null != annotationClass ? annotationClass.getSimpleName() : "no param",
                annotationClass.toString()));
        print("");
        // 4. getAnnotatedInterfaces()
        AnnotatedType[] annotatedInterfaces = clazz.getAnnotatedInterfaces();
        print(String.format("%s.getAnnotatedInterfaces()\n%s", clazz.getSimpleName(),
                Arrays.toString(annotatedInterfaces)));
        print("");
        // 5. getAnnotatedSuperclass()
        AnnotatedType annotatedSuperclass = clazz.getAnnotatedSuperclass();
        print(String.format("%s.getAnnotatedSuperclass()\n%s", clazz.getSimpleName(),
                annotatedSuperclass));
        print("");
        // 6. getDeclaredAnnotation()
        Annotation declaredAnnotation = clazz.getDeclaredAnnotation(annotationClass);
        //noinspection ConstantConditions
        print(String.format("%s.getDeclaredAnnotation(%s)\n%s", clazz.getSimpleName(),
                null != annotationClass ? annotationClass.getSimpleName() : "no param",
                declaredAnnotation));
        print("");
        // 7. getDeclaredAnnotations()
        Annotation[] declaredAnnotations = clazz.getDeclaredAnnotations();
        if (null != declaredAnnotations) {
            int length = declaredAnnotations.length;
            print(String.format("ths current getDeclaredAnnotations() is not empty that is %s length.", length));
            for (int i = 0; i < length; i++) {
                String name = declaredAnnotations[i].annotationType().getName();
                System.out.println(String.format("%s %s.%s() -> \n%s", i,
                        clazz.getSimpleName(), "getDeclaredAnnotations", name));
            }
        } else {
            print(String.format("%s.getDeclaredAnnotations() is null.", clazz.getSimpleName()));
        }
        print("");
        // 8. getDeclaredAnnotationsByType()
        Annotation[] declaredAnnotationsByType = clazz.getDeclaredAnnotationsByType(annotationClass);
        //noinspection ConstantConditions
        print(String.format("%s.getDeclaredAnnotationsByType(%s)\n%s", clazz.getSimpleName(),
                null != annotationClass ? annotationClass.getSimpleName() : "no param",
                Arrays.toString(declaredAnnotationsByType)));
        print("");
    }
}
