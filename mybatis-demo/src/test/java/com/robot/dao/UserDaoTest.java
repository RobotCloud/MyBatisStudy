package com.robot.dao;

import com.robot.entity.User;
import com.robot.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * @Author zhangbaoxu
 * @Date 2020/9/23
 */
public class UserDaoTest {
    @Test
    public void selectAllUser() {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        List<User> users = mapper.selectAllUser();
        for (User user : users) {
            System.out.println(user);
        }
    }
}
