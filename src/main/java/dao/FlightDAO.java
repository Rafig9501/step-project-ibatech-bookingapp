package dao;


import entity.Flight;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FlightDAO implements DAO<Flight> {

    public final File file = new File("flights.bin");

    @Override
    public Optional<Flight> get(String id) {
        ArrayList<Flight> flights = (ArrayList<Flight>) getAll();
        return flights.stream().filter(f -> f.getFlightID().equals(id)).findFirst();
    }

    @Override
    public boolean create(Flight flight) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            List<Flight> flights = new ArrayList<>();
            flights.add(flight);
            oos.writeObject(flights);
            oos.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean delete(String id) {
        try {
            List<Flight> updatedFlights = getAll().stream().filter(f ->
                    !(f.getFlightID().equals(id))).collect(Collectors.toList());
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(updatedFlights);
            oos.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean update(Flight flight) {
        try {
            ArrayList<Flight> flights = (ArrayList<Flight>) getAll();
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            flights.add(flight);
            oos.writeObject(flights);
            oos.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Collection<Flight> getAll() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            Object readObject = ois.readObject();
            ois.close();
            return (Collection<Flight>) readObject;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}