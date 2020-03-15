package dao;

import entity.Flight;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

public class FlightDAO implements DAO<Flight> {

    public final File file = new File("C:\\Users\\User\\Desktop\\IdeaProjects\\StepProjectFlights\\files\\flights.bin");

    @Override
    public Optional<Flight> get(String id) throws IOException, ClassNotFoundException {
            ArrayList<Flight> flights = (ArrayList<Flight>) getAll();
            return flights.stream().filter(f -> f.getFlightID().equals(id)).findFirst();
    }

    @Override
    public void create(Flight flight) throws IOException {
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        List<Flight> flights = new ArrayList<>();
        flights.add(flight);
        oos.writeObject(flights);
        fos.close();
        oos.close();
    }

    @Override
    public void delete(String id) throws IOException, ClassNotFoundException {
        ArrayList<Flight> flights = (ArrayList<Flight>) getAll();
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        List<Flight> updatedFlights = flights.stream().filter(f ->
                !(f.getFlightID().equals(id))).collect(Collectors.toList());
        oos.writeObject(updatedFlights);
        fos.close();
        oos.close();
    }

    @Override
    public void update(Flight flight) throws IOException, ClassNotFoundException {
        ArrayList<Flight> flights = (ArrayList<Flight>) getAll();
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        flights.add(flight);
        oos.writeObject(flights);
        fos.close();
        oos.close();
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