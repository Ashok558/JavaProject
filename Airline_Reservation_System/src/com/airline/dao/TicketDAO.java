package com.airline.dao;

import com.airline.model.Tickets;

import java.util.List;

public interface TicketDAO {
    boolean addTicket(Tickets ticket);
    Tickets getTicketById(int ticketId);
    List<Tickets> getAllTickets();
    // Add more methods as needed
}
