package com.ls.uc.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dw.sc.common.bean.TokenBean;
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
    public User login(String name,String pass) {
        List<User> list = userMapper.selectList((Wrapper<User>) new QueryWrapper().eq("name",name));
        if(null==list){

        }else if(!list.get(0).getPwd().equals(pass)){

        }
        TokenBean tokenBean = TokenBean.builder()
                        .userId(list.get(0).getId())
                        .phone(list.get(0).getPhone()).build();

        return list.get(0);
    }

    @Override
    public List<User> getUserById(Integer id) {
 /*       Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(TOKEN_KEY))
                    .parseClaimsJws(token.replaceFirst(PRE_FIX, ""))
                    .getBody();
        } catch (Exception e) {
        }*/
        return null;
    }
}
