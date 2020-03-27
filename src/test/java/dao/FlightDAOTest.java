package dao;

import entity.Flight;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FlightDAOTest {

    FlightDAO flightDAO;
    Flight flight;
    UserDAO userDAO;
    BookingDAO bookingDao;

    @BeforeEach
    void creatingInstances() throws IOException {
        flightDAO = new FlightDAO();
        userDAO = new UserDAO();
        bookingDao = new BookingDAO();
        flight = new Flight("Istanbul","Dubai",12,2020,12,2,14,20);
    }

    @AfterEach
    void deletingEverything(){
        if (flightDAO.file.exists()){
            flightDAO.file.delete();
        }
        if (userDAO.file.exists()){
            userDAO.file.delete();
        }
        if (bookingDao.file.exists()){
            bookingDao.file.delete();
        }
    }

    @Test
    void get() {
        assertTrue(flightDAO.create(flight));
        assertTrue(flightDAO.get(flight.getFlightID()).isPresent());
    }

    @Test
    void create() {
        assertTrue(flightDAO.create(flight));
    }

    @Test
    void delete() {
        assertTrue(flightDAO.create(flight));
        assertTrue(flightDAO.delete(flight.getFlightID()));
    }

    @Test
    void update() {
        assertTrue(flightDAO.update(flight));
    }

    @Test
    void getAll() {
        assertTrue(flightDAO.create(flight));
        assertFalse(flightDAO.getAll().isEmpty());
    }
}