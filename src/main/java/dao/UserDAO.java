package dao;

import entity.User;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserDAO implements DAO<User> {

    public final File file = new File("C:\\Users\\User\\Desktop\\IdeaProjects\\StepProjectFlights\\files\\users.bin");

    public UserDAO() throws IOException {
        if (!file.exists()) {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(new ArrayList<User>());
        }
    }

    @Override
    public Optional<User> get(String username) {
        FileInputStream fis;
        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return Optional.empty();
        }
        ObjectInputStream ois;
        try {
            ois = new ObjectInputStream(fis);
        } catch (IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }
        ArrayList<User> users;
        try {
            users = (ArrayList<User>) ois.readObject();
            fis.close();
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return Optional.empty();
        }
        return users.stream().filter(user -> user.getUserName().equals(username)).findFirst();
    }

    @Override
    public boolean create(User user) {
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            return false;
        }
        ObjectOutputStream oos;
        try {
            oos = new ObjectOutputStream(fos);
        } catch (IOException e) {
            return false;
        }
        List<User> users = new ArrayList<>();
        users.add(user);
        try {
            oos.writeObject(users);
            fos.close();
            oos.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public boolean delete(String userName) {
        List<User> updatedUserList = getAll().stream().filter(user ->
                !user.getUserName().equals(userName)).collect(Collectors.toList());
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            return false;
        }
        ObjectOutputStream oos;
        try {
            oos = new ObjectOutputStream(fos);
        } catch (IOException e) {
            return false;
        }
        try {
            oos.writeObject(updatedUserList);
            fos.close();
            oos.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public boolean update(User user) {
        ArrayList<User> users = (ArrayList<User>) getAll();
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            return false;
        }
        ObjectOutputStream oos;
        try {
            oos = new ObjectOutputStream(fos);
        } catch (IOException e) {
            return false;
        }
        users.add(user);
        try {
            oos.writeObject(users);
            fos.close();
            oos.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public Collection<User> getAll() {
        FileInputStream fis;
        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        }
        ObjectInputStream ois;
        try {
            ois = new ObjectInputStream(fis);
        } catch (IOException e) {
            return new ArrayList<>();
        }
        Object users;
        try {
            users = ois.readObject();
            fis.close();
            ois.close();
            return (ArrayList<User>) users;
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }
}
