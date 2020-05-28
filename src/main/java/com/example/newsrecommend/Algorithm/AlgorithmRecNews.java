package com.example.newsrecommend.Algorithm;

import com.example.newsrecommend.entity.User;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public  class AlgorithmRecNews {
    public static Map<String,Integer> RecNewsNewUser(User user){
        Map<String,Integer> moduleCount = user.getModuleCount();
        ConcurrentHashMap<String,Integer> tempModuleCount = new ConcurrentHashMap<>();
        //召回：
        // 计算每个类别的比例
        Integer countAll = moduleCount.values().stream().mapToInt(v->v).sum();
        for(String key:moduleCount.keySet()){
            tempModuleCount.put(key,1000 * moduleCount.get(key)/countAll);
        }
        return tempModuleCount;
    }
}
