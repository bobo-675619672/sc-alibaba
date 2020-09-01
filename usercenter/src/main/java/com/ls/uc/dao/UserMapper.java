package com.ls.uc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ls.uc.entity.pojo.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<User> {
}
