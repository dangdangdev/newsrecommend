package com.example.newsrecommend.service;

import com.example.newsrecommend.Repository.UserRepository;
import com.example.newsrecommend.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;

@Service
@Transactional(readOnly = false, rollbackFor = Exception.class)
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public boolean existUser(String name){
        return userRepository.findByName(name)==null?false:true;
    }

    public User addUser(String name,String password){
        if(!this.existUser(name)){
            User user = new User(name,password,new HashMap<>(),new Date());
            return userRepository.save(user);
        }else{
            return null;
        }
    }

    public User userLogin(String name,String password){
        User user = userRepository.findByNameAndPassword(name,password);
        if(user!=null){
            user.setLasteLogTime(new Date());
            userRepository.save(user);
        }
        return user;
    }

    public User getUserById(String id){
        return userRepository.findById(id).get();
    }

    public User getUserByName(String name){
        return userRepository.findByName(name);
    }

    public User saveUser(User user){
        return userRepository.save(user);
    }
}
