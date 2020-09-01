package com.ls.uc.service.impl;

import com.ls.uc.dao.UserMapper;
import com.ls.uc.entity.pojo.User;
import com.ls.uc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public User login(String name, String pss) {
        return null;
    }

    @Override
    public List<User> getUserById(Integer id) {
        return null;
    }
}
