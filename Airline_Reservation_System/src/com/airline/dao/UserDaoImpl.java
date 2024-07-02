package com.airline.dao;

import com.airline.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDaoImpl implements UserDAO {
    private Connection connection;

    public UserDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean addUser(User user) {
        String addUserQuery = "INSERT INTO users ( username, password,email) VALUES (?, ?, ?)";

        try (PreparedStatement addUserStmt = connection.prepareStatement(addUserQuery)) {
            addUserStmt.setString(1, user.getUsername());
            addUserStmt.setString(2, user.getPassword());
            addUserStmt.setString(3, user.getEmail());

            int result = addUserStmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

	
	@Override
	public User getUserByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}
}
