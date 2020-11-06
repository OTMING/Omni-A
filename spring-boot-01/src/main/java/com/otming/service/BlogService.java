package com.otming.service;

import com.otming.pojo.Blog;

import java.util.List;
import java.util.Map;

public interface BlogService {

    int insertBlog(Map map);

    int updateBlogById(Map map);

    int deleteBlogById(int id);

    Blog selectBlogById(int id);

    List<Blog> selectBlog();

    List<Blog> selectBlogByName(String username);

    List<Blog> selectBlogByType(String blogtype);

    List<Blog> getData(Map map);

    List<Blog> getDataByName(Map map);

    List<Blog> getDataByType(Map map);

    List<Blog> getBlog(Map map);
}
