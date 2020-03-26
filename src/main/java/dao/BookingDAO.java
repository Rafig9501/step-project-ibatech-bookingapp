package dao;

import entity.Booking;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BookingDAO implements DAO<Booking> {

    public final File file = new File("bookings.bin");

    public BookingDAO() throws IOException {
        if (!file.exists()) {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(new ArrayList<Booking>());
        }
    }

    @Override
    public Optional<Booking> get(String bookingID) {
        ArrayList<Booking> bookings = (ArrayList<Booking>) getAll();
        return bookings.stream().filter(booking
                -> booking.getBookingID().equals(bookingID)).findFirst();
    }

    @Override
    public boolean create(Booking booking) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            List<Booking> bookings = new ArrayList<>();
            bookings.add(booking);
            oos.writeObject(bookings);
            oos.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean delete(String bookingID) {
        try {
            List<Booking> updatedBookings = getAll().stream().filter(booking ->
                    !booking.getBookingID().equals(bookingID)).collect(Collectors.toList());
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(updatedBookings);
            oos.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean update(Booking booking) {
        try {
            ArrayList<Booking> bookings = (ArrayList<Booking>) getAll();
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            bookings.add(booking);
            oos.writeObject(bookings);
            oos.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Collection<Booking> getAll() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            Object readed = ois.readObject();
            ois.close();
            return (ArrayList<Booking>) readed;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}