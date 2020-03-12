package dao;

import pojo.Booking;
import pojo.Flight;
import pojo.User;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BookingDAO implements DAO<Booking> {

    final File file = new File("C:\\Users\\User\\Desktop\\IdeaProjects\\StepProjectFlights\\files\\bookings.bin");
    boolean seat = false;

    @Override
    public Optional<Booking> get(String id) {
        return Optional.empty();
    }

    @Override
    public void save(Booking booking) throws IOException, ClassNotFoundException {

        if (!file.exists()) {

            FileInputStream fis = new FileInputStream(FlightDAO.file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            ArrayList<Flight> flights = (ArrayList<Flight>) ois.readObject();
            List<Flight> flightList = flights.stream().map(flight -> {
                if (flight.getSeats() >= 0) {
                    flight.setSeats(flight.getSeats() - 1);
                    seat = flight.getSeats() >= 0;
                    return flight;
                } else {
                    seat = flight.getSeats() >= 0;
                    return flight;
                }
            }).collect(Collectors.toList());
            fis.close();
            ois.close();
            FileOutputStream fos = new FileOutputStream(FlightDAO.file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(flightList);

        } else if (file.exists()) {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Object readed = ois.readObject();
            ArrayList<Booking> bookings = (ArrayList<Booking>) readed;
            boolean exist = bookings.stream().anyMatch(b -> (b.getName().equals(booking.getName())
                    && b.getSurname().equals(booking.getSurname())));
            if (!exist) {
                FileOutputStream fos = new FileOutputStream(file);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                bookings.add(booking);
                oos.writeObject(booking);
                fos.close();
                oos.close();
            }
            fis.close();
            ois.close();
        }
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
