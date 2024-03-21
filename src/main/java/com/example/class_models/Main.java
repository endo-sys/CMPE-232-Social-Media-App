package com.example.class_models;

import java.io.*;
import java.sql.*;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) throws Exception
    {
        String url = "jdbc:mysql://localhost:3306/postagram";
        String username = "root"; // MySQL credentials
        String password = "Enes1234!*";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, username, password);
        System.out.println("Connection Established successfully");
        Statement st = con.createStatement();
       String userQuery = "create table users(User_ID INT NOT NULL," +
               "First_name Varchar(20) NOT NULL ," +
               "Last_name VARCHAR(20) NOT NULL ," +
               "user_nickname VARCHAR(12)NOT NULL ," +
               "following INT," +
               "followers INT," +
               "password VARCHAR(16) NOT NULL," +
               "PRIMARY KEY(User_ID))";
       String post_query=  "CREATE  table posts("+
               "User_ID INT NOT NULL," +
               "Post_Date DATE NOT NULL," +
               "Post_ID INT NOT NULL,"+
               "Post_Likes INT," +
               "Post_Content VARCHAR(140),"+
               "PRIMARY KEY(Post_Id)," +
               "foreign key(User_ID) references users(User_ID))";
       String followinguery =  "CREATE TABLE follows (" +
               "    User_ID INT NOT NULL," +
               "    Followed_ID INT NOT NULL," +
               "    PRIMARY KEY(User_ID, Followed_ID)," +
               "    FOREIGN KEY(User_ID) REFERENCES users(User_ID)," +
               "    FOREIGN KEY(Followed_ID) REFERENCES users(User_ID)" +
               ");";

       String blocked_users = "CREATE TABLE block_users (" +
               "    User_ID INT NOT NULL," +
               "    Blocked_ID INT NOT NULL," +
               "    PRIMARY KEY(User_ID, Blocked_ID)," +
               "    FOREIGN KEY(User_ID) REFERENCES users(User_ID)," +
               "    FOREIGN KEY(Blocked_ID) REFERENCES users(User_ID)" +
               ");";
       String comments_queery = "create table comments(" +
               "Comments_ID INT NOT NULL," +
               "User_ID INT NOT NULL," +
               "Post_ID INT NOT NULL," +
               "Comment_likes_ INT," +
               "Commant_Content VARCHAR(140)," +
               "foreign key(User_ID)references users(User_ID)," +
               "foreign key(Post_ID)references posts(Post_ID)," +
               "primary key(Comments_ID))";

       String post_likes = "CREATE TABLE post_likes (" +
               "    User_ID INT NOT NULL," +
               "    Post_ID INT NOT NULL," +
               "    PRIMARY KEY(User_ID, Post_ID)," +
               "    FOREIGN KEY(User_ID) REFERENCES users(User_ID)," +
               "    FOREIGN KEY(Post_ID) REFERENCES posts(Post_ID)" +
               ");";
       String comment_likes ="CREATE TABLE comment_likes (" +
               "    User_ID INT NOT NULL," +
               "    Comments_ID INT NOT NULL," +
               "   Post_ID INT NOT NULL,"+
               "    PRIMARY KEY(User_ID, Comments_ID,Post_ID)," +
               "    FOREIGN KEY(User_ID) REFERENCES users(User_ID)," +
               "FOREIGN KEY(Post_ID) REFERENCES posts(POST_ID),"+
               "    FOREIGN KEY(Comments_ID) REFERENCES comments(Comments_ID)" +
               ");";

        // st.executeUpdate(userQuery);
        // st.executeUpdate(post_query);
        //st.executeUpdate(followinguery);
         //st.executeUpdate(blocked_users);
         //st.executeUpdate(comments_queery);
         //st.executeUpdate(comment_likes);
         //st.executeUpdate(post_likes);
        User_model newuser = new User_model("enes","erdogan","erdonesh31",0,0,"pass");
        String insertion_querry="INSERT INTO users (User_ID, First_name, Last_name, user_nickname, following, followers, password)\n" +
                "VALUES" +
                "(1, 'John', 'Doe', 'johnny87', 150, 200, 'pass123')," +
                "(2, 'Jane', 'Smith', 'jane_s', 100, 120, 'secret456')," +
                "(3, 'Mike', 'Johnson', 'mike123', 80, 50, 'mikepass')," +
                "(4, 'Alice', 'Williams', 'ali_w', 300, 250, 'alicepass123')," +
                "(5, 'Bob', 'Miller', 'bobm', 50, 80, 'bobby567')," +
                "(6, 'Eva', 'Brown', 'evab', 120, 180, 'evapassword')," +
                "(7, 'Chris', 'Jones', 'chrisj', 90, 60, 'chrispass')," +
                "(8, 'Sophie', 'Anderson', 'sophie_a', 200, 300, 'sophiepass')," +
                "(9, 'David', 'Clark', 'dave_c', 180, 150, 'davepass123')," +
                "(10, 'Emily', 'Moore', 'emmo', 60, 40, 'emilypass');";

        //st.executeUpdate(insertion_querry);
        //st.executeUpdate("ALTER TABLE posts ADD COLUMN Post_Title Varchar(10) AFTER  Post_Likes;");
        String insertion_into_posts = "INSERT INTO posts (User_ID, Post_Date, Post_ID, Post_Likes, Post_Title, Post_Content)" +
                "VALUES" +
                "(1, '2024-01-02', 1, 0, 'FirstPost', 'This is my first post! #excited')," +
                "(2, '2024-01-03', 2, 0, 'QuickUpd', 'Just sharing a quick update!')," +
                "(3, '2024-01-04', 3, 0, 'HelloWorl', 'Hello everyone! How s your day going?')," +
                "(4, '2024-01-05', 4, 0, 'WkndVibes', 'Enjoying the weekend! #weekendvibes')," +
                "(5, '2024-01-06', 5, 0, 'NewYrRes', 'New year, new goals! #resolutions')," +
                "(6, '2024-01-07', 6, 0, 'GrateMom', 'Feeling grateful for the little things.')," +
                "(7, '2024-01-08', 7, 0, 'ExplorePl', 'Exploring new places! #travel')," +
                "(8, '2024-01-09', 8, 0, 'QuietEve', 'Quiet evening at home. #relaxing')," +
                "(9, '2024-01-10', 9, 0, 'ExcitProj', 'Excited about the upcoming project!')," +
                "(10, '2024-01-11', 10, 0, 'RandomTho', 'Just a random thought for today');";
       // st.executeUpdate(insertion_into_posts);
        String insertion_into_comments = "INSERT INTO comments (Comments_ID, User_ID, Post_ID,Comment_likes_,Commant_Content)" +
                "VALUES" +
                "(1, 2, 1, 0, 'Great first post!')," +
                "(2, 3, 1, 0, 'Welcome to the platform!')," +
                "(3, 4, 2, 0, 'Nice update!')," +
                "(4, 5, 3, 0, 'How is everyone doing?')," +
                "(5, 6, 3, 0, 'Feeling great, thanks!')," +
                "(6, 7, 4, 0, 'Enjoy your weekend!')," +
                "(7, 8, 5, 0, 'What are your resolutions?')," +
                "(8, 9, 5, 0, 'Best of luck with your goals!')," +
                "(9, 10, 6, 0, 'Gratitude is key.')," +
                "(10, 1, 7, 0, 'Beautiful place!');";
        //st.executeUpdate(insertion_into_comments);
       String insertion_into_follows ="INSERT INTO follows (User_ID, Followed_ID)" +
               "VALUES" +
               "(1, 2),"+
               "(1, 3)," +
               "(2, 4)," +
               "(3, 1)," +
               "(4, 5)," +
               "(5, 1)," +
               "(6, 7)," +
               "(7, 8)," +
               "(8, 9)," +
               "(9, 10);";
      // st.executeUpdate(insertion_into_follows);
        String imsertion_into_blocks="INSERT INTO block_users (User_ID, Blocked_ID)" +
                "VALUES" +
                "(1, 3)," +
                "(2, 4)," +
                "(3, 1)," +
                "(5, 1)," +
                "(6, 7)," +
                "(7, 8)," +
                "(8, 9)," +
                "(9, 10)," +
                "(10, 1);";
       // st.executeUpdate(imsertion_into_blocks);
       String insertion_into_Post_likes = "INSERT INTO post_likes (User_ID, Post_ID)" +
               "VALUES" +
               "(1, 1)," +
               "(2, 1)," +
               "(3, 2),"+
               "(4, 2)," +
               "(5, 3)," +
               "(6, 3)," +
               "(7, 4)," +
               "(8, 4)," +
               "(9, 5)," +
               "(10, 6);";
       //st.executeUpdate(insertion_into_Post_likes);
String insert_into_comments_likes= "INSERT INTO comment_likes (User_ID, Comments_ID, Post_ID)" +
        "VALUES" +
        "(1, 1, 1)," +
        "(2, 1, 1)," +
        "(3, 2, 2)," +
        "(4, 3, 3)," +
        "(5, 4, 4)," +
        "(6, 5, 5)," +
        "(7, 6, 6)," +
        "(8, 7, 7)," +
        "(9, 8, 8)," +
        "(10, 9, 9);";
//st.executeUpdate(insert_into_comments_likes);
    }
}

