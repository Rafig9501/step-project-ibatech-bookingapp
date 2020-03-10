package dao;

import pojo.Booking;

import java.util.Collection;
import java.util.Optional;

public class BookingDAO implements DAO<Booking> {

    @Override
    public Optional<Booking> get(String id) {
        return Optional.empty();
    }

    @Override
    public void save(Booking booking) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Collection<Booking> getAll() {
        return null;
    }
}
