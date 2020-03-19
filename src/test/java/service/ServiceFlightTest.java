package service;

import entity.Flight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServiceFlightTest {

    ServiceFlight serviceFlight;
    Flight flight;

    @BeforeEach
    void creatingInstances(){
        serviceFlight = new ServiceFlight();
        flight = new Flight("Istanbul","Dubai",12,2020,12,2,14,20);
        flight.setFlightID("32 FDS");
    }

    @Test
    void get() {
        save();
        assertTrue(serviceFlight.get("32 FDS").isPresent());
    }

    @Test
    void save() {
        serviceFlight.save(flight);
    }

    @Test
    void delete() {
        assertTrue(serviceFlight.delete("32 FDS"));
    }

    @Test
    void getAll() {
        assertFalse(serviceFlight.getAll().isEmpty());
    }
}