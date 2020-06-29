package com.example.newsrecommend.controller;


import com.example.newsrecommend.Repository.NewsRepository;
import com.example.newsrecommend.Repository.UserRepository;
import com.example.newsrecommend.entity.Collection;
import com.example.newsrecommend.entity.News;
import com.example.newsrecommend.entity.User;
import com.example.newsrecommend.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
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
        model.addAttribute(collectionNews);
        return "collection";
    }

    @PostMapping
    public String addCollection(@RequestParam("uname")String uname,@RequestParam("nid")String nid){
        User user = userRepository.findByName(uname);
        if(collectionService.exsitCollection(user.getId(),nid))
            return "existed";
        collectionService.addCollection(user.getId(),nid);
        return "success";
    }

}
