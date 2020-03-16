package service;

import dao.UserDAO;
import entity.User;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Predicate;

public class ServiceUser {

    UserDAO userDAO;

    public ServiceUser() throws IOException {
        userDAO = new UserDAO();
    }

    public Optional<User> get(String username) throws IOException, ClassNotFoundException {
        return userDAO.get(username);
    }

    public boolean save(User user) throws IOException, ClassNotFoundException {
        boolean userExist = getAll().stream().anyMatch(us -> us.getUserName().equals(user.getUserName()));
        if (!userExist) {
            return userDAO.update(user);
        }
        return false;
    }

    public ArrayList<User> getAll() throws IOException, ClassNotFoundException {
        if (userDAO.file.exists()) return (ArrayList<User>) userDAO.getAll();
        return new ArrayList<>();
    }

    public boolean delete(String userName, String password) throws IOException, ClassNotFoundException {
        boolean match = false;
        if (userDAO.file.exists()){
        match = getAll().stream().anyMatch(user ->
                user.getUserName().equals(userName) && user.getPassword().equals(password));
        }
        if (match) {
            return userDAO.delete(userName);
        }
        return false;
    }
}
