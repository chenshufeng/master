package com.csf.exec;

import com.csf.config.Configer;
import com.csf.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @auther: chenshf
 * @date: 18/8/26 11:54
 * @description:
 */
public class SimpleExcutor implements Excutor {

    private Configer configer;

    public SimpleExcutor(Configer configer){
        this.configer = configer;
    }

    public Connection getConn(){
        return configer.getConn();
    }

    @Override
    public <T> T select(String sql, Object[] args){
        Connection conn = getConn();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setLong(1, (Long) args[0]);
            resultSet = preparedStatement.executeQuery();
            User user = new User();
            while (resultSet.next()){
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString(2));
            }
            return (T) user;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(resultSet != null){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
