package com.example.class_models;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;
public class User_model {
    public String firstname;
    public  String lastname;
    public  String user_nickname;
    public int following;
    public  int followers;
    private   String password;

    public User_model(String firstname, String lastname, String user_nickname, int following, int followers, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.user_nickname = user_nickname;
        this.following = following;
        this.followers = followers;
        this.password =get_hashed(password);
    }
    public  String getPassword(){
        return  password;
    }
    public  String get_hashed(String password){
        MessageDigest msgDst = null;
        try {
            msgDst = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

// the digest() method is invoked to compute the message digest
// from an input digest() and it returns an array of byte
        byte[] msgArr = msgDst.digest(password.getBytes());

// getting signum representation from byte array msgArr
        BigInteger bi = new BigInteger(1, msgArr);

// Converting into hex value
        String hshtxt = bi.toString(16);

        while (hshtxt.length() < 32)
        {
            hshtxt = "0" + hshtxt;
        }
        return hshtxt;
    }
}

