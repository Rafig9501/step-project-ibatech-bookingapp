package service;

import entity.Flight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FlightServiceTest {

    FlightService flightService;
    Flight flight;

    @BeforeEach
    void creatingInstances(){
        flightService = new FlightService();
        flight = new Flight("Istanbul","Dubai",12,2020,12,2,14,20);
        flight.setFlightID("32 FDS");
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
        assertTrue(flightService.delete("32 FDS"));
    }

    @Test
    void getAll() {
        assertFalse(flightService.getAll().isEmpty());
    }
}