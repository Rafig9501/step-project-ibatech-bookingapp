package dao;

import entity.User;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserDAO implements DAO<User> {

    public final File file = new File("users.bin");

    public UserDAO() throws IOException {
        if (!file.exists()) {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(new ArrayList<User>());
        }
    }

    @Override
    public Optional<User> get(String username) {
        try {
            ArrayList<User> users = (ArrayList<User>) getAll();
            return users.stream().filter(user -> user.getUserName().equals(username)).findFirst();
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public boolean create(User user) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            List<User> users = new ArrayList<>();
            users.add(user);
            oos.writeObject(users);
            oos.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean delete(String userName) {
        try {
            List<User> updatedUserList = getAll().stream().filter(user ->
                    !user.getUserName().equals(userName)).collect(Collectors.toList());
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(updatedUserList);
            oos.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean update(User user) {
        try {
            ArrayList<User> users = (ArrayList<User>) getAll();
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            users.add(user);
            oos.writeObject(users);
            oos.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Collection<User> getAll() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            Object users = ois.readObject();
            ois.close();
            return (ArrayList<User>) users;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
