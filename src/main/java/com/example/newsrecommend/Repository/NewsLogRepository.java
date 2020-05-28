package com.example.newsrecommend.Repository;

import com.example.newsrecommend.entity.News;
import com.example.newsrecommend.entity.NewsLog;
import com.example.newsrecommend.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.List;
import java.util.Optional;

@Repository
public interface NewsLogRepository extends MongoRepository<NewsLog, String> {
    public NewsLog findByUidAndNid(String uid,String nid);
}
