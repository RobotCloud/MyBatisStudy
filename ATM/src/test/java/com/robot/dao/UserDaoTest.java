package com.robot.dao;

import com.robot.pojo.User;
import com.robot.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDaoTest {

    @Test
    public void getUserListTest() {
        // 1.获得SqlSession对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        // 2.执行SQL
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList = userMapper.getUserList();
        for (User user : userList) {
            System.out.println(user);
        }
        sqlSession.close();
    }

    @Test
    public void save() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        User user = new User("6250008888001", "125001", "211421199901018888", "老大", "15188880001", 25000);
        user.setBalance(12345);

        int result = userMapper.save(user);
        if (result >= 0) {
            System.out.println("存取成功");
        }
        sqlSession.close();
    }

    @Test
    public void login() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        Map<String, String> map = new HashMap<String, String>();
        map.put("cardNo", "6250008888001");
        map.put("password", "125001");

        User user = userMapper.selectUser(map);
        System.out.println(user);

        sqlSession.close();
    }

    @Test
    public void getUserByCardNoTest() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        User user = userMapper.getUserByCardNo("6250008888001");
        System.out.println(user);

        sqlSession.close();
    }

    @Test
    public void getUserByCardNoTestTest() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        Map<String, String> map = new HashMap<String, String>();
        map.put("password", "125888");
        map.put("cardNo", "6250008888001");
        int result = userMapper.modifyPassword(map);

        if (result >= 0) {
            sqlSession.commit();
        } else {
            sqlSession.rollback();
        }

        sqlSession.close();
    }

}
