package com.example.newsrecommend.service;

import com.example.newsrecommend.Algorithm.AlgorithmRecNews;
import com.example.newsrecommend.Repository.NewsRepository;
import com.example.newsrecommend.Repository.RecommendationRepository;
import com.example.newsrecommend.Repository.UserRepository;
import com.example.newsrecommend.entity.News;
import com.example.newsrecommend.entity.NewsLog;
import com.example.newsrecommend.entity.Recommendation;
import com.example.newsrecommend.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RecommendationService {
    @Autowired
    private RecommendationRepository recommendationRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private NewsService newsService;
    @Autowired
    private NewsLogService newsLogService;
    private AlgorithmRecNews algorithmRecNews;

    public List<News> getRecNews(String userName){
        User user = userRepository.findByName(userName);
        Map<String,Integer > map =  AlgorithmRecNews.RecNewsNewUser(user);
        Map<String,List<News>> moudleNewsList = new HashMap<>();
        //取出每个类别的新闻, 根据时间倒序，每个类别的数量是map的valule
        List<News> result = new ArrayList<>(850);  //比800稍多一点
        for(String key:map.keySet()){
            List<News> newsList =newsService.getNewsByModule(key,0,map.get(key));
            result.addAll(newsList);
            moudleNewsList.put(key,newsList);
        }
        //最后只推荐40篇文章
        List<News> recomendNewsList = null;
        if(user.getKey().isEmpty()){
            //新用户，没有关键词，对于每个分类的文章，按照从前往后的顺序查找看该文章是否在NewsLog里出现过
            recomendNewsList = new ArrayList<>(8);
            for(String key:map.keySet()){
                Iterator<News> iterator = moudleNewsList.get(key).iterator() ;
                int count = 1;
                while(iterator.hasNext() && count>0){
                    News news = (News)iterator.next();
                    if(newsLogService.getNewsLogByUserAndNews(user,news)==null && recommendationRepository.findByUidAndNid(user.getId(),news.getId())==null){
                        recomendNewsList.add(news);
                        count--;
                    }
                }
            }
        }else{
            //非新用户，有关键词，则需要对于召回的文章按照关键词计算相似度，并排序
            /*List<News> filterResult = new ArrayList<>(850);
            for(News news:result){
                if(newsLogService.getNewsLogByUserAndNews(user,news) == null && recommendationRepository.findByUserIdAndNewsId(user.getId(),news.getId())==null){
                    filterResult.add(news);
                }
            }
            filterResult.sort((x,y)->{
                Map<String,Double> xKeyMap = x.getKey();
                Map<String,Double> yKeyMap = y.getKey();
                Map<String,Double> userKeyMap = user.getKey();
                Double similarityX = 0.0;
                Double similarityy = 0.0;
                for(String key:userKeyMap.keySet()) {
                    if (xKeyMap.containsKey(key)) {
                        similarityX += userKeyMap.get(key) * xKeyMap.get(key);
                    }
                    if (yKeyMap.containsKey(key)) {
                        similarityy += userKeyMap.get(key) * yKeyMap.get(key);
                    }
                }
                return similarityX.compareTo(similarityy);
            });
            if(filterResult.size()>40){
                recomendNewsList = filterResult.subList(0,39);
            }else{
                recomendNewsList = filterResult;
            }*/
            recomendNewsList = result.stream().filter(item->
                newsLogService.getNewsLogByUserAndNews(user,item) == null &&  recommendationRepository.findByUidAndNid(user.getId(),item.getId()) ==null
            ).sorted((x,y)->{
                Map<String,Double> xKeyMap = x.getKey();
                Map<String,Double> yKeyMap = y.getKey();
                Map<String,Double> userKeyMap = user.getKey();
                Double similarityX = 0.0;
                Double similarityy = 0.0;
                for(String key:userKeyMap.keySet()) {
                    if (xKeyMap.containsKey(key)) {
                        similarityX += userKeyMap.get(key) * xKeyMap.get(key);
                    }
                    if (yKeyMap.containsKey(key)) {
                        similarityy += userKeyMap.get(key) * yKeyMap.get(key);
                    }
                }
                return similarityX.compareTo(similarityy);
            }).map(item->item).collect(Collectors.toList()).subList(0,15);
        }
        //把推荐结果add到recommendation文档中
        if(recomendNewsList!=null && !recomendNewsList.isEmpty()){
            for(News news:recomendNewsList){
                recommendationRepository.save(new Recommendation(user.getId(),news.getId(),new Date()));
            }
        }
        return recomendNewsList;
    }
}
