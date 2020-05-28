package com.example.newsrecommend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class NewsrecommendApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewsrecommendApplication.class, args);
    }

}
