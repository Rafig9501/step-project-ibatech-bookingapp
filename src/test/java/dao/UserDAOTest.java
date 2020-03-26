package dao;

import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class UserDAOTest {

    UserDAO dao;
    User user;

    @BeforeEach
    void creatingInstances() throws IOException {
        dao = new UserDAO();

    }

    @Test
    void get() {
        user = new User("rafi","rafi");
        dao.create(user);
        assertTrue(dao.get(user.getUserName()).isPresent());
    }

    @Test
    void create() {
        user = new User("rafi","rafi");
        assertTrue(dao.create(user));
    }

    @Test
    void delete() {
        user = new User("rafi","rafi");
        assertTrue(dao.create(user));
        assertTrue(dao.delete(user.getUserName()));
    }

    @Test
    void update() {
        assertTrue(dao.update(user));
    }

    @Test
    void getAll() {
        user = new User("rafi","rafi");
        assertTrue(dao.create(user));
        assertFalse(dao.getAll().isEmpty());
    }
}