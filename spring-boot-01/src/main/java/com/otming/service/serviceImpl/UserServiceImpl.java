package com.otming.service.serviceImpl;

import com.otming.dao.UserMapper;
import com.otming.pojo.User;
import com.otming.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserMapper userMapper;


    public List<User> findall() {
        return userMapper.findall();
    }

    public User selectUser(Map map) {
        return userMapper.selectUser(map);

    }

    public User selectByName(String username) {
        return userMapper.selectByName(username);
    }

    public int insertUser(Map map) {

        return userMapper.insertUser(map);

    }

    public int deleteUser(Map map) {
        return userMapper.deleteUser(map);
    }

    public int updateUser(Map map) {
        return userMapper.updateUser(map);
    }


    public void setUserMapper(UserMapper userMapper) {
    }
}
