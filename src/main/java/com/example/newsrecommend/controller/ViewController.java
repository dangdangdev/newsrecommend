package com.example.newsrecommend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {
    @RequestMapping("/newsrecommend/login")
    public String login(Model m){
        return "login";
    }

    @RequestMapping("/newsrecommend")
    public String index(Model m){
        return "index";
    }
}
