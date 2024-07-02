package com.airline.service;

import com.airline.dao.FlightDAO;
import com.airline.model.Flights;

import java.sql.SQLException;
import java.util.List;

public class FlightService {

    private FlightDAO flightDAO;

    public FlightService(FlightDAO flightDAO) {
        this.flightDAO = flightDAO;
    }

    public List<Flights> searchFlights(String departure, String destination) throws SQLException {
        return flightDAO.searchFlights(departure, destination);
    }

    public Flights getFlightById(int flightId) throws SQLException {
        return flightDAO.getFlightById(flightId);
    }

    public boolean updateFlightSeats(int flightId, int numberSeats) throws SQLException {
        return flightDAO.updateFlightSeats(flightId, numberSeats);
    }

    // Add more service methods as needed
}
