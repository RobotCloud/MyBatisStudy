package com.robot.dao;

import com.robot.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    // 查询全部用户信息
    List<User> getUserList();

    // 存款
    int save(User user);

    // 根据银行卡号和密码 查询用户
    User selectUser(Map<String, String> map);

    // 根据银行卡号查询用户
    User getUserByCardNo(String cardNo);

    // 修改密码
    int modifyPassword(Map<String, String> map);

}
