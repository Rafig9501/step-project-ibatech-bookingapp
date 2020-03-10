package service;

import dao.DAO;
import pojo.User;

import java.io.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class ServiceUser implements DAO<User> {

    public ServiceUser() throws IOException {

    }

    @Override
    public Optional<User> get(int id) {
        return Optional.empty();
    }

    @Override
    public void save(User user) throws IOException {


    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Collection<User> getAll() {
        return null;
    }
}
