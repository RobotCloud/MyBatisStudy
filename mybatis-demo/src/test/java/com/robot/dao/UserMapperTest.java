package com.robot.dao;

import com.robot.entity.User;
import com.robot.entity.UserExample;
import com.robot.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * @Author 张宝旭
 * @Date 2020/9/26
 */
public class UserMapperTest {
    @Test
    public void selectByPrimaryKeyTest() {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.selectByPrimaryKey(1);
        System.out.println(user);
    }

    @Test
    public void UserExampleTest() {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andIdBetween(1, 3);
        List<User> users = mapper.selectByExample(userExample);
        for (User user : users) {
            System.out.println(user);
        }
    }
}
