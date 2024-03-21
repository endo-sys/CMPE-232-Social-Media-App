package com.example.classes_connection;
import java.sql.*;

import com.example.class_models.Post_Model;
import com.example.class_models.User_model;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.example.classes_connection.User_Connection.select_user_like_Name;

public class Post_Connection {
    static String url = "jdbc:mysql://localhost:3306/postagram/useSSL=false";
    static String username = "root"; // MySQL credentials
    static String password = "Enes1234!*";
    static String jdbc_driver = "com.mysql.cj.jdbc.Driver";
    Post_Connection(){}
    static  String insert_intopost= "INSERT INTO posts (User_ID, Post_Date, Post_ID, Post_Likes, Post_Title, Post_Content)" +
            "Values (?, ?, ?, ?,?,?)";
    static  String delete_post = "Delete from posts where POST_ID=?";
    static String update_post= "Update posts set Post_Title=?,Post_Content=? where Post_ID=?";
    static  String get_theall_Posts ="Select * from posts";
    static  String get_user_posts = "Select * from posts where User_ID=?";

    protected static java.sql.Connection get_connection() {
        java.sql.Connection connection = null;
        try {
            Class.forName(jdbc_driver);
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            System.out.println("Connection has been failed");
        }

        return connection;
    }


    //User_ID, Post_Date, Post_ID, Post_Likes, Post_Title, Post_Content
    public  static  void  insert_post_into_database(Post_Model postModel){
        try (Connection connection = get_connection();
             PreparedStatement preparedStatement = connection.prepareStatement(insert_intopost);) {
            preparedStatement.setInt(1, postModel.User_ID);
            Date POST_DATE = new Date(postModel.Post_Date.toString());
            preparedStatement.setDate(2, java.sql.Date.valueOf(POST_DATE.toString()));
            preparedStatement.setInt(3, postModel.Post_Likes);
            preparedStatement.setString(4,postModel.post_title);
            preparedStatement.setString(5,postModel.Post_Content);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
   //User_ID, Post_Date, Post_ID, Post_Likes, Post_Title, Post_Content
    public static List<Post_Model> GETALLPOSTS() {
        List<Post_Model> Post_model_list = new ArrayList<Post_Model>();
        try (Connection connection = get_connection();
             PreparedStatement preparedStatement = connection.prepareStatement(get_theall_Posts);) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                java.sql.Date Post_date = resultSet.getDate("Post_Date");
                int Post_likes = resultSet.getInt("Post_Likes");
                int User_Id = resultSet.getInt("User_ID");
               String post_content = resultSet.getString("Post_Content");
               String post_title = resultSet.getString("Post_Title");
                Post_model_list.add(new Post_Model(User_Id,Post_date,post_title,Post_likes,post_content));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return Post_model_list;
    }
    public static List<Post_Model> GET_USER_pOSTS() {
        List<Post_Model> Post_model_list = new ArrayList<Post_Model>();
        try (Connection connection = get_connection();
             PreparedStatement preparedStatement = connection.prepareStatement(get_theall_Posts);) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                java.sql.Date Post_date = resultSet.getDate("Post_Date");
                int Post_likes = resultSet.getInt("Post_Likes");
                int User_Id = resultSet.getInt("User_ID");
                String post_content = resultSet.getString("Post_Content");
                String post_title = resultSet.getString("Post_Title");
                Post_model_list.add(new Post_Model(User_Id,Post_date,post_title,Post_likes,post_content));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return Post_model_list;
    }


}
