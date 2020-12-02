package com.otming.service.serviceImpl;

import com.otming.dao.BlogMapper;
import com.otming.pojo.Blog;
import com.otming.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;


    public int insertBlog(Map map) {
        return blogMapper.insertBlog(map);
    }

    public int updateBlogById(Map map) {
        return blogMapper.updateBlogById(map);
    }

    public int deleteBlogById(int id) {
        return blogMapper.deleteBlogById(id);
    }



    public List<Blog> selectBlog() {
        return blogMapper.selectBlog();
    }



    public List<Blog> getData(Map map) {
        return blogMapper.getData(map);
    }



    @Override
    public List<Blog> getBlog(Map map) {
        return blogMapper.getBlog(map);
    }


    /* public List<Blog> selectBlogByName(String username) {
        return blogMapper.selectBlogByName(username);
    }*/

   /* @Override
    public List<Blog> selectBlogByType(String blogtype) {
        return blogMapper.selectBlogByType(blogtype);
    }*/

     /* public List<Blog> getDataByName(Map map) {
        return blogMapper.getDataByName(map);
    }*/

   /* @Override
    public List<Blog> getDataByType(Map map) {
        return blogMapper.getDataByType(map);
    }*/
}
