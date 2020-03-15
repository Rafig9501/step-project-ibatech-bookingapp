package dao;

import entity.User;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
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
    public void create(User user) throws IOException {
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        List<User> userList = new ArrayList<>();
        userList.add(user);
        oos.writeObject(userList);
        fos.close();
        oos.close();
    }

    @Override
    public void delete(String userName) throws IOException, ClassNotFoundException {
        List<User> updatedUserList = getAll().stream().filter(user ->
                !user.getUserName().equals(userName)).collect(Collectors.toList());
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(updatedUserList);
        fos.close();
        oos.close();
    }

    @Override
    public void update(User user) throws IOException, ClassNotFoundException {
        ArrayList<User> users = (ArrayList<User>) getAll();
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        users.add(user);
        oos.writeObject(users);
        fos.close();
        oos.close();
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
