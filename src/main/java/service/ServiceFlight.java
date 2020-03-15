package service;

import dao.FlightDAO;
import entity.Booking;
import entity.Flight;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ServiceFlight {

    FlightDAO flightDAO;

    public ServiceFlight() throws IOException, ClassNotFoundException {
        flightDAO = new FlightDAO();
        if (!flightDAO.file.exists()) {
            flightDAO.create(new Flight("Baku", "London", 20, 2020, 7, 15, 21, 30));
            flightDAO.update(new Flight("Baku", "Moscow", 15, 2020, 3, 29, 2, 10));
            flightDAO.update(new Flight("Baku", "Kiev", 18, 2020, 4, 3, 11, 15));
            flightDAO.update(new Flight("Baku", "Tehran", 26, 2020, 5, 1, 15, 0));
            flightDAO.update(new Flight("Baku", "Saint Petersburg", 29, 2020, 5, 12, 12, 40));
            flightDAO.update(new Flight("Baku", "Astana", 11, 2020, 3, 17, 9, 20));
            flightDAO.update(new Flight("Baku", "Batumi", 7, 2020, 3, 31, 12, 45));
            flightDAO.update(new Flight("Baku", "Istanbul", 32, 2020, 4, 13, 5, 35));
            flightDAO.update(new Flight("Baku", "Dubai", 17, 2020, 4, 18, 15, 0));
        }
    }

    public Optional<Flight> get(String flightID) throws IOException, ClassNotFoundException {
            return flightDAO.get(flightID);
    }

    public boolean save(Flight flight) throws IOException, ClassNotFoundException {

            boolean flightExist = getAll().stream().anyMatch(f -> f.getFlightID().equals(flight.getFlightID()));
            if (!flightExist) {
                flightDAO.update(flight);
                return true;
            }
            return false;
        }

    public boolean delete(String id) throws IOException, ClassNotFoundException {
            boolean match = getAll().stream().anyMatch(flight ->
                    flight.getFlightID().equals(id));
            if (match) {
                flightDAO.delete(id);
                return true;
            }
        return false;
    }

    public ArrayList<Flight> getAll() throws IOException, ClassNotFoundException {
        return (ArrayList<Flight>) flightDAO.getAll();
    }
}
