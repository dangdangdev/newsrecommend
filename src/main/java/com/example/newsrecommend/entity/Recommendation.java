package com.example.newsrecommend.entity;


import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Document(collection = "Recommendation")
public class Recommendation implements Serializable {
    @Id
    private String id;

    private String uid;
    private String nid;
    private Date time;

    public Recommendation(String uid, String nid, Date time) {
        this.uid = uid;
        this.nid = nid;
        this.time = time;
    }
}
