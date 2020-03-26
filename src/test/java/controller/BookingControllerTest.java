package controller;

import dao.BookingDAO;
import dao.FlightDAO;
import dao.UserDAO;
import entity.Booking;
import entity.Flight;
import entity.Passenger;
import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utilities.Credentials;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class BookingControllerTest {

    BookingController controller;
    Booking booking;
    Passenger passenger;
    Flight flight;
    User user;
    UserDAO userDAO;
    FlightDAO flightDAO;

    @BeforeEach
    void creatingInstances() throws IOException {
        controller = new BookingController();
        user = new User("rafi","rafi");
        passenger = new Passenger("Rafi","Rafi");
        flight = new Flight("Istanbul","Dubai",12,2020,12,2,14,20);
        flight.setFlightID("32 FDS");
        userDAO = new UserDAO();
        userDAO.create(user);
        flightDAO = new FlightDAO();
        flightDAO.update(flight);
        booking = new Booking(user,passenger,flight.getFlightID());
    }

    @Test
    void saveBooking() {
        Credentials.addingUser(user.getUserName(),user.getPassword());
        assertTrue(controller.saveBooking(passenger.getName(),passenger.getSurname(),flight.getFlightID()));
    }

    @Test
    void getAllBookings() {
        Credentials.addingUser(user.getUserName(),user.getPassword());
        assertTrue(controller.saveBooking(passenger.getName(),passenger.getSurname(),flight.getFlightID()));
        assertTrue(controller.getAllBookings());
    }

    @Test
    void deleteBooking() throws IOException {
        Credentials.addingUser(user.getUserName(),user.getPassword());
        assertTrue(controller.saveBooking(passenger.getName(),passenger.getSurname(),flight.getFlightID()));
        assertTrue(controller.deleteBooking(Credentials.bookingID));
    }

    @Test
    void getBooking() {
        Credentials.addingUser(user.getUserName(),user.getPassword());
        assertTrue(controller.saveBooking(passenger.getName(),passenger.getSurname(),flight.getFlightID()));
        assertTrue(controller.getBooking(Credentials.bookingID).isPresent());
    }

    @Test
    void flghtsInThisDirection() {
        assertTrue(controller.flghtsInThisDirection(flight.getTo(),flight.getMonth(),flight.getDay()));
    }
}