package com.example.newsrecommend.Algorithm;

public enum Module {
    SPORTS("体育"),
    LIFE("生活"),
    MILITARY("军事"),
    INTERNATION("国际"),
    FINANCE("财经"),
    ENTERTAINMENT("娱乐"),
    SOCIAL("社会"),
    TECHNOLOGY("科技");
    private String name;
    Module(String name) {
        this.name = name;
    }
}
