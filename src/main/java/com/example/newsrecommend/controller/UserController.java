package com.example.newsrecommend.controller;

import com.example.newsrecommend.entity.User;
import com.example.newsrecommend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/newsrecommend/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping()
    public User userAdd(@RequestParam(value = "name") String name,@RequestParam(value="password") String password){
        User user= userService.addUser(name,password);
        return user;
    }

    @GetMapping()
    public User userLogin(@RequestParam(value = "name") String name,@RequestParam(value="password") String password){
        return userService.userLogin(name,password);
    }
}
