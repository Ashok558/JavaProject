package com.airline.service;

import com.airline.dao.UserDAO;
import com.airline.model.User;

public class UserService {
    private UserDAO userDao; // Assume UserDao is injected via constructor or setter

    public UserService(UserDAO userDao) {
        this.userDao = userDao;
    }

    public boolean registerUser(User user) {
        // Example of business logic validation
        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty.");
        }

        // Check if the username already exists
        if (userDao.getUserByUsername(user.getUsername()) != null) {
            throw new IllegalArgumentException("Username already exists.");
        }

        // Example: Password strength validation
        if (user.getPassword().length() < 8) {
            throw new IllegalArgumentException("Password must be at least 8 characters long.");
        }

        // Example: Email format validation (simplified check)
        if (!user.getEmail().contains("@")) {
            throw new IllegalArgumentException("Invalid email format.");
        }

        // Call the DAO method to add the user if validation passes
        return userDao.addUser(user);
    }

    public User loginUser(String username, String password) {
        // Example: Validate credentials
        User user = userDao.getUserByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    // Add more service methods as needed
}
