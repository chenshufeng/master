package com.csf.config;


import java.util.List;

/**
 * @author: chenshf
 * @date: 18/8/26 13:27
 * @description:
 */
public class MapperBean {
    /**
     * 接口名
     */
    private String interfaceName;

    /**
     * 接口下面所有的方法
     */
    private List<Function> list;

    public List<Function> getList() {
        return list;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public void setList(List<Function> list) {
        this.list = list;
    }
}
