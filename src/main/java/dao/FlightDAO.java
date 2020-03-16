package dao;


import entity.Flight;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class FlightDAO implements DAO<Flight> {

    public final File file = new File("C:\\Users\\User\\Desktop\\IdeaProjects\\StepProjectFlights\\files\\flights.bin");

    @Override
    public Optional<Flight> get(String id) throws IOException, ClassNotFoundException {
            ArrayList<Flight> flights = (ArrayList<Flight>) getAll();
            return flights.stream().filter(f -> f.getFlightID().equals(id)).findFirst();
    }

    @Override
    public boolean create(Flight flight) throws IOException {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            Logger logger = Logger.getLogger("Flag");
            return false;
        }
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(fos);
        } catch (IOException e) {
            Logger logger = Logger.getLogger("Flag");
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
            Logger logger = Logger.getLogger("Flag");
            return false;
        }
    }

    @Override
    public boolean delete(String id) {
        ArrayList<Flight> flights = null;
        try {
            flights = (ArrayList<Flight>) getAll();
        } catch (IOException | ClassNotFoundException e) {
            Logger logger = Logger.getLogger("Flag");
            return false;
        }
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            Logger logger = Logger.getLogger("Flag");
            return false;
        }
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(fos);
        } catch (IOException e) {
            Logger logger = Logger.getLogger("Flag");
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
            Logger logger = Logger.getLogger("Flag");
            return false;
        }
    }

    @Override
    public boolean update(Flight flight) {
        ArrayList<Flight> flights = null;
        try {
            flights = (ArrayList<Flight>) getAll();
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
    public Collection<Flight> getAll() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Object readObject = ois.readObject();
        fis.close();
        ois.close();
        return (Collection<Flight>) readObject;
    }
}