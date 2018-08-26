package com.csf;

import com.csf.dao.UserMapper;
import com.csf.entity.User;
import com.csf.session.SqlSession;

/**
 * @author: chenshf
 * @date: 18/8/26 14:09
 * @description:
 */
public class Test {

    public static void main(String[] args) {

        SqlSession sqlSession = new SqlSession();

        UserMapper mapper = sqlSession.genProxyMapper(UserMapper.class);

        User user = mapper.getUserInfoById(1L);

        System.out.println(user.toString());

    }
}
