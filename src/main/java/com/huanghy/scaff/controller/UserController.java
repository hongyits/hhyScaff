package com.huanghy.scaff.controller;

import com.huanghy.scaff.domain.User;
import com.huanghy.scaff.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class UserController {

    @Autowired
    private IUserService userService;


    @GetMapping("/getUser")
    public User getUser(){
        return userService.getById(1);
    }

    @GetMapping("/getAllUser")
    public List<User> getAllUser(){
        return userService.findAllUser();
    }

}
