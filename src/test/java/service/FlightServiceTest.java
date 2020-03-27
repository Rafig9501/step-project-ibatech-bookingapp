package service;

import dao.BookingDAO;
import dao.FlightDAO;
import dao.UserDAO;
import entity.Flight;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FlightServiceTest {

    FlightService flightService;
    Flight flight;
    BookingDAO bookingDAO;
    FlightDAO flightDAO;
    UserDAO userDAO;

    @BeforeEach
    void creatingInstances() throws IOException {
        bookingDAO = new BookingDAO();
        flightDAO = new FlightDAO();
        userDAO = new UserDAO();
        flightService = new FlightService();
        flight = new Flight("Istanbul","Dubai",12,2020,12,2,14,20);
        flight.setFlightID("32 FDS");
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
        save();
        assertTrue(flightService.get("32 FDS").isPresent());
    }

    @Test
    void save() {
        flightService.save(flight);
    }

    @Test
    void delete() {
        flightService.save(flight);
        assertTrue(flightService.delete("32 FDS"));
    }

    @Test
    void getAll() {
        assertFalse(flightService.getAll().isEmpty());
    }
}