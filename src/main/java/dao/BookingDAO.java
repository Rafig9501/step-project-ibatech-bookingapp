package dao;

import entity.Booking;
import entity.Flight;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
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
    public void create(Booking booking) throws IOException {
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        List<Booking> bookings = new ArrayList<>();
        bookings.add(booking);
        oos.writeObject(bookings);
        fos.close();
        oos.close();
    }

    @Override
    public void delete(String passengerID) throws IOException, ClassNotFoundException {
        ArrayList<Booking> bookings = (ArrayList<Booking>) getAll();
        List<Booking> updatedBookings = bookings.stream().filter(booking ->
                !booking.getPassenger().getId().equals(passengerID)).collect(Collectors.toList());
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(updatedBookings);
        fos.close();
        oos.close();
    }

    @Override
    public void update(Booking booking) throws Exception {
        ArrayList<Booking> bookings = (ArrayList<Booking>) getAll();
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        bookings.add(booking);
        oos.writeObject(bookings);
        fos.close();
        oos.close();
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