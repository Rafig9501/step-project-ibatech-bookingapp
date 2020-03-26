package dao;

import entity.Booking;
import entity.Flight;
import entity.Passenger;
import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class BookingDAOTest {

    BookingDAO dao;
    Booking booking;

    @BeforeEach
    void creatingInstances() throws IOException {
        dao = new BookingDAO();
        User user = new User("rafi","rafi");
        Passenger passenger = new Passenger("Rafi","Rafi");
        Flight flight = new Flight("Istanbul","Dubai",12,2020,12,2,14,20);
        flight.setFlightID("32 FDS");
        booking = new Booking(user,passenger,flight.getFlightID());
    }

    @Test
    void get() {
        dao.create(booking);
        assertTrue(dao.get(booking.getBookingID()).isPresent());
    }

    @Test
    void create() {
        assertTrue(dao.create(booking));
    }

    @Test
    void delete() {
        dao.create(booking);
        assertTrue(dao.delete(booking.getBookingID()));
    }

    @Test
    void update() {
        assertTrue(dao.update(booking));
    }

    @Test
    void getAll() {
        dao.create(booking);
        assertFalse(dao.getAll().isEmpty());
    }
}