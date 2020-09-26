package com.robot.utils;


import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MybatisUtils {

    private static SqlSessionFactory sqlSessionFactory;
    private static ThreadLocal<SqlSession> threadLocal = new ThreadLocal<SqlSession>();

    static {
        try {
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("创建sqlSessionFactory失败");
        }
    }

    // 获取连接
    public static SqlSession getSqlSession() {
        SqlSession sqlSession = threadLocal.get();
        if (sqlSession == null) {
            sqlSession = sqlSessionFactory.openSession();
            threadLocal.set(sqlSession);
        }
        return sqlSession;
    }

    // 提交事务
    public static void commit() {
        SqlSession sqlSession = getSqlSession();
        sqlSession.commit();
    }

    // 回滚事务
    public static void rollback() {
        SqlSession sqlSession = getSqlSession();
        sqlSession.rollback();
    }

    // 关闭连接
    public static void close() {
        SqlSession sqlSession = getSqlSession();
        if (sqlSession != null) {
            threadLocal.remove();
            sqlSession.close();
        }
    }
}
