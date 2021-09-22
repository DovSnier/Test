package com.dvsnier.java.sample.bean;

import com.dvsnier.java.sample.annotation.Bean;
import com.dvsnier.java.sample.annotation.Type;

/**
 * ValueBean
 * Created by dovsnier on 2020/7/23.
 */
@Bean(name = "ValueBean")
@Type(name = "ValueBean")
public class ValueBean<@Type T> extends @Bean BaseBean {

    protected @Type T value;
    @Bean
    protected Object object;

    public ValueBean() {
    }

    public ValueBean(int type) {
        super(type);
    }

    public ValueBean(int what, int type) {
        super(what, type);
    }

    public @Type T getValue() {
        return value;
    }

    public void setValue(@Type T value) {
        this.value = value;
    }

    public @Bean
    Object getObject() {
        return object;
    }

    public void setObject(@Bean Object object) {
        this.object = object;
    }

    @Override
    public String toString() {
        return "ValueBean{" +
                "value=" + value +
                ", object=" + object +
                '}';
    }
}
