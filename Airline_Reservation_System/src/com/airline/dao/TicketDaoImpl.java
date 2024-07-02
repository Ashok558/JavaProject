package com.airline.dao;

import com.airline.model.Tickets;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class TicketDaoImpl implements TicketDAO {
    private Connection connection;

    public TicketDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean addTicket(Tickets ticket) {
        String addTicketQuery = "INSERT INTO Tickets (user_id, flight_id, bookingDate, bookingTime, seat_number) VALUES (?, ?, ?, ?, ?)";
        String updateFlightSeatsQuery = "UPDATE Flights SET available_seats = available_seats - 1 WHERE flight_id = ?";

        try (PreparedStatement addTicketStmt = connection.prepareStatement(addTicketQuery);
             PreparedStatement updateFlightSeatsStmt = connection.prepareStatement(updateFlightSeatsQuery)) {

            addTicketStmt.setInt(1, ticket.getUser_id());
            addTicketStmt.setInt(2, ticket.getFlight_id());
            addTicketStmt.setDate(3, ticket.getBookingDate());
            addTicketStmt.setTime(4, ticket.getBookingTime());
            addTicketStmt.setString(5, ticket.getSeat_number());

            int ticketResult = addTicketStmt.executeUpdate();

            if (ticketResult > 0) {
                updateFlightSeatsStmt.setInt(1, ticket.getFlight_id());
                int updateResult = updateFlightSeatsStmt.executeUpdate();
                return updateResult > 0;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

	@Override
	public Tickets getTicketById(int ticketId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Tickets> getAllTickets() {
		// TODO Auto-generated method stub
		return null;
	}
}
