package controller;

import dao.FlightDAO;
import entity.Flight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FlightControllerTest {

    FlightController controller;
    Flight flight;
    FlightDAO flightDAO;

    @BeforeEach
    void creatingInstances(){
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