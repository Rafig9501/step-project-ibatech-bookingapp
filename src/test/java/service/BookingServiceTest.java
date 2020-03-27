package service;

import dao.BookingDAO;
import dao.FlightDAO;
import dao.UserDAO;
import entity.Booking;
import entity.Flight;
import entity.Passenger;
import entity.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utilities.Credentials;

import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

class BookingServiceTest {

    BookingService bookingService;
    Booking booking;
    User user;
    BookingDAO bookingDAO;
    FlightDAO flightDAO;
    UserDAO userDAO;

    @BeforeEach
    void creatingInstances() throws IOException {
        bookingDAO = new BookingDAO();
        flightDAO = new FlightDAO();
        userDAO = new UserDAO();
        bookingService = new BookingService();
        user = new User("rafiq","rafiq");
        Flight flight = new Flight("Istanbul","Dubai",23,2020,3,22,10,15);
        flight.setFlightID("32 FDS");
        FlightService flightService = new FlightService();
        flightService.save(flight);
        Passenger passenger = new Passenger("Rafig","Mammadzada");
        passenger.setId("32d");
        booking = new Booking(user,passenger,"32 FDS");
    }

    @AfterEach
    void deletingEverything(){
        if (flightDAO.file.exists()){
            flightDAO.file.delete();
        }
        if (userDAO.file.exists()){
            userDAO.file.delete();
        }
        if (bookingDAO.file.exists()){
            bookingDAO.file.delete();
        }
    }

    @Test
    void get() throws IOException {
        bookingService = new BookingService();
        user = new User("rafiq","rafiq");
        Flight flight = new Flight("Istanbul","Dubai",23,2020,3,22,10,15);
        flight.setFlightID("32 FDS");
        FlightService flightService = new FlightService();
        flightService.save(flight);
        Passenger passenger = new Passenger("Rafig","Mammadzada");
        passenger.setId("32d");
        booking = new Booking(user,passenger,"32 FDS");
        bookingService.save(booking);
        assertTrue(bookingService.get(booking.getBookingID()).isPresent());
    }

    @Test
    void save() {
        assertTrue(bookingService.save(booking));
    }

    @Test
    void getAll() {
        save();
        Credentials.addingUser(user.getUserName(),user.getPassword());
        assertFalse(bookingService.getAll().isEmpty());
    }

    @Test
    void delete() {
        assertTrue(bookingService.delete("32d"));
    }
}