package com.example.newsrecommend.service;

import com.example.newsrecommend.Repository.NewsLogRepository;
import com.example.newsrecommend.Repository.NewsRepository;
import com.example.newsrecommend.entity.News;
import com.example.newsrecommend.entity.NewsLog;
import com.example.newsrecommend.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class NewsLogService {
    @Autowired
    private NewsLogRepository newsLogRepository;

    public NewsLog addNewsLog(User user, News news){
        if(newsLogRepository.findByUidAndNid(user.getId(),news.getId())==null){
            NewsLog newsLog = new NewsLog(user.getId(),news.getId(), new Date());
            return newsLogRepository.save(newsLog);
        }
        return null;
    }

    public NewsLog getNewsLogByUserAndNews(User user,News news){
        NewsLog newsLog = newsLogRepository.findByUidAndNid(user.getId(),news.getId());
        return newsLog;
    }
}
