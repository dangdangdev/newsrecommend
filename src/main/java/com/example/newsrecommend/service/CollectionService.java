package com.example.newsrecommend.service;


import com.example.newsrecommend.Repository.CollectionRepository;
import com.example.newsrecommend.entity.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CollectionService {
    @Autowired
    private CollectionRepository collectionRepository;

    public List<Collection> getCollectionsByUser(String uid){
        return collectionRepository.getCollectionByUidOrderByTime(uid);
    }

    public boolean exsitCollection(String uid,String nid){
        return collectionRepository.getCollectionByUidAndNid(uid,nid)!=null;
    }

    public void addCollection(String uid, String nid){
        Collection collection = new Collection(uid,nid,new Date());
        collectionRepository.save(collection);
    }
}
