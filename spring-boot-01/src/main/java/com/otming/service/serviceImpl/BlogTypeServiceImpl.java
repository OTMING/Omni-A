package com.otming.service.serviceImpl;

import com.otming.dao.BlogTypeMapper;
import com.otming.pojo.BlogType;
import com.otming.service.BlogTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BlogTypeServiceImpl implements BlogTypeService {

    @Autowired
    private BlogTypeMapper  blogTypeMapper;


    @Override
    public BlogType getBlogType(int id) {
        return blogTypeMapper.getBlogType(id);
    }
}
