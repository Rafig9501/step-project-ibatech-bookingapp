package dao;

import pojo.User;

import java.io.*;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class UserDAO implements DAO<User> {

    File file = new File("C:\\Users\\User\\Desktop\\IdeaProjects\\StepProjectFlights\\files\\users.bin");

    @Override
    public Optional<User> get(String username) throws IOException, ClassNotFoundException {

        if (Optional.ofNullable(this.file).isPresent()) {
                FileInputStream fis = new FileInputStream(this.file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            ArrayList<User> users = (ArrayList<User>) ois.readObject();
            fis.close();
            ois.close();
            return users.stream().filter(user -> user.getUserName().equals(username)).findFirst();
        }
        else return Optional.empty();
    }

    @Override
    public void save(User user) throws IOException, ClassNotFoundException {

        if (!file.exists()) {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            List<User> userList = new ArrayList<>();
            userList.add(user);
            oos.writeObject(userList);
            fos.close();
            oos.close();
        } else if (file.exists()) {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Object o = ois.readObject();
            ArrayList<User> data = (ArrayList<User>) o;
            boolean exist = data.stream().anyMatch(us -> us.getUserName().equals(user.getUserName()));

            if (!exist) {
                FileOutputStream fos = new FileOutputStream(file);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                data.add(user);
                oos.writeObject(data);
                fos.close();
                oos.close();
            }
            fis.close();
            ois.close();
        }
    }

    @Override
    public boolean delete(String userName) throws IOException, ClassNotFoundException {
        // this function didn't implemented
        return false;
    }

    @Override
    public Collection<User> getAll() throws IOException, ClassNotFoundException {

        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Object users = ois.readObject();
        return (ArrayList<User>) users;
    }
}