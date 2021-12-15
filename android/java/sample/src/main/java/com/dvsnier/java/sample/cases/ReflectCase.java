package com.dvsnier.java.sample.cases;

import com.dvsnier.java.sample.bean.BaseBean;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;
import java.util.List;

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

    public static void printThenSimple(String clazz, String clazz_method, String value) {
        print(String.format("%s.%s():\n%s", clazz, clazz_method, value));
    }

    public static void printThenNoParamWithTwoValue(String clazz, String clazz_method,
                                                    String name_key, String name_value,
                                                    String generic_key, String generic_value) {
        print(String.format("%s.%s():\n%s: %s\n%s: %s", clazz, clazz_method,
                name_key, name_value, generic_key, generic_value));
    }

    public static void printThenNoParamWithOneValue(String clazz, String clazz_method,
                                                    String name_key, String name_value) {
        print(String.format("%s.%s():\n%s: %s", clazz, clazz_method,
                name_key, name_value));
    }

    public static void printThenParamWithTwoValue(String clazz, String clazz_method,
                                                  String parameter, String name_key,
                                                  String name_value, String generic_key,
                                                  String generic_value) {
        print(String.format("%s.%s(%s):\n%s: %s\n%s: %s", clazz, clazz_method, parameter,
                name_key, name_value, generic_key, generic_value));
    }

    public static void printThenParamWithOneValue(String clazz, String clazz_method,
                                                  String parameter, String name_key,
                                                  String name_value) {
        print(String.format("%s.%s(%s):\n%s: %s", clazz, clazz_method, parameter,
                name_key, name_value));
    }

    public static void printThenElementWithTwoValue(int index, String name_key,
                                                    String name_value, String generic_key,
                                                    String generic_value) {
        print(String.format("index: %s\n%s: %s\n%s: %s", index, name_key, name_value,
                generic_key, generic_value));
    }

    public static void printThenElementWithOneValue(int index, String name_key,
                                                    String name_value) {
        print(String.format("index: %s\n%s: %s", index, name_key, name_value));
    }

    public static void printThenElementWithThreeAndOneValue(int index, String name_key,
                                                            String modifier, String type,
                                                            String name_value,
                                                            String generic_key,
                                                            String generic_value) {
        print(String.format("index: %s\n%s: %s %s %s\n%s: %s", index,
                name_key, modifier, type, name_value,
                generic_key, generic_value));
    }

    public static void printThenParamDescribeWithTwoValue(String clazz, String clazz_method,
                                                          String modifier, String type,
                                                          String parameter,
                                                          String name_key, String name_value,
                                                          String generic_key, String generic_value) {
        print(String.format("%s.%s(%s %s %s):\n%s: %s\n%s: %s", clazz, clazz_method,
                modifier, type, parameter,
                name_key, name_value,
                generic_key, generic_value));
    }

    public static void printThenMethodParamDescribeWithTwoValue(String clazz, String clazz_method,
                                                                String modifier,
                                                                String type,
                                                                String method_name,
                                                                String name_key, String name_value,
                                                                String generic_key, String generic_value,
                                                                Class... parameterType) {
        if (null != parameterType && parameterType.length > 0) {
            List<Class> classes = Arrays.asList(parameterType);
            StringBuilder stringBuilder = new StringBuilder();
            int size = classes.size();
            for (int i = 0; i < size; i++) {
                Class item = classes.get(i);
                stringBuilder.append(item.getSimpleName());
                if (i > 0 && i == size - 1) {
                    stringBuilder.append(",");
                }
            }
            print(String.format("%s.%s(%s %s %s(%s)):\n%s: %s\n%s: %s", clazz, clazz_method,
                    modifier, type, method_name, stringBuilder.toString(),
                    name_key, name_value,
                    generic_key, generic_value));
        } else {
            print(String.format("%s.%s(%s %s %s()):\n%s: %s\n%s: %s", clazz, clazz_method,
                    modifier, type, method_name,
                    name_key, name_value,
                    generic_key, generic_value));
        }
    }

    public static void printThenIsNull(String clazz, String clazz_method) {
        print(String.format("%s.%s() is null.", clazz, clazz_method));
    }

    public static void printThenParamIsNull(String clazz, String clazz_method, String parameter) {
        print(String.format("%s.%s(%s) is null.", clazz, clazz_method, parameter));
    }

    public static void printThenMethodParamIsNull(String clazz, String clazz_method,
                                                  String method_name,
                                                  Class... methodParameterType) {
        if (null != methodParameterType && methodParameterType.length > 0) {
            List<Class> classes = Arrays.asList(methodParameterType);
            StringBuilder stringBuilder = new StringBuilder();
            int size = classes.size();
            for (int i = 0; i < size; i++) {
                Class item = classes.get(i);
                stringBuilder.append(item.getSimpleName());
                if (i > 0 && i == size - 1) {
                    stringBuilder.append(",");
                }
            }
            print(String.format("%s.%s(%s(%s)) is null.", clazz, clazz_method, method_name,
                    stringBuilder.toString()));
        } else {
            print(String.format("%s.%s(%s()) is null.", clazz, clazz_method, method_name));
        }
    }

    public static void printThenElementDeclare(String clazz, String clazz_method, int length) {
        print(String.format("ths current %s.%s() is not empty that is %s length.", clazz,
                clazz_method, length));
    }

    /**
     * 获取类类型有三种方式
     */
    public void reflect_case_class() {
        String className = "";
        // 1. x.class
        className = BaseBean.class.getSimpleName();
        print(String.format("className:\n%s", className));
        print("");
        // 2. x.getClass()
        className = BaseBean.newInstance().getClass().getSimpleName();
        print(String.format("className:\n%s", className));
        print("");
        // 3. Class.forName()
        try {
            Class<?> clazz = Class.forName("com.dvsnier.java.sample.bean.BaseBean");
            if (null != clazz) {
                className = clazz.getSimpleName();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        print(String.format("className:\n%s", className));
    }

    public void reflect_case_class2(Class clazz) {
        // 1. 获取类的父类型
        Class<?> clazz_clazz = clazz.getSuperclass();
        if (null != clazz_clazz) {
            String simpleName = clazz_clazz.getSimpleName();
            printThenSimple(clazz.getSimpleName(), "getSuperclass", simpleName);
        } else {
            printThenIsNull(clazz.getSimpleName(), "getSuperclass");
        }
        print("");
        // 2. getDeclaringClass()
        Class<?> declaringClass = clazz.getDeclaringClass();
        if (null != declaringClass) {
            String simpleName = declaringClass.getSimpleName();
            printThenSimple(clazz.getSimpleName(), "getDeclaringClass", simpleName);
        } else {
            printThenIsNull(clazz.getSimpleName(), "getDeclaringClass");
        }
        print("");
        // 3. getEnclosingClass()
        Class<?> enclosingClass = clazz.getEnclosingClass();
        if (null != enclosingClass) {
            String simpleName = enclosingClass.getSimpleName();
            printThenSimple(clazz.getSimpleName(), "getEnclosingClass", simpleName);
        } else {
            printThenIsNull(clazz.getSimpleName(), "getEnclosingClass");
        }
        print("");
    }

    public void reflect_case_class3(Class clazz) {
        // 1. getComponentType()
        Class componentType = clazz.getComponentType();
        if (null != componentType) {
            printThenSimple(clazz.getSimpleName(), "getComponentType",
                    componentType.getName());
        } else {
            printThenIsNull(clazz.getSimpleName(), "getComponentType");
        }
        print("");
        // 2. getGenericInterfaces()
        Type[] genericInterfaces = clazz.getGenericInterfaces();
        if (null != genericInterfaces) {
            int length = genericInterfaces.length;
            printThenElementDeclare(clazz.getSimpleName(), "getGenericInterfaces"
                    , length);
            for (int i = 0; i < length; i++) {
                String typeName = genericInterfaces[i].getTypeName();
                printThenElementWithOneValue(i, "typeName", typeName);
            }
        } else {
            printThenIsNull(clazz.getSimpleName(), "getGenericInterfaces");
        }
        print("");
        // 3. getGenericSuperclass()
        Type genericSuperclass = clazz.getGenericSuperclass();
        if (null != genericSuperclass) {
            printThenSimple(clazz.getSimpleName(), "getGenericSuperclass",
                    genericSuperclass.getTypeName());
        } else {
            printThenIsNull(clazz.getSimpleName(), "getGenericSuperclass");
        }
        print("");
        // 4. getTypeName()
        String typeName = clazz.getTypeName();
        if (null != typeName) {
            printThenSimple(clazz.getSimpleName(), "getTypeName",
                    typeName);
        } else {
            printThenIsNull(clazz.getSimpleName(), "getTypeName");
        }
        print("");
        // 5. getTypeParameters()
        TypeVariable[] typeParameters = clazz.getTypeParameters();
        if (null != typeParameters) {
            int length = typeParameters.length;
            printThenElementDeclare(clazz.getSimpleName(), "getTypeParameters"
                    , length);
            for (int i = 0; i < length; i++) {
                String name = typeParameters[i].getName();
                String typeParametersTypeName = typeParameters[i].getTypeName();
                GenericDeclaration genericDeclaration = typeParameters[i]
                        .getGenericDeclaration();
                AnnotatedType[] annotatedBounds = typeParameters[i].getAnnotatedBounds();
                Annotation[] annotations = typeParameters[i].getAnnotations();
                Type[] bounds = typeParameters[i].getBounds();
                Annotation[] declaredAnnotations = typeParameters[i].getDeclaredAnnotations();
                printThenElementWithTwoValue(i, "name", name,
                        "typeParametersTypeName", typeParametersTypeName);
            }
        } else {
            printThenIsNull(clazz.getSimpleName(), "getTypeParameters");
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

                printThenParamWithTwoValue(clazz.getSimpleName(), "getConstructor",
                        null != parameterTypes ? parameterTypes.getClass().getName() : "no param",
                        "constructorName", constructorName,
                        "toGenericString", toGenericString);
            } else {
                printThenParamIsNull(clazz.getSimpleName(), "getConstructor",
                        null != parameterTypes ? parameterTypes.getClass().getName()
                                : "no param");
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
            printThenElementDeclare(clazz.getSimpleName(), "getConstructors", length);
            for (int i = 0; i < length; i++) {
                String constructorName = constructors[i].getName();
                String toGenericString = constructors[i].toGenericString();
                printThenElementWithTwoValue(i, "constructorName", constructorName,
                        "toGenericString", toGenericString);
            }
        } else {
            printThenIsNull(clazz.getSimpleName(), "getConstructors");
        }
        print("");
        try {
            // 3. getDeclaredConstructor()
            //noinspection unchecked
            Constructor<?> declaredConstructor = clazz.getDeclaredConstructor(parameterTypes);
            if (null != declaredConstructor) {
                String declaredConstructorName = declaredConstructor.getName();
                String toGenericString = declaredConstructor.toGenericString();
                printThenParamWithTwoValue(clazz.getSimpleName(), "getDeclaredConstructor",
                        null != parameterTypes ? parameterTypes.getClass().getName() : "no param",
                        "declaredConstructorName", declaredConstructorName,
                        "toGenericString", toGenericString);
            } else {
                printThenParamIsNull(clazz.getSimpleName(), "getDeclaredConstructor",
                        null != parameterTypes ? parameterTypes.getClass().getName() : "no param");
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
            printThenElementDeclare(clazz.getSimpleName(), "getDeclaredConstructors", length);
            for (int i = 0; i < length; i++) {
                String declaredConstructorName = declaredConstructors[i].getName();
                String toGenericString = declaredConstructors[i].toGenericString();
                printThenElementWithTwoValue(i,
                        "declaredConstructorName", declaredConstructorName,
                        "toGenericString", toGenericString);
            }
        } else {
            printThenIsNull(clazz.getSimpleName(), "getDeclaredConstructors");
        }
        print("");
        // 5. getEnclosingConstructor()
        Constructor<?> enclosingConstructor = clazz.getEnclosingConstructor();
        if (null != enclosingConstructor) {
            String enclosingConstructorName = enclosingConstructor.getName();
            String toGenericString = enclosingConstructor.toGenericString();
            printThenNoParamWithTwoValue(clazz.getSimpleName(),
                    "getEnclosingConstructor",
                    "enclosingConstructorName", enclosingConstructorName,
                    "toGenericString", toGenericString);
        } else {
            printThenIsNull(clazz.getSimpleName(), "getEnclosingConstructor");
        }
        print("");
    }

    public void reflect_case_class_field(Class clazz, String field_name) {
        try {
            // 1. getField()
            Field field = clazz.getField(field_name);
            if (null != field) {
                String fieldName = field.getName();
                String toGenericString = field.toGenericString();
                printThenParamDescribeWithTwoValue(clazz.getSimpleName(), "getField",
                        Modifier.toString(field.getModifiers()),
                        field.getType().getSimpleName(),
                        field_name,
                        "fieldName", fieldName,
                        "toGenericString", toGenericString);
            } else {
                printThenParamIsNull(clazz.getSimpleName(), "getField", field_name);
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
                printThenElementDeclare(clazz.getSimpleName(), "getFields", length);
                for (int i = 0; i < length; i++) {
                    String fieldName = fields[i].getName();
                    String toGenericString = fields[i].toGenericString();
                    printThenElementWithThreeAndOneValue(i,
                            "fieldName",
                            Modifier.toString(fields[i].getModifiers()),
                            fields[i].getType().getSimpleName(),
                            fieldName,
                            "toGenericString", toGenericString);
                }
            } else {
                printThenIsNull(clazz.getSimpleName(), "getFields");
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        print("");
        try {
            // 3. getDeclaredField()
            Field declaredField = clazz.getDeclaredField(field_name);
            if (null != declaredField) {
                String declaredFieldName = declaredField.getName();
                String toGenericString = declaredField.toGenericString();
                printThenParamDescribeWithTwoValue(clazz.getSimpleName(), "getDeclaredField",
                        Modifier.toString(declaredField.getModifiers()),
                        declaredField.getType().getSimpleName(),
                        field_name,
                        "declaredFieldName", declaredFieldName,
                        "toGenericString", toGenericString);
            } else {
                printThenParamIsNull(clazz.getSimpleName(), "getDeclaredField", field_name);
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
                printThenElementDeclare(clazz.getSimpleName(), "getDeclaredFields", length);
                for (int i = 0; i < length; i++) {
                    String declaredFieldName = declaredFields[i].getName();
                    String toGenericString = declaredFields[i].toGenericString();
                    printThenElementWithThreeAndOneValue(i,
                            "declaredFieldName",
                            Modifier.toString(declaredFields[i].getModifiers()),
                            declaredFields[i].getType().getSimpleName(),
                            declaredFieldName,
                            "toGenericString", toGenericString);
                }
            } else {
                printThenIsNull(clazz.getSimpleName(), "getDeclaredFields");
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        print("");
    }

    public void reflect_case_class_method(Class clazz, String method_name, Class... parameterTypes) {
        try {
            // 1. getMethod()
            //noinspection unchecked
            Method method = clazz.getMethod(method_name, parameterTypes);
            if (null != method) {
                String methodName = method.getName();
                String toGenericString = method.toGenericString();
                printThenMethodParamDescribeWithTwoValue(clazz.getSimpleName(), "getMethod",
                        Modifier.toString(method.getModifiers()),
                        method.getReturnType().getSimpleName(),
                        method_name,
                        "methodName", methodName,
                        "toGenericString", toGenericString,
                        parameterTypes
                );
            } else {
                printThenMethodParamIsNull(clazz.getSimpleName(), "getMethod",
                        method_name, parameterTypes);
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
                printThenElementDeclare(clazz.getSimpleName(), "getMethods", length);
                for (int i = 0; i < length; i++) {
                    String methodName = methods[i].getName();
                    String toGenericString = methods[i].toGenericString();
                    printThenElementWithThreeAndOneValue(i,
                            "methodName",
                            Modifier.toString(methods[i].getModifiers()),
                            methods[i].getReturnType().getSimpleName(),
                            methodName,
                            "toGenericString",
                            toGenericString
                    );
                }
            } else {
                printThenIsNull(clazz.getSimpleName(), "getMethods");
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
                String declaredMethodName = declaredMethod.getName();
                String toGenericString = declaredMethod.toGenericString();
                printThenMethodParamDescribeWithTwoValue(clazz.getSimpleName(),
                        "getDeclaredMethod",
                        Modifier.toString(declaredMethod.getModifiers()),
                        declaredMethod.getReturnType().getSimpleName(),
                        method_name,
                        "declaredMethodName", declaredMethodName,
                        "toGenericString", toGenericString,
                        parameterTypes);
            } else {
                printThenMethodParamIsNull(clazz.getSimpleName(),
                        "getDeclaredMethod", method_name, parameterTypes);
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
                printThenElementDeclare(clazz.getSimpleName(), "getDeclaredMethods",
                        length);
                for (int i = 0; i < length; i++) {
                    String declaredMethodName = declaredMethods[i].getName();
                    String toGenericString = declaredMethods[i].toGenericString();
                    printThenElementWithThreeAndOneValue(i,
                            "declaredMethodName",
                            Modifier.toString(declaredMethods[i].getModifiers()),
                            declaredMethods[i].getReturnType().getSimpleName(),
                            declaredMethodName,
                            "toGenericString", toGenericString);
                }
            } else {
                printThenIsNull(clazz.getSimpleName(), "getDeclaredMethods");
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
                String annotationName = annotation.annotationType().getName();
                //noinspection ConstantConditions
                printThenParamWithOneValue(clazz.getSimpleName(),
                        "getAnnotation", null != annotationClass ?
                                annotationClass.getSimpleName() : "no param",
                        "annotationName", annotationName);
            } else {
                //noinspection ConstantConditions
                printThenParamIsNull(clazz.getSimpleName(), "getAnnotation",
                        null != annotationClass ? annotationClass.getSimpleName() : "no param");
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        print("");
        // 2. getAnnotations()
        Annotation[] annotations = clazz.getAnnotations();
        if (null != annotations) {
            int length = annotations.length;
            printThenElementDeclare(clazz.getSimpleName(), "getAnnotations", length);
            for (int i = 0; i < length; i++) {
                String annotationName = annotations[i].annotationType().getName();
                printThenElementWithOneValue(i, "annotationName", annotationName);
            }
        } else {
            printThenIsNull(clazz.getSimpleName(), "getAnnotations");
        }
        print("");
        // 3. getAnnotationsByType()
        Annotation[] annotationsByType = clazz.getAnnotationsByType(annotationClass);
        if (null != annotationsByType) {
            int length = annotationsByType.length;
            printThenElementDeclare(clazz.getSimpleName(), "getAnnotationsByType",
                    length);
            for (int i = 0; i < length; i++) {
                String annotationsByTypeName = annotationsByType[i].annotationType().getName();
                //noinspection ConstantConditions
                printThenParamWithOneValue(clazz.getSimpleName(),
                        "getAnnotationsByType",
                        null != annotationClass ? annotationClass.getSimpleName() : "no param",
                        "annotationsByTypeName", annotationsByTypeName);
            }

        } else {
            //noinspection ConstantConditions
            printThenParamIsNull(clazz.getSimpleName(), "getAnnotationsByType",
                    null != annotationClass ? annotationClass.getSimpleName() : "no param");
        }
        print("");
        // 4. getAnnotatedInterfaces()
        AnnotatedType[] annotatedInterfaces = clazz.getAnnotatedInterfaces();
        if (null != annotatedInterfaces) {
            int length = annotatedInterfaces.length;
            printThenElementDeclare(clazz.getSimpleName(), "getAnnotatedInterfaces",
                    length);
            for (int i = 0; i < length; i++) {
                String typeName = annotatedInterfaces[i].getType().getTypeName();
                //noinspection unchecked
                boolean annotationPresent = annotatedInterfaces[i]
                        .isAnnotationPresent(annotationClass);
                //noinspection unchecked
                Annotation annotatedInterfacesAnnotation = annotatedInterfaces[i]
                        .getAnnotation(annotationClass);
                Annotation[] annotatedInterfacesAnnotations = annotatedInterfaces[i]
                        .getAnnotations();
                //noinspection ConstantConditions
                printThenParamWithOneValue(clazz.getSimpleName(),
                        "getAnnotationsByType",
                        null != annotationClass ? annotationClass.getSimpleName() : "no param",
                        "typeName", typeName);
            }

        } else {
            //noinspection ConstantConditions
            printThenParamIsNull(clazz.getSimpleName(), "getAnnotationsByType",
                    null != annotationClass ? annotationClass.getSimpleName() : "no param");
        }
        print("");
        // 5. getAnnotatedSuperclass()
        AnnotatedType annotatedSuperclass = clazz.getAnnotatedSuperclass();
        String typeName = annotatedSuperclass.getType().getTypeName();
        printThenNoParamWithOneValue(clazz.getSimpleName(),
                "getAnnotatedSuperclass",
                "annotatedSuperclass", typeName);
        print("");
        // 6. getDeclaredAnnotation()
        try {
            Annotation declaredAnnotation = clazz.getDeclaredAnnotation(annotationClass);
            String simpleName = declaredAnnotation.annotationType().getSimpleName();
            //noinspection ConstantConditions
            printThenParamWithOneValue(clazz.getSimpleName(), "getDeclaredAnnotation",
                    null != annotationClass ? annotationClass.getSimpleName() : "no param",
                    "declaredAnnotation", simpleName);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        print("");
        // 7. getDeclaredAnnotations()
        Annotation[] declaredAnnotations = clazz.getDeclaredAnnotations();
        if (null != declaredAnnotations) {
            int length = declaredAnnotations.length;
            printThenElementDeclare(clazz.getSimpleName(), "getDeclaredAnnotations",
                    length);
            for (int i = 0; i < length; i++) {
                String name = declaredAnnotations[i].annotationType().getName();
                printThenElementWithOneValue(i, "getDeclaredAnnotations", name);
            }
        } else {
            printThenIsNull(clazz.getSimpleName(), "getDeclaredAnnotations");
        }
        print("");
        // 8. getDeclaredAnnotationsByType()
        Annotation[] declaredAnnotationsByType = clazz
                .getDeclaredAnnotationsByType(annotationClass);
        if (null != declaredAnnotationsByType) {
            int length = declaredAnnotationsByType.length;
            printThenElementDeclare(clazz.getSimpleName(),
                    "getDeclaredAnnotationsByType", length);
            for (int i = 0; i < length; i++) {
                String annotationsByTypeName = declaredAnnotationsByType[i]
                        .annotationType().getName();
                //noinspection ConstantConditions
                printThenParamWithOneValue(clazz.getSimpleName(),
                        "getDeclaredAnnotationsByType",
                        null != annotationClass ? annotationClass.getSimpleName() : "no param",
                        "annotationsByTypeName", annotationsByTypeName);
            }

        } else {
            //noinspection ConstantConditions
            printThenParamIsNull(clazz.getSimpleName(),
                    "getDeclaredAnnotationsByType",
                    null != annotationClass ? annotationClass.getSimpleName() : "no param");
        }
        print("");
    }
}
