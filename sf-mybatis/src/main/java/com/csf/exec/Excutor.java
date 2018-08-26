package com.csf.exec;

/**
 * @auther: chenshf
 * @date: 18/8/26 11:53
 * @description:
 */
public interface Excutor {

    public <T> T select(String sql, Object[] args);
}
