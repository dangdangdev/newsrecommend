package com.example.newsrecommend.Repository;

import com.example.newsrecommend.entity.News;
import com.example.newsrecommend.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.List;
import java.util.Optional;

@Repository
public interface NewsRepository extends MongoRepository<News, String> {
    public List<News> getNewsByModule(String module, Pageable pageable);
    public News getNewsById(String id);
}
