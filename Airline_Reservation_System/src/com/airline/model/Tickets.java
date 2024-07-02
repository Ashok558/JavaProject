package com.airline.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

public class Tickets implements Serializable {
	private static final long serialVersionUID = 8834226529092155662L; // Match this with the one in both client and server

    private int ticket_id;
    private int user_id;
    private int flight_id;
    private Date bookingDate;
    private Time bookingTime;
    private String seat_number;

    public Tickets() {
        // Default constructor
    }

    public Tickets(int ticketId, int userId ,int flightId,
                   Date bookingDate, Time bookingTime, String seatNumber) {
        this.ticket_id = ticketId;
        this.user_id = userId;
        this.flight_id = flightId;
        this.bookingDate = bookingDate;
        this.bookingTime = bookingTime;
        this.seat_number=seatNumber;
    }

 

    // toString method (optional, for debugging/logging)

    @Override
	public String toString() {
		return "Tickets [ticket_id=" + ticket_id + ", user_id=" + user_id + ", flight_id=" + flight_id
				+ ", bookingDate=" + bookingDate + ", bookingTime=" + bookingTime + ", seat_number=" + seat_number
				+ "]";
	}
    // Getters and Setters
	public int getTicket_id() {
		return ticket_id;
	}

	public void setTicket_id(int ticket_id) {
		this.ticket_id = ticket_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getFlight_id() {
		return flight_id;
	}

	public void setFlight_id(int flight_id) {
		this.flight_id = flight_id;
	}

	public Date getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}

	public Time getBookingTime() {
		return bookingTime;
	}

	public void setBookingTime(Time bookingTime) {
		this.bookingTime = bookingTime;
	}

	public String getSeat_number() {
		return seat_number;
	}

	public void setSeat_number(String seat_number) {
		this.seat_number = seat_number;
	}
}
