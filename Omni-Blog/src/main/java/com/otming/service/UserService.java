package com.otming.service;

import com.otming.pojo.User;

import java.util.List;
import java.util.Map;


public interface UserService {


    List<User> findall();

    User selectUser(Map map);

    User selectByName(String username);

    int insertUser(Map map);

    /*删除用户*/
    int deleteUser(Map map);

    /*修改用户信息*/
    int updateUser(Map map);


}
