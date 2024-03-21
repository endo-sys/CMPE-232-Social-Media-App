package com.example.classes_connection;
import com.example.class_models.User_model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class User_Connection {
    static String url = "jdbc:mysql://localhost:3306/postagram/useSSL=false";
    static String username = "root"; // MySQL credentials
    static String password = "Enes1234!*";
    static String jdbc_driver = "com.mysql.cj.jdbc.Driver";
    static String insertion_querry = "INSERT INTO users (First_name, Last_name, user_nickname, following, followers, password)\n" +
            "VALUES" +
            "('?', '?', '?',?, ?, '?'),";
    static String select_user_by_id = "Select *from users where User_ID=?";
    static String delete_user = "Delete from users where User_ID=?";
    static String update_user = "Update users set First_name=?,Last_name=?,password= ? where User_ID =?";
    static String select_user_like_Name = "Select * from users where user_nickname like '%?%'";

    public User_Connection() {
    }

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

    public static void insert_user_database(User_model userModel) {
        try (Connection connection = get_connection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertion_querry);) {
            preparedStatement.setString(1, userModel.firstname);
            preparedStatement.setString(2, userModel.lastname);
            preparedStatement.setString(3, userModel.user_nickname);
            preparedStatement.setInt(4, userModel.following);
            preparedStatement.setInt(5, userModel.followers);
            preparedStatement.setString(6, userModel.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

    public static User_model get_user(int id) {
        User_model userModel = null;
        try (Connection connection = get_connection();
             PreparedStatement preparedStatement = connection.prepareStatement(select_user_by_id);) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String firstname = resultSet.getString("firstname");
                String lastname = resultSet.getString("lastname");
                String user_nickname = resultSet.getString("user_nickname");
                int following = resultSet.getInt("following");
                int followers = resultSet.getInt("followers");
                String password = resultSet.getString("password");
                userModel = new User_model(firstname, lastname, user_nickname, following, followers, password);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userModel;
    }

    public static List<User_model> get_user_by_nickname(String input_name) {
        List<User_model> userModelList = new ArrayList<User_model>();
        try (Connection connection = get_connection();
             PreparedStatement preparedStatement = connection.prepareStatement(select_user_like_Name);) {
            preparedStatement.setString(1, input_name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String firstname = resultSet.getString("firstname");
                String lastname = resultSet.getString("lastname");
                String user_nickname = resultSet.getString("user_nickname");
                int following = resultSet.getInt("following");
                int followers = resultSet.getInt("followers");
                String password = resultSet.getString("password");
                userModelList.add(new User_model(firstname, lastname, user_nickname, following, followers, password));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return userModelList;
    }

    public static boolean update_user(User_model userModel) {
        int update;
        try (Connection connection = get_connection();
             PreparedStatement preparedStatement = connection.prepareStatement(update_user);) {
            preparedStatement.setString(1, userModel.firstname);
            preparedStatement.setString(2, userModel.lastname);
            preparedStatement.setString(3, userModel.getPassword());
            update = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return update > 0;
    }

    public static boolean delete_user(int id) {
        boolean result = false;
        try (Connection connection = get_connection();
             PreparedStatement preparedStatement = connection.prepareStatement(delete_user)) {
            preparedStatement.setInt(1, id);
            int update = preparedStatement.executeUpdate();
            if (update > 0) {
                result = true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}