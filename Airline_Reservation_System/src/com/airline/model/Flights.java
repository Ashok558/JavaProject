package com.airline.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Flights implements Serializable{
    private int flight_id;
    private String flight_number;
    private String departure;
    private String destination;
    private Timestamp departure_time;
    private Timestamp arrival_time;
    private int available_seats;
    private String seats_locations;
    private String price_details;

    public Flights() {
        // Default constructor
    }

    public Flights(int flightId, String flightNumber, String departure, String destination,
                  Timestamp departureTime, Timestamp arrivalTime, int availableSeats,
                  String seatsLocations, String priceDetails) {
        this.flight_id = flightId;
        this.flight_number = flightNumber;
        this.departure = departure;
        this.destination = destination;
        this.departure_time = departureTime;
        this.arrival_time = arrivalTime;
        this.available_seats = availableSeats;
        this.seats_locations = seatsLocations;
        this.price_details = priceDetails;
    }

    // Getters and Setters

    public int getFlightId() {
        return flight_id;
    }

    public void setFlightId(int flightId) {
        this.flight_id = flightId;
    }

    public String getFlightNumber() {
        return flight_number;
    }

    public void setFlightNumber(String flightNumber) {
        this.flight_number = flightNumber;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Timestamp getDepartureTime() {
        return departure_time;
    }

    public void setDepartureTime(Timestamp departureTime) {
        this.departure_time = departureTime;
    }

    public Timestamp getArrivalTime() {
        return arrival_time;
    }

    public void setArrivalTime(Timestamp arrivalTime) {
        this.arrival_time = arrivalTime;
    }

    public int getAvailableSeats() {
        return available_seats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.available_seats = availableSeats;
    }

    public String getSeatsLocations() {
        return seats_locations;
    }

    public void setSeatsLocations(String seatsLocations) {
        this.seats_locations = seatsLocations;
    }

    public String getPriceDetails() {
        return price_details;
    }

    public void setPriceDetails(String priceDetails) {
        this.price_details = priceDetails;
    }

    // toString method (optional, for debugging/logging)

    @Override
    public String toString() {
        return "Flight{" +
                "flightId=" + flight_id +
                ", flightNumber='" + flight_number + '\'' +
                ", departure='" + departure + '\'' +
                ", destination='" + destination + '\'' +
                ", departureTime=" + departure_time +
                ", arrivalTime=" + arrival_time +
                ", availableSeats=" + available_seats +
                ", seatsLocations='" + seats_locations + '\'' +
                ", priceDetails='" + price_details + '\'' +
                '}';
    }
}
