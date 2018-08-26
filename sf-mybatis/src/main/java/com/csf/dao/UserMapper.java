package com.csf.dao;

import com.csf.entity.User;

/**
 * @author: chenshf
 * @date: 18/8/26 11:48
 * @description:
 */
public interface UserMapper {

    public User getUserInfoById(Long id);
}
