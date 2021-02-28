package com.huanghy.scaff.service;

import com.huanghy.scaff.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IUserService {

    User getById(long id);

    List<User> findAllUser();
}
