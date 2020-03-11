package dao;

import pojo.Flight;
import pojo.User;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FlightDAO implements DAO<Flight> {

    File file = new File("C:\\Users\\User\\Desktop\\IdeaProjects\\StepProjectFlights\\files\\flights.bin");

    public FlightDAO() throws IOException {

        if (!file.exists()) {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            List<Flight> flights = new ArrayList<>();
            flights.add(new Flight("Baku","London",2020,7,15,21,30));
            flights.add(new Flight("Baku","Moscow",2020,3,29,2,10));
            flights.add(new Flight("Baku","Kiev",2020,4,3,11,15));
            flights.add(new Flight("Baku","Tehran",2020,5,1,15,0));
            flights.add(new Flight("Baku","Saint Petersburg",2020,5,12,12,40));
            flights.add(new Flight("Baku","Astana",2020,3,17,9,20));
            flights.add(new Flight("Baku","Batumi",2020,3,31,12,45));
            flights.add(new Flight("Baku","Istanbul",2020,4,13,5,35));
            flights.add(new Flight("Baku","Dubai",2020,4,18,15,0));
            oos.writeObject(flights);
            fos.close();
            oos.close();
        }
    }

    @Override
    public Optional<Flight> get(String id) throws IOException, ClassNotFoundException {
        if (Optional.ofNullable(this.file).isPresent()) {
            FileInputStream fis = new FileInputStream(this.file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            ArrayList<Flight> flights = (ArrayList<Flight>) ois.readObject();
            fis.close();
            ois.close();
            return flights.stream().filter(f -> f.getFlightID().equals(id)).findFirst();
        }
        else return Optional.empty();
    }

    @Override
    public void save(Flight flight) throws IOException, ClassNotFoundException {
        if (!file.exists()) {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            List<Flight> flights = new ArrayList<>();
            flights.add(flight);
            oos.writeObject(flights);
            fos.close();
            oos.close();
        } else if (file.exists()) {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Object o = ois.readObject();
            ArrayList<Flight> data = (ArrayList<Flight>) o;
            boolean exist = data.stream().anyMatch(f -> f.getFlightID().equals(flight.getFlightID()));

            if (!exist) {
                FileOutputStream fos = new FileOutputStream(file);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                data.add(flight);
                oos.writeObject(data);
                fos.close();
                oos.close();
            }
            fis.close();
            ois.close();
        }
    }

    @Override
    public boolean delete(String id) throws IOException, ClassNotFoundException {
        if (file.exists()){
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Object object = ois.readObject();
            ArrayList<Flight> flights = (ArrayList<Flight>) object;
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            List<Flight> flightList = flights.stream().filter(f -> !(f.getFlightID().equals(id))).collect(Collectors.toList());
            oos.writeObject(flightList);
            fis.close();
            ois.close();
            fos.close();
            oos.close();
            return flights.stream().anyMatch(f -> f.getFlightID().equals(id));
        }
        return false;
    }

    @Override
    public Collection<Flight> getAll() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Object flights = ois.readObject();
        return (ArrayList<Flight>) flights;
    }
}