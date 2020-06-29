package com.example.newsrecommend.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.Date;

@Data
@Document(collection = "Collection")
public class Collection {
    @Id
    private String id;

    private String uid;
    private String nid;

    private Date time;
    public Collection(String uid, String nid,Date time) {
        this.uid = uid;
        this.nid = nid;
        this.time = time;
    }
}
