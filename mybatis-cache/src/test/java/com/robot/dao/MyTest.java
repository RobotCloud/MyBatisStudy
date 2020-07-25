package com.robot.dao;

import com.robot.pojo.User;
import com.robot.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class MyTest {

    @Test
    public void cacheTest() {
        // 从开启sqlSession，到关闭为止，中间都属于一级缓存
        // 查出的内容会被缓存，在之间第二次查同样的数据时，直接在缓存中拿，无需再去数据库中查
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        User user = mapper.queryUserById(1);
        System.out.println(user);

        // 清理缓存
//        sqlSession.clearCache();

        User user2 = mapper.queryUserById(1);
        System.out.println(user);

        sqlSession.close();
    }

    @Test
    public void cache2Test() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        SqlSession sqlSession2 = MybatisUtils.getSqlSession();

        // 当一级缓存关闭时，会将缓存内容传给二级缓存
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.queryUserById(1);
        System.out.println(user);
        sqlSession.close();

        UserMapper mapper2 = sqlSession2.getMapper(UserMapper.class);
        User user2 = mapper2.queryUserById(1);
        System.out.println(user2);
        System.out.println(user==user2);
        sqlSession2.close();
    }
}
