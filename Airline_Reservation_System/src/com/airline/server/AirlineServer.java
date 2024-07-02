package com.airline.server;

import com.airline.dao.FlightDAO;
import com.airline.dao.FlightDaoImpl;
import com.airline.dao.TicketDAO;
import com.airline.dao.TicketDaoImpl;
import com.airline.dao.UserDAO;
import com.airline.dao.UserDaoImpl;
import com.airline.model.Flights;
import com.airline.model.Tickets;
import com.airline.model.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class AirlineServer {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private Connection connection;
    private UserDAO userDao;
    private FlightDAO flightDao;
    private TicketDAO ticketDao;

    public AirlineServer(int port) {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server started. Listening on port " + port);
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/AirlineReservationSystem", "root", "Ashok0130@");
            System.out.println("Database connected!");

            userDao = new UserDaoImpl(connection);
            flightDao = new FlightDaoImpl(connection);
            ticketDao = new TicketDaoImpl(connection);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void start() throws SQLException {
        try {
            while (true) {
                System.out.println("Waiting for client...");
                clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress().getHostName());

                out = new ObjectOutputStream(clientSocket.getOutputStream());
                in = new ObjectInputStream(clientSocket.getInputStream());

                handleClient();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleClient() throws SQLException {
        try {
            while (true) {
                Object receivedObject = in.readObject();

                if (receivedObject instanceof User) {
                    User user = (User) receivedObject;
                    System.out.println("Received user: " + user);

                    boolean added = userDao.addUser(user);
                    System.out.println("User added: " + added);

                    out.writeObject("User registered successfully!");
                    out.flush();
                } else if (receivedObject instanceof String && ((String) receivedObject).equalsIgnoreCase("getAllFlights")) {
                    List<Flights> flights = flightDao.getAllFlights();
                    out.writeObject(flights);
                    out.flush();
                } else if (receivedObject instanceof Tickets) {
                    Tickets ticket = (Tickets) receivedObject;
                    System.out.println("Received ticket: " + ticket);

                    boolean added = ticketDao.addTicket(ticket);
                    if (added) {
                        System.out.println("Ticket booked successfully!");
                        out.writeObject("Ticket booked successfully!");
                    } else {
                        System.out.println("Failed to book ticket.");
                        out.writeObject("Failed to book ticket.");
                    }
                    out.flush();
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws SQLException {
        AirlineServer server = new AirlineServer(8019); // Adjust port number here
        server.start();
    }
}
