package com.example.newsrecommend.controller;

import com.example.newsrecommend.entity.News;
import com.example.newsrecommend.entity.User;
import com.example.newsrecommend.service.NewsLogService;
import com.example.newsrecommend.service.NewsService;
import com.example.newsrecommend.service.UserService;
import jdk.nashorn.internal.runtime.UnwarrantedOptimismException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Controller
@RequestMapping("/newsrecommend/news")
public class NewsController {
    @Autowired
    NewsService newsService;
    @Autowired
    UserService userService;
    @Autowired
    NewsLogService newsLogService;

    @GetMapping(params = {"id", "name"})
    public String getNewsById(@RequestParam("id") String id, @RequestParam("name") String name, Model m) {
        User user = userService.getUserByName(name);
        News news = newsService.getNewsById(id);
        this.getNewsTrans(user, news);
        m.addAttribute(news);
        m.addAttribute("uname",name);
        return "news";
    }

    @Transactional(readOnly = false, rollbackFor = Throwable.class)
    public void getNewsTrans(User user, News news) {
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
        Map<String, Integer> moudleCount = user.getModuleCount();
        moudleCount.put(news.getModule(), moudleCount.get(news.getModule()) + 1);
        user.setModuleCount(moudleCount);
        userService.saveUser(user);
        newsLogService.addNewsLog(user, news);
    }
}
