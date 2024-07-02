package com.airline.dao;

import com.airline.model.Flights;

import java.util.List;

public interface FlightDAO {
   
    Flights getFlightById(int flightId);
    List<Flights> getAllFlights();
    List<Flights> searchFlights(String departure, String destination);
    boolean updateFlightSeats(int flightId, int numberSeats);
   
}
