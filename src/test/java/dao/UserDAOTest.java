package dao;

import entity.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class UserDAOTest {

    UserDAO userDAO;
    User user;
    FlightDAO flightDAO;
    BookingDAO bookingDAO;

    @BeforeEach
    void creatingInstances() throws IOException {
        userDAO = new UserDAO();
        flightDAO = new FlightDAO();
        bookingDAO = new BookingDAO();
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
        user = new User("rafi","rafi");
        userDAO.create(user);
        assertTrue(userDAO.get(user.getUserName()).isPresent());
    }

    @Test
    void create() {
        user = new User("rafi","rafi");
        assertTrue(userDAO.create(user));
    }

    @Test
    void delete() {
        user = new User("rafi","rafi");
        assertTrue(userDAO.create(user));
        assertTrue(userDAO.delete(user.getUserName()));
    }

    @Test
    void update() {
        assertTrue(userDAO.update(user));
    }

    @Test
    void getAll() {
        user = new User("rafi","rafi");
        assertTrue(userDAO.create(user));
        assertFalse(userDAO.getAll().isEmpty());
    }
}