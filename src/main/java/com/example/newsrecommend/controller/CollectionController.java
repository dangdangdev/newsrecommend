package com.example.newsrecommend.controller;


import com.example.newsrecommend.Repository.NewsRepository;
import com.example.newsrecommend.Repository.UserRepository;
import com.example.newsrecommend.entity.Collection;
import com.example.newsrecommend.entity.News;
import com.example.newsrecommend.entity.User;
import com.example.newsrecommend.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/newsrecommend/collection")
public class CollectionController {
    @Autowired
    CollectionService collectionService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    NewsRepository newsRepository;

    @GetMapping
    public String getCollectionsByUser(@RequestParam("uname")String uname, Model model){
        User user = userRepository.findByName(uname);
        List<News> collectionNews = new ArrayList<>();
        List<Collection> collections = collectionService.getCollectionsByUser(user.getId());
        for(Collection collection : collections){
            News news =  newsRepository.getNewsById(collection.getNid());
            collectionNews.add(news);
        }
        model.addAttribute("newsList",collectionNews);
        model.addAttribute("uname",uname);
        return "collection";
    }

    @ResponseBody
    @PostMapping
    @Transactional
    public String addCollection(@RequestParam("uname")String uname,@RequestParam("nid")String nid){
        User user = userRepository.findByName(uname);
        if(collectionService.exsitCollection(user.getId(),nid))
            return "existed";
        collectionService.addCollection(user.getId(),nid);
        News news = newsRepository.getNewsById(nid);
        Map<String, Double> keyUser = user.getKey();
        Map<String, Double> keyNews = news.getKey();
        for (String key : keyNews.keySet()) {
            if (keyUser.containsKey(key)) {
                keyUser.put(key, keyUser.get(key) + keyNews.get(key));
            } else {
                keyUser.put(key, keyNews.get(key));
            }
        }
        user.setKey(keyUser);
        userRepository.save(user);
        return "success";
    }
}
