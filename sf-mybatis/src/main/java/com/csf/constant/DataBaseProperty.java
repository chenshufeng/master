package com.csf.constant;

/**
 * @author chenshf
 * @date: 18/8/26 12:18
 * @description: 数据库连接属性
 */
public enum DataBaseProperty {
    DRIVECLASS("driveClass","驱动类"),
    USERNAME("userName","用户名"),
    PASSWORD("password","密码"),
    URL("url","连接串");

    private String name;
    private String desc;

    DataBaseProperty(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
