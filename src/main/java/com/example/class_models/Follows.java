package com.example.class_models;

public class Follows {
    public  int User_ID;
    public int Followed_ID;

    public Follows(int user_ID, int followed_ID) {
        User_ID = user_ID;
        Followed_ID = followed_ID;
    }
}
