package com.csf.session;

import com.csf.config.Configer;
import com.csf.exec.Excutor;
import com.csf.exec.SimpleExcutor;
import com.csf.proxy.MapperProxy;

import java.lang.reflect.Proxy;

/**
 * @auther: chenshf
 * @date: 18/8/26 11:50
 * @description:
 */
public class SqlSession {

    private Configer xml = new Configer();
    private Excutor excutor = new SimpleExcutor(xml);

    /**
     * 提供查询方法
     * @param sql
     * @param args
     * @return
     */
    public <T> T selectById(String sql,Object[] args){
        //通过执行器真正查询
        return excutor.select(sql,args);
    }

    /**
     * 生成接口代理
     * @param cls
     * @return
     */
    public <T> T genProxyMapper(Class<T> cls){
        return (T) Proxy.newProxyInstance(cls.getClassLoader(),new Class[]{cls},new MapperProxy(xml,this));
    }
}
