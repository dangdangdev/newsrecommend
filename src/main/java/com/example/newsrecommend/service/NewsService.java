package com.example.newsrecommend.service;

import com.example.newsrecommend.Repository.NewsRepository;
import com.example.newsrecommend.entity.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService {
    @Autowired
    NewsRepository newsRepository;

    public List<News> getNewsByModule(String module,Integer page,Integer pagesize){
        Sort sort = Sort.by(Sort.Direction.DESC, "time");
        Pageable pageable = PageRequest.of(page, pagesize, sort);
        return newsRepository.getNewsByModule(module,pageable);
    }

    public News getNewsById(String id){
        return newsRepository.getNewsById(id);
    }
}
