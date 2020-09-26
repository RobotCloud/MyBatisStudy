package com.robot.dao;

import com.robot.pojo.Blog;
import com.robot.utils.IDutils;
import com.robot.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.*;

public class MyTest {

    @Test
    public void addInitBlog() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);

        Blog blog = new Blog();
        blog.setId(IDutils.getID());
        blog.setTitle("Mybatis如此简单");
        blog.setAuthor("robot");
        blog.setCreateTime(new Date());
        blog.setViews(9999);
        mapper.addBlog(blog);

        blog.setId(IDutils.getID());
        blog.setTitle("Java如此简单");
        mapper.addBlog(blog);

        blog.setId(IDutils.getID());
        blog.setTitle("Spring如此简单");
        mapper.addBlog(blog);

        blog.setId(IDutils.getID());
        blog.setTitle("微服务如此简单");
        mapper.addBlog(blog);

        sqlSession.close();

    }

    @Test
    public void queryBlog() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
        Map map = new HashMap();
//        map.put("title", "Java如此简单");
        map.put("author", "robot");
        List<Blog> blogs = blogMapper.queryBlog(map);
        for (Blog blog : blogs) {
            System.out.println(blog);
        }
        sqlSession.close();
    }

    @Test
    public void queryBlog2() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
        Map map = new HashMap();
        map.put("title", "Java如此简单");
        map.put("author", "robot");
        List<Blog> blogs = blogMapper.queryBlog2(map);
        for (Blog blog : blogs) {
            System.out.println(blog);
        }
        sqlSession.close();
    }

    @Test
    public void queryBlog3() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
        Map map = new HashMap();
        map.put("title", "Java如此简单");
        map.put("views", "9999");
        List<Blog> blogs = blogMapper.queryBlog3(map);
        for (Blog blog : blogs) {
            System.out.println(blog);
        }
        sqlSession.close();
    }

    @Test
    public void updateBlog() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
        Map map = new HashMap();
        map.put("id", "6d6cb532858b4fb1b0204ce5b8601c9f");
        map.put("title", "Java攻城狮-robot");
        //map.put("author", "baby");
        int blog = blogMapper.updateBlog(map);
        System.out.println(blog);
        sqlSession.close();
    }

    @Test
    public void queryBlogForeach() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        HashMap map = new HashMap();
        ArrayList<String> ids = new ArrayList();
        ids.add("6d6cb532858b4fb1b0204ce5b8601c9f");
        map.put("ids", ids);
        mapper.queryBlogForeach(map);
        sqlSession.close();
    }

    @Test
    public void deleteTest() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        HashMap map = new HashMap();
        ArrayList<String> ids = new ArrayList();
        ids.add("6d6cb532858b4fb1b0204ce5b8601c9f");
        map.put("ids", ids);
        mapper.queryBlogForeach(map);
        sqlSession.close();
    }
}
