package com.example.newsrecommend.entity;

import lombok.Cleanup;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.annotation.Documented;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
@Document(collection = "News")
public class News implements Serializable {
    @Id
    private String id;

    private String title;

    private String content;

    private String module;

    private Map<String, Double> key = new HashMap<String, Double>();

    private Date time;
    private String timeString;
    private String from;

    public News(String id, String title, String content, String module, Map<String, Double> key, Date time, String timeString, String from) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.module = module;
        this.key = key;
        this.time = time;
        this.timeString = timeString;
        this.from = from;
    }

    public News(String title, String content, String module, Map<String, Double> key, Date time, String timeString, String from) {
        this.title = title;
        this.content = content;
        this.module = module;
        this.key = key;
        this.time = time;
        this.timeString = timeString;
        this.from = from;
    }

    public News() {
    }
}
