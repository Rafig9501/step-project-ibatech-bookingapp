package dao;

import entity.User;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
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
    public Optional<User> get(String username) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        ArrayList<User> users = (ArrayList<User>) ois.readObject();
        fis.close();
        ois.close();
        return users.stream().filter(user -> user.getUserName().equals(username)).findFirst();
    }

    @Override
    public boolean create(User user) throws IOException {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            Logger logger = Logger.getLogger("Flag");
            return false;
        }
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(fos);
        } catch (IOException e) {
            Logger logger = Logger.getLogger("Flag");
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
            Logger logger = Logger.getLogger("Flag");
            return false;
        }
    }

    @Override
    public boolean delete(String userName) {
        List<User> updatedUserList = null;
        try {
            updatedUserList = getAll().stream().filter(user ->
                    !user.getUserName().equals(userName)).collect(Collectors.toList());
        } catch (IOException | ClassNotFoundException e) {
            Logger logger = Logger.getLogger("Flag");
            return false;
        }
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            Logger logger = Logger.getLogger("Flag");
            return false;
        }
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(fos);
        } catch (IOException e) {
            Logger logger = Logger.getLogger("Flag");
            return false;
        }
        try {
            oos.writeObject(updatedUserList);
            fos.close();
            oos.close();
            return true;
        } catch (IOException e) {
            Logger logger = Logger.getLogger("Flag");
            return false;
        }
    }

    @Override
    public boolean update(User user) {
        ArrayList<User> users = null;
        try {
            users = (ArrayList<User>) getAll();
        } catch (IOException | ClassNotFoundException e) {
            return false;
        }
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            return false;
        }
        ObjectOutputStream oos = null;
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
    public Collection<User> getAll() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Object users = ois.readObject();
        ArrayList<User> userArrayList = (ArrayList<User>) users;
        fis.close();
        ois.close();
        return userArrayList;
    }
}
