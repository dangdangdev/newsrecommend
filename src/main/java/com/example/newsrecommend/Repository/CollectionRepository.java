package com.example.newsrecommend.Repository;

import com.example.newsrecommend.entity.Collection;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollectionRepository extends MongoRepository<Collection,String> {
    public List<Collection> getCollectionByUidOrderByTime(String uid);
    public Collection getCollectionByUidAndNid(String uid,String nid);
}
