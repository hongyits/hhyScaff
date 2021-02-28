package com.huanghy.scaff.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huanghy.scaff.domain.User;

import java.util.List;

public interface UserMapper extends  BaseMapper<User> {

    public List<User> findAllUser();

}
