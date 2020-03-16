package controller;

import entity.User;
import service.ServiceUser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public class ControllerUser {

    ServiceUser service;

    public ControllerUser() throws IOException {
        service = new ServiceUser();
    }

    public Optional<User> getUser(String userName) throws IOException, ClassNotFoundException {
            return service.get(userName);
    }

    public boolean saveUser(String userName, String password){
        User user = new User(userName,password);
        try {
            return service.save(user);
        } catch (IOException | ClassNotFoundException e) {
            return false;
        }
    }

    public ArrayList<User> getAllUsers(){
        try {
            return service.getAll();
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }

    public boolean deleteUser(String userName, String password){
        try {
            return service.delete(userName,password);
        } catch (IOException | ClassNotFoundException e) {
            return false;
        }
    }
}
