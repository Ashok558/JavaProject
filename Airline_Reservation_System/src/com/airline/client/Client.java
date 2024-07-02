package com.airline.client;

import com.airline.model.Flights;
import com.airline.model.Tickets;
import com.airline.model.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Client {
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private Scanner scanner;

    public Client(String ip, int port) {
        try {
            socket = new Socket(ip, port);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            scanner = new Scanner(System.in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        try {
            while (true) {
                System.out.println("===== Airline Reservation System Menu =====");
                System.out.println("1. Register User");
                System.out.println("2. Get All Flights");
                System.out.println("3. Book Ticket");
                System.out.println("4. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        registerUser();
                        break;
                    case 2:
                        getAllFlights();
                        break;
                    case 3:
                        bookTicket();
                        break;
                    case 4:
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void registerUser() throws IOException, ClassNotFoundException {
        System.out.println("Enter user details:");
        System.out.print("Email ID: ");
        String email = scanner.nextLine();
        while (!isValidEmail(email)) {
            System.out.print("Invalid email format. Enter again: ");
            email = scanner.nextLine();
        }

        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();
        while (!isValidPassword(password)) {
            System.out.print("Password must be at least 8 characters long and contain at least one letter, one number, and one special character. Enter again: ");
            password = scanner.nextLine();
        }

        User user = new User();
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(password);

        out.writeObject(user);
        out.flush();

        String response = (String) in.readObject();
        System.out.println("Server response: " + response);
    }

    private void getAllFlights() throws IOException, ClassNotFoundException {
        out.writeObject("getAllFlights");
        out.flush();

        List<Flights> flights = (List<Flights>) in.readObject();
        if (flights != null) {
            for (Flights flight : flights) {
                System.out.println(flight);
            }
        } else {
            System.out.println("No flights found.");
        }
    }

    private void bookTicket() throws IOException, ClassNotFoundException {
        try {
            System.out.println("Enter ticket details:");
            System.out.print("User ID: ");
            int userId = scanner.nextInt();
            System.out.print("Flight ID: ");
            int flightId = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            System.out.print("Booking date (yyyy-MM-dd): ");
            String bookingDate = scanner.nextLine();
            while (!isValidDate(bookingDate)) {
                System.out.print("Invalid date format. Enter again (yyyy-MM-dd): ");
                bookingDate = scanner.nextLine();
            }
            System.out.print("Booking time (HH:mm:ss): ");
            String bookingTime = scanner.nextLine();
            while (!isValidTime(bookingTime)) {
                System.out.print("Invalid time format. Enter again (HH:mm:ss): ");
                bookingTime = scanner.nextLine();
            }
            System.out.print("Seat number: ");
            String seatNumber = scanner.nextLine();

            Tickets ticket = new Tickets();
            ticket.setUser_id(userId);
            ticket.setFlight_id(flightId);
            ticket.setBookingDate(Date.valueOf(bookingDate));
            ticket.setBookingTime(Time.valueOf(bookingTime));
            ticket.setSeat_number(seatNumber);

            out.writeObject(ticket);
            out.flush();

            String response = (String) in.readObject();
            System.out.println("Server response: " + response);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid date or time format. Please try again.");
        }
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean isValidPassword(String password) {
        String passwordRegex = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        Pattern pattern = Pattern.compile(passwordRegex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    private boolean isValidDate(String date) {
        try {
            Date.valueOf(date);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    private boolean isValidTime(String time) {
        try {
            Time.valueOf(time);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public static void main(String[] args) {
        Client client = new Client("localhost", 8019);
        client.start();
    }
}
