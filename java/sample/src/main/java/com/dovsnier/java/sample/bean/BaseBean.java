package com.dovsnier.java.sample.bean;

import com.dovsnier.java.sample.annotation.Bean;
import com.dovsnier.java.sample.annotation.Type;

/**
 * BaseBean
 * Created by dovsnier on 2020/7/23.
 */

@Bean(name = "BaseBean")
public class BaseBean {

    public String author;
    protected String name;
    protected int what;
    protected int arg1;
    protected int arg2;
    @Type(name = "type")
    private int type;
    int anonym;

    public BaseBean() {
    }

    public BaseBean(int type) {
        this.type = type;
    }

    public BaseBean(int what, int type) {
        this.what = what;
        this.type = type;
    }

    public static BaseBean newInstance() {
        return new BaseBean();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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

    @Type
    public @Bean int getType() {
        return type;
    }

    @Bean
    public void setType(@Type int type) {
        this.type = type;
    }

    public int getAnonym() {
        return anonym;
    }

    public void setAnonym(int anonym) {
        this.anonym = anonym;
    }

    @Override
    public String toString() {
        return "BaseBean{" +
                "author='" + author + '\'' +
                ", name='" + name + '\'' +
                ", what=" + what +
                ", arg1=" + arg1 +
                ", arg2=" + arg2 +
                ", type=" + type +
                ", anonym=" + anonym +
                '}';
    }

    public class ExtInnerBase extends BaseBean {

    }

    public static class CommonInnerBase extends BaseBean {
    }
}
