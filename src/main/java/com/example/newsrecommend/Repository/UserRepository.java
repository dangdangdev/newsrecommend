package com.example.newsrecommend.Repository;

import com.example.newsrecommend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    public User findByName(String name);
    public User findByNameAndPassword(String name,String password);
}
