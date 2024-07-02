package com.airline.model;

import java.io.Serializable;

public class User implements Serializable{
    private int user_id;
    private String username;
    private String password;
    private String email;

    public User() {
        // Default constructor
    }

    public User(int userId, String username, String password, String email) {
        this.user_id = userId;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    // Getters and Setters

    public int getUserId() {
        return user_id;
    }

    public void setUserId(int userId) {
        this.user_id = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // toString method (optional, for debugging/logging)

    @Override
    public String toString() {
        return "User{" +
                "userId=" + user_id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
