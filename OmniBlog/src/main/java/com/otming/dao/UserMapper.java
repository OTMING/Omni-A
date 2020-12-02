package com.otming.dao;

import com.otming.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface UserMapper {


    List<User> findall();


    /*根据传入的参数查询用户*/
    User selectUser(Map map);


    /*根据用户名查询用户*/
    User selectByName(String username);

    /*插入用户*/
    int insertUser(Map map);

    /*删除用户*/
    int deleteUser(Map map);

    /*修改用户信息*/
    int updateUser(Map map);

}
