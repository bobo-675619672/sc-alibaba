package com.ls.uc.service;


import com.ls.uc.entity.pojo.User;

import java.util.List;

public interface UserService {

    User login(String name,String pass);

    List<User> getUserById(Integer id);


}
