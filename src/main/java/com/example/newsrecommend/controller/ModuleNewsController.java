package com.example.newsrecommend.controller;

import com.example.newsrecommend.entity.News;
import com.example.newsrecommend.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/newsrecommend/modulenews")
public class ModuleNewsController {
    @Autowired
    NewsService newsService;

    @GetMapping
    public List<News> getNewsByModule(@RequestParam("module")String module,@RequestParam("page")Integer page,@RequestParam("pagesize")Integer pagesize){
        List<News> list = newsService.getNewsByModule(module,page,pagesize);
        return list;
    }


}
