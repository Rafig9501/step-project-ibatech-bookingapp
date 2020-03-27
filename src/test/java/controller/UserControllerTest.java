package controller;

import dao.BookingDAO;
import dao.FlightDAO;
import dao.UserDAO;
import entity.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {

    UserController controller;
    UserDAO userDAO;
    FlightDAO flightDAO;
    BookingDAO bookingDAO;

    @BeforeEach
    void creatingInstances() throws IOException {
        controller = new UserController();
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
    void getUser() {
        User user = new User("Rafig","Rafig");
        assertTrue(userDAO.update(user));
        assertTrue(controller.getUser(user.getUserName()).isPresent());
    }

    @Test
    void saveUser() {
        User user = new User("Rafig","Rafig");
        assertTrue(controller.saveUser(user.getUserName(),user.getPassword()));
    }

    @Test
    void getAllUsers() {
        User user = new User("Rafig","Rafig");
        assertTrue(userDAO.update(user));
        assertFalse(controller.getAllUsers().isEmpty());
    }

    @Test
    void deleteUser() {
        User user = new User("Rafig","Rafig");
        assertTrue(userDAO.update(user));
        assertTrue(controller.deleteUser(user.getUserName(),user.getPassword()));
    }
}