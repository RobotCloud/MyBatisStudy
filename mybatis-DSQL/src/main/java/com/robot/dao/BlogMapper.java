package com.robot.dao;

import com.robot.pojo.Blog;

import java.util.List;
import java.util.Map;

public interface BlogMapper {

    int addBlog(Blog blog);

    List<Blog> queryBlog(Map map);

    List<Blog> queryBlog2(Map map);

    List<Blog> queryBlog3(Map map);

    int updateBlog(Map map);

    List<Blog> queryBlogForeach(Map map);
}
