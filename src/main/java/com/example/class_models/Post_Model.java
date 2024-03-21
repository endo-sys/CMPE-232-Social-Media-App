package com.example.class_models;

import java.sql.Date;
import java.time.LocalDateTime;

public class Post_Model {
    public int User_ID;
    public java.sql.Date Post_Date;
    public String post_title;
    public  int Post_Likes;
    public  String Post_Content;

    public Post_Model(int user_ID, java.sql.Date post_Date, String post_title, int post_Likes, String post_Content) {
        User_ID = user_ID;
        Post_Date = post_Date;
        this.post_title = post_title;
        Post_Likes = post_Likes;
        Post_Content = post_Content;
    }
}
