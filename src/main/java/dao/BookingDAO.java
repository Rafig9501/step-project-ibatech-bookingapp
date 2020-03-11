package dao;

import pojo.Booking;

import java.io.File;
import java.util.Collection;
import java.util.Optional;

public class BookingDAO implements DAO<Booking> {

    File file = new File("C:\\Users\\User\\Desktop\\IdeaProjects\\StepProjectFlights\\files\\bookings.bin");

    public BookingDAO() {

    }

    @Override
    public Optional<Booking> get(String id) {
        return Optional.empty();
    }

    @Override
    public void save(Booking booking) {

    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public Collection<Booking> getAll() {
        return null;
    }
}
