package com.csf.config;

/**
 * @author: chenshf
 * @date: 18/8/26 13:29
 * @description:
 */
public class Function {
    /**
     * 方法名
     */
    private String funcName;
    /**
     * sql操作类型
     */
    private String sqlType;
    /**
     * 返回类型
     */
    private Object resultType;
    /**
     * sql语句
     */
    private String sql;

    public String getFuncName() {
        return funcName;
    }

    public void setFuncName(String funcName) {
        this.funcName = funcName;
    }

    public String getSqlType() {
        return sqlType;
    }

    public void setSqlType(String sqlType) {
        this.sqlType = sqlType;
    }

    public Object getResultType() {
        return resultType;
    }

    public void setResultType(Object resultType) {
        this.resultType = resultType;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }
}
