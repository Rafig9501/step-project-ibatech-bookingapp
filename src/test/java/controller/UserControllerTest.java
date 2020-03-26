package controller;

import dao.UserDAO;
import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {

    UserController controller;
    UserDAO userDAO;

    @BeforeEach
    void creatingInstances() throws IOException {
        controller = new UserController();
        userDAO = new UserDAO();
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