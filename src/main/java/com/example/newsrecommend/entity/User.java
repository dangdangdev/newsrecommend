package com.example.newsrecommend.entity;

import lombok.Data;
import org.hibernate.annotations.TypeDef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
@Document(collection = "User")
public class User implements Serializable{
    @Id
    private String id;

    private String name;

    private String password;

    private Map<String, Double> key = new HashMap<String, Double>();

    private Map<String,Integer> moduleCount = new HashMap<>();

    private Date lasteLogTime;

    public User(String name, String password, Map<String, Double> key, Date lasteLogTime) {
        //this.id = id;
        this.name = name;
        this.password = password;
        this.key = key;
        this.lasteLogTime = lasteLogTime;
        moduleCount.put("军事",1);
        moduleCount.put("体育",1);
        moduleCount.put("国际",1);
        moduleCount.put("生活",1);
        moduleCount.put("财经",1);
        moduleCount.put("娱乐",1);
        moduleCount.put("社会",1);
        moduleCount.put("科技",1);
    }


}
