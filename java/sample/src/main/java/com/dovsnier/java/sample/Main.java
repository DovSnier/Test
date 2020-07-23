package com.dovsnier.java.sample;

import com.dovsnier.java.sample.cases.ReflectCase;

public class Main {

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());

        ReflectCase reflectCase = new ReflectCase();
        // 类
        reflectCase.reflect_case_class();
    }
}
