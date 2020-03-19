package dao;


import entity.Flight;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FlightDAO implements DAO<Flight> {

    public final File file = new File("C:\\Users\\User\\Desktop\\IdeaProjects\\StepProjectFlights\\files\\flights.bin");

    @Override
    public Optional<Flight> get(String id) {
        ArrayList<Flight> flights = (ArrayList<Flight>) getAll();
        return flights.stream().filter(f -> f.getFlightID().equals(id)).findFirst();
    }

    @Override
    public boolean create(Flight flight) {
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            return false;
        }
        ObjectOutputStream oos;
        try {
            oos = new ObjectOutputStream(fos);
        } catch (IOException e) {
            return false;
        }
        List<Flight> flights = new ArrayList<>();
        flights.add(flight);
        try {
            oos.writeObject(flights);
            fos.close();
            oos.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public boolean delete(String id) {
        ArrayList<Flight> flights = (ArrayList<Flight>) getAll();
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            return false;
        }
        ObjectOutputStream oos;
        try {
            oos = new ObjectOutputStream(fos);
        } catch (IOException e) {
            return false;
        }
        List<Flight> updatedFlights = flights.stream().filter(f ->
                !(f.getFlightID().equals(id))).collect(Collectors.toList());
        try {
            oos.writeObject(updatedFlights);
            fos.close();
            oos.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public boolean update(Flight flight) {
        ArrayList<Flight> flights = (ArrayList<Flight>) getAll();
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            return false;
        }
        ObjectOutputStream oos;
        try {
            oos = new ObjectOutputStream(fos);
        } catch (IOException e) {
            return false;
        }
        flights.add(flight);
        try {
            oos.writeObject(flights);
            fos.close();
            oos.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public Collection<Flight> getAll() {
        FileInputStream fis;
        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        }
        ObjectInputStream ois;
        try {
            ois = new ObjectInputStream(fis);
        } catch (IOException e) {
            return new ArrayList<>();
        }
        Object readObject;
        try {
            readObject = ois.readObject();
            fis.close();
            ois.close();
            return (ArrayList<Flight>) readObject;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}