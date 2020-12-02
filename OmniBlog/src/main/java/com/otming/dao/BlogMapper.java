package com.otming.dao;

import com.otming.pojo.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Mapper
@Repository
public interface BlogMapper {

    List<Blog> getBlog(Map map);

    int insertBlog(Map map);

    int updateBlogById(Map map);

    int deleteBlogById(int id);


    List<Blog> selectBlog();

    List<Blog> getData(Map map);





}
