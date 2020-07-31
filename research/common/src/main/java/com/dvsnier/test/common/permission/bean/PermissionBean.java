package com.dvsnier.test.common.permission.bean;

import com.dvsnier.base.bean.IndexBaseBean;

import java.util.Arrays;

/**
 * PermissionBean
 * Created by dovsnier on 2020/7/27.
 */
public class PermissionBean extends IndexBaseBean {

    protected String name;
    protected String msg;
    protected String packageName;
    protected String permission;
    protected String[] permissions;

    public PermissionBean() {
    }

    public PermissionBean(int itemType) {
        super(itemType);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String[] getPermissions() {
        return permissions;
    }

    public void setPermissions(String[] permissions) {
        this.permissions = permissions;
    }

    @Override
    public String toString() {
        return "PermissionBean{" +
                "name='" + name + '\'' +
                "msg='" + msg + '\'' +
                ", packageName='" + packageName + '\'' +
                ", permission='" + permission + '\'' +
                ", permissions=" + Arrays.toString(permissions) +
                '}';
    }
}