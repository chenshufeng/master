package com.csf.proxy;

import com.csf.config.Configer;
import com.csf.config.Function;
import com.csf.config.MapperBean;
import com.csf.session.SqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author: chenshf
 * @date: 18/8/26 13:06
 * @description: mapper 代理
 */
public class MapperProxy implements InvocationHandler {

    /**
     * 用于获取加载后的配置信息
     */
    private Configer configer;

    /**
     * 用于操作数据库
     */
    private SqlSession sqlSession;

    public MapperProxy(Configer configer, SqlSession sqlSession) {
        this.configer = configer;
        this.sqlSession = sqlSession;
    }

    /**
     * 代理调用
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        MapperBean mapperBean = configer.getMapperBean("UserInfo.xml");
        if(mapperBean.getInterfaceName().equals(method.getDeclaringClass().getName())){
            List<Function> functions = mapperBean.getList();
            for (Function function:functions){
                if(function.getFuncName().equals(method.getName())){
                    return sqlSession.selectById(function.getSql(),args);
                }
            }
        }
        return null;
    }
}
