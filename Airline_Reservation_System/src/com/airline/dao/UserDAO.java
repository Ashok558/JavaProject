package com.airline.dao;

import com.airline.model.User;

public interface UserDAO {
    boolean addUser(User user);
    User getUserByUsername(String username);
    // Add more methods as needed
}
