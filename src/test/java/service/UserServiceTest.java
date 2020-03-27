package service;

import dao.BookingDAO;
import dao.FlightDAO;
import dao.UserDAO;
import entity.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    UserService userService;
    User user;
    UserDAO userDAO;
    BookingDAO bookingDAO;
    FlightDAO flightDAO;

    @BeforeEach
    void testInstances() throws IOException {
        userService = new UserService();
        bookingDAO = new BookingDAO();
        flightDAO = new FlightDAO();
        userDAO = new UserDAO();
        user = new User("rafi", "rafa");
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
        userDAO.create(user);
        assertTrue(userService.get("rafi").isPresent());
    }

    @Test
    void save() {
        User user = new User("rafa","rafa");
        assertTrue(userService.save(user));
    }

    @Test
    void getAll(){
        userDAO.create(user);
        assertFalse(userService.getAll().isEmpty());
    }

    @Test
    void delete(){
        userDAO.create(user);
        assertTrue(userService.delete("rafi","rafa"));
    }
}