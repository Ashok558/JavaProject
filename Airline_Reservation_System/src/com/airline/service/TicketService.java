package com.airline.service;

import com.airline.dao.TicketDAO;
import com.airline.model.Tickets;

import java.util.List;

public class TicketService {

    private TicketDAO ticketDao; // Assume TicketDAO is injected via constructor or setter

    public TicketService(TicketDAO ticketDao) {
        this.ticketDao = ticketDao;
    }

    public boolean addTicket(Tickets ticket) {
        // Business logic validation can be added here if needed
        return ticketDao.addTicket(ticket);
    }

    public Tickets getTicketById(int ticketId) {
        return ticketDao.getTicketById(ticketId);
    }

    public List<Tickets> getAllTickets() {
        return ticketDao.getAllTickets();
    }

    // Add more service methods as needed
}
