package com.example.class_models;

import javax.xml.stream.events.Comment;

public class Comment_Model {
    public int Comment_ID;
    public int User_ID;
    public int Post_ID;
    public  int Comment_likes_;
    public  String Commant_Content;

    public Comment_Model(int comment_ID, int user_ID, int post_ID, int comment_likes_, String commant_Content) {
        Comment_ID = comment_ID;
        User_ID = user_ID;
        Post_ID = post_ID;
        Comment_likes_ = comment_likes_;
        Commant_Content = commant_Content;
    }
}
