package controller;

import entity.User;
import service.ServiceUser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class ControllerUser {

    ServiceUser service;

    public ControllerUser() throws IOException {
        service = new ServiceUser();
    }

    public Optional<User> getUser(String userName) {
        return service.get(userName);
    }

    public boolean saveUser(String userName, String password) {
        User user = new User(userName, password);
        return service.save(user);
    }

    public ArrayList<User> getAllUsers() {
        return service.getAll();
    }

    public boolean deleteUser(String userName, String password) {
        return service.delete(userName, password);
    }
}
