package com.otming.dao;


import com.otming.pojo.BlogType;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface BlogTypeMapper {


    BlogType getBlogType(int id);


}
