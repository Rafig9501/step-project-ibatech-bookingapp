package controller;

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
import service.BookingService;
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
    BookingService bookingService;
    BookingDAO bookingDAO;

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

    @BeforeEach
    void creatingInstances() throws IOException {
        controller = new BookingController();
        user = new User("rafi","rafi");
        passenger = new Passenger("Rafi","Rafi");
        flight = new Flight("Istanbul","Dubai",12,2020,12,2,14,20);
        flight.setFlightID("45 FDS");
        userDAO = new UserDAO();
        userDAO.create(user);
        flightDAO = new FlightDAO();
        bookingDAO = new BookingDAO();
        flightDAO.update(flight);
        booking = new Booking(user,passenger,flight.getFlightID());
        bookingService = new BookingService();
    }

    @Test
    void saveBooking() {
        Credentials.loggedIn.clear();
        Credentials.addingUser(user.getUserName(),user.getPassword());
        assertTrue(controller.saveBooking(passenger.getName(),passenger.getSurname(),flight.getFlightID()));
    }

    @Test
    void getAllBookings() {
        Credentials.loggedIn.clear();
        Credentials.addingUser(user.getUserName(),user.getPassword());
        assertTrue(controller.saveBooking(passenger.getName(),passenger.getSurname(),flight.getFlightID()));
        assertTrue(controller.getAllBookings());
    }

    @Test
    void deleteBooking() {
        Credentials.loggedIn.clear();
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