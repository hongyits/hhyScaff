package com.huanghy.scaff.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huanghy.scaff.domain.User;
import com.huanghy.scaff.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements IUserService {

    @Resource
    UserMapper userMapper;

    @Override
    public User getById(long id) {
        return null;
    }

    @Override
    public List<User> findAllUser() {
        List<User> allUser = userMapper.findAllUser();
        return allUser;
    }
}
