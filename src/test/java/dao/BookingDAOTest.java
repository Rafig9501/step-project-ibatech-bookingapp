package dao;

import entity.Booking;
import entity.Flight;
import entity.Passenger;
import entity.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class BookingDAOTest {

    BookingDAO bookingDAO;
    Booking booking;
    UserDAO userDAO;
    FlightDAO flightDAO;

    @BeforeEach
    void creatingInstances() throws IOException {
        bookingDAO = new BookingDAO();
        userDAO = new UserDAO();
        flightDAO = new FlightDAO();
        User user = new User("rafi","rafi");
        Passenger passenger = new Passenger("Rafi","Rafi");
        Flight flight = new Flight("Istanbul","Dubai",12,2020,12,2,14,20);
        flight.setFlightID("32 FDS");
        booking = new Booking(user,passenger,flight.getFlightID());
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
    void get() {
        bookingDAO.create(booking);
        assertTrue(bookingDAO.get(booking.getBookingID()).isPresent());
    }

    @Test
    void create() {
        assertTrue(bookingDAO.create(booking));
    }

    @Test
    void delete() {
        bookingDAO.create(booking);
        assertTrue(bookingDAO.delete(booking.getBookingID()));
    }

    @Test
    void update() {
        assertTrue(bookingDAO.update(booking));
    }

    @Test
    void getAll() {
        bookingDAO.create(booking);
        assertFalse(bookingDAO.getAll().isEmpty());
    }
}