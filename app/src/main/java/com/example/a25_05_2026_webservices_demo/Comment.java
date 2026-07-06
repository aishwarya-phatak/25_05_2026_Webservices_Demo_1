package com.example.a25_05_2026_webservices_demo;

public class Comment {
    private int id;
    private String body;
    private int postId;
    private int likes;
    private User user;


    public Comment(int id, String body, int postId, int likes, User user) {
        this.id = id;
        this.body = body;
        this.postId = postId;
        this.likes = likes;
        this.user = user;
    }
}
