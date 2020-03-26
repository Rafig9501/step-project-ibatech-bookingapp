package service;

import dao.UserDAO;
import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    UserService userService;
    User user;
    UserDAO userDAO;

    @BeforeEach
    void testInstances() throws IOException {
        userService = new UserService();
        userDAO = new UserDAO();
        user = new User("rafi", "rafa");
    }

    @Test
    void get() {
        userDAO.create(user);
        assertTrue(userService.get("rafi").isPresent());
    }

    // this method works only once for one user. it doesn't create same user more than once
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