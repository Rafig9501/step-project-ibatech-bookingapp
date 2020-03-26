package dao;

import entity.Flight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FlightDAOTest {

    FlightDAO dao;
    Flight flight;

    @BeforeEach
    void creatingInstances(){
        dao = new FlightDAO();
        flight = new Flight("Istanbul","Dubai",12,2020,12,2,14,20);
    }

    @Test
    void get() {
        assertTrue(dao.create(flight));
        assertTrue(dao.get(flight.getFlightID()).isPresent());
    }

    @Test
    void create() {
        assertTrue(dao.create(flight));
    }

    @Test
    void delete() {
        assertTrue(dao.create(flight));
        assertTrue(dao.delete(flight.getFlightID()));
    }

    @Test
    void update() {
        assertTrue(dao.update(flight));
    }

    @Test
    void getAll() {
        assertTrue(dao.create(flight));
        assertFalse(dao.getAll().isEmpty());
    }
}