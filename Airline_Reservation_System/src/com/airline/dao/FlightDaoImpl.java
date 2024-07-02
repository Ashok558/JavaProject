package com.airline.dao;

import com.airline.model.Flights;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class FlightDaoImpl implements FlightDAO {

    private Connection connection; // Assume connection is initialized elsewhere

    public FlightDaoImpl(Connection connection) {
        this.connection = connection;
    }

    
   

    @Override
    public Flights getFlightById(int flightId) {
        String query = "SELECT * FROM flights WHERE flight_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, flightId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Flights(
                        resultSet.getInt("flight_id"),
                        resultSet.getString("flight_number"),
                        resultSet.getString("departure"),
                        resultSet.getString("destination"),
                        resultSet.getTimestamp("departure_time"),
                        resultSet.getTimestamp("arrival_time"),
                        resultSet.getInt("available_seats"),
                        resultSet.getString("seats_locations"),
                        resultSet.getString("price_details")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Flights> getAllFlights() {
        List<Flights> flightsList = new ArrayList<>();
        String query = "SELECT * FROM flights";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Flights flight = new Flights(
                        resultSet.getInt("flight_id"),
                        resultSet.getString("flight_number"),
                        resultSet.getString("departure"),
                        resultSet.getString("destination"),
                        resultSet.getTimestamp("departure_time"),
                        resultSet.getTimestamp("arrival_time"),
                        resultSet.getInt("available_seats"),
                        resultSet.getString("seats_locations"),
                        resultSet.getString("price_details")
                );
                flightsList.add(flight);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flightsList;
    }

    @Override
    public List<Flights> searchFlights(String departure, String destination) {
        List<Flights> flightsList = new ArrayList<>();
        String query = "SELECT * FROM flights WHERE departure = ? AND destination = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, departure);
            statement.setString(2, destination);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Flights flight = new Flights(
                        resultSet.getInt("flight_id"),
                        resultSet.getString("flight_number"),
                        resultSet.getString("departure"),
                        resultSet.getString("destination"),
                        resultSet.getTimestamp("departure_time"),
                        resultSet.getTimestamp("arrival_time"),
                        resultSet.getInt("available_seats"),
                        resultSet.getString("seats_locations"),
                        resultSet.getString("price_details")
                );
                flightsList.add(flight);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flightsList;
    }

    @Override
    public boolean updateFlightSeats(int flightId, int numberSeats) {
        String query = "UPDATE flights SET available_seats = ? WHERE flight_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, numberSeats);
            statement.setInt(2, flightId);
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Add more methods for delete, update, search flights, etc.
}
