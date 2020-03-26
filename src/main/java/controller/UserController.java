package controller;

import entity.User;
import service.UserService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class UserController {

    UserService service;

    public UserController() throws IOException {
        service = new UserService();
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
