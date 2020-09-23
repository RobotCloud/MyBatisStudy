package com.robot.dao;

import com.robot.entity.User;

import java.util.List;

/**
 * @Author zhangbaoxu
 * @Date 2020/9/23
 */
public interface UserDao {
    List<User> selectAllUser();
}
