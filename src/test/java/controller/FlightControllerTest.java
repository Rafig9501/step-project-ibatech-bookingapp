package controller;

import dao.BookingDAO;
import dao.FlightDAO;
import dao.UserDAO;
import entity.Flight;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FlightControllerTest {

    FlightController controller;
    Flight flight;
    FlightDAO flightDAO;
    BookingDAO bookingDAO;
    UserDAO userDAO;

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
        userDAO = new UserDAO();
        bookingDAO = new BookingDAO();
        controller = new FlightController();
        flight = new Flight("Istanbul","Dubai",12,2020,12,2,14,20);
        flight.setFlightID("32 FDS");
        flightDAO = new FlightDAO();
        flightDAO.update(flight);
    }

    @Test
    void getFlight() {
        assertTrue(controller.getFlight(flight.getFlightID()).isPresent());
    }

    @Test
    void getAllFlights() {
        assertFalse(controller.getAllFlights().isEmpty());
    }

    @Test
    void isWithin24hour() {
        assertFalse(controller.isWithin24hour(flight));
    }
}