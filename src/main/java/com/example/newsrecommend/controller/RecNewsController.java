package com.example.newsrecommend.controller;

import com.example.newsrecommend.entity.News;
import com.example.newsrecommend.entity.Recommendation;
import com.example.newsrecommend.service.NewsService;
import com.example.newsrecommend.service.RecommendationService;
import com.example.newsrecommend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/newsrecommend/recnews")
public class RecNewsController {
    @Autowired
    UserService userService;
    @Autowired
    NewsService newsService;
    @Autowired
    RecommendationService recommendationService;

    @GetMapping
    public List<News> getRecNews(@RequestParam("name") String userName){
        return recommendationService.getRecNews(userName);
    }

}
