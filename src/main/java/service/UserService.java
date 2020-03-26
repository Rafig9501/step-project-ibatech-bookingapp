package service;

import dao.UserDAO;
import entity.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class UserService {

    UserDAO userDAO;

    public UserService() throws IOException {
        userDAO = new UserDAO();
    }

    public Optional<User> get(String username) {
        return userDAO.get(username);
    }

    public boolean save(User user) {
        boolean userExist = getAll().stream().anyMatch(us -> us.getUserName().equals(user.getUserName()));
        if (!userExist) {
            return userDAO.update(user);
        }
        return false;
    }

    public ArrayList<User> getAll() {
        if (userDAO.file.exists()) return (ArrayList<User>) userDAO.getAll();
        return new ArrayList<>();
    }

    public boolean delete(String userName, String password) {
        boolean match = false;
        if (userDAO.file.exists()) {
            match = getAll().stream().anyMatch(user ->
                    user.getUserName().equals(userName) && user.getPassword().equals(password));
        }
        if (match) {
            return userDAO.delete(userName);
        }
        return false;
    }
}
