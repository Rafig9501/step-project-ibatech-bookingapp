package service;

import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ServiceUserTest {

    ServiceUser serviceUser;
    User user;

    @BeforeEach
    void testInstances() throws IOException {
        serviceUser = new ServiceUser();
        user = new User("rafi", "rafa");
    }

    @Test
    void get() {
        assertTrue(serviceUser.get("rafi").isPresent());
    }

    // this method works only once for one user. it doesn't create same user more than once
    @Test
    void save() {
        assertTrue(serviceUser.save(user));
    }

    @Test
    void getAll(){
        save();
        assertFalse(serviceUser.getAll().isEmpty());
    }

    @Test
    void delete(){
        assertTrue(serviceUser.delete("rafig","rafa"));
    }
}