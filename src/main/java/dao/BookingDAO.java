package dao;

import entity.Booking;
import entity.Flight;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class BookingDAO implements DAO<Booking> {

    public final File file = new File("C:\\Users\\User\\Desktop\\IdeaProjects\\StepProjectFlights\\files\\bookings.bin");

    public BookingDAO() throws IOException {
        if (!file.exists()) {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(new ArrayList<Booking>());
        }
    }

    @Override
    public Optional<Booking> get(String passengerID) throws IOException, ClassNotFoundException {
        ArrayList<Booking> bookings = (ArrayList<Booking>) getAll();
        return bookings.stream().filter(booking
                -> booking.getPassenger().getId().equals(passengerID)).findFirst();
    }

    @Override
    public boolean create(Booking booking) {
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
        List<Booking> bookings = new ArrayList<>();
        bookings.add(booking);
        try {
            oos.writeObject(bookings);
            fos.close();
            oos.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public boolean delete(String passengerID) {
        ArrayList<Booking> bookings = null;
        try {
            bookings = (ArrayList<Booking>) getAll();
        } catch (IOException | ClassNotFoundException e) {
            return false;
        }
        List<Booking> updatedBookings = bookings.stream().filter(booking ->
                !booking.getPassenger().getId().equals(passengerID)).collect(Collectors.toList());
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
        try {
            oos.writeObject(updatedBookings);
            fos.close();
            oos.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public boolean update(Booking booking) {
        ArrayList<Booking> bookings = null;
        try {
            bookings = (ArrayList<Booking>) getAll();
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
        bookings.add(booking);
        try {
            oos.writeObject(bookings);
            fos.close();
            oos.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public Collection<Booking> getAll() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Object readed = ois.readObject();
        fis.close();
        ois.close();
        return (ArrayList<Booking>) readed;
    }
}