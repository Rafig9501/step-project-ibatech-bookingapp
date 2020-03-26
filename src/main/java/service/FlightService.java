package service;

import dao.FlightDAO;
import entity.Flight;

import java.util.ArrayList;
import java.util.Optional;

public class FlightService {

    FlightDAO flightDAO;

    public FlightService() {
        flightDAO = new FlightDAO();
        if (!flightDAO.file.exists()) {
            flightDAO.create(new Flight("Baku", "London", 20, 2020, 3, 27, 21, 30));
            flightDAO.update(new Flight("Baku", "London", 20, 2020, 3, 28, 11, 30));
            flightDAO.update(new Flight("Baku", "Moscow", 15, 2020, 3, 29, 2, 10));
            flightDAO.update(new Flight("Baku", "Moscow", 15, 2020, 3, 29, 6, 10));
            flightDAO.update(new Flight("Baku", "Kiev", 18, 2020, 4, 3, 11, 15));
            flightDAO.update(new Flight("Baku", "Kiev", 18, 2020, 4, 3, 9, 15));
            flightDAO.update(new Flight("Baku", "Tehran", 26, 2020, 3, 28, 15, 0));
            flightDAO.update(new Flight("Baku", "Saint Petersburg", 29, 2020, 3, 28, 12, 40));
            flightDAO.update(new Flight("Baku", "Saint Petersburg", 29, 2020, 3, 29, 12, 40));
            flightDAO.update(new Flight("Baku", "Astana", 11, 2020, 3, 28, 9, 20));
            flightDAO.update(new Flight("Baku", "Astana", 11, 2020, 3, 28, 4, 20));
            flightDAO.update(new Flight("Baku", "Batumi", 7, 2020, 3, 31, 12, 45));
            flightDAO.update(new Flight("Baku", "Batumi", 7, 2020, 3, 31, 5, 45));
            flightDAO.update(new Flight("Baku", "Istanbul", 32, 2020, 3, 29, 5, 35));
            flightDAO.update(new Flight("Baku", "Istanbul", 32, 2020, 3, 29, 6, 35));
            flightDAO.update(new Flight("Baku", "Dubai", 17, 2020, 4, 1, 15, 0));
            flightDAO.update(new Flight("Baku", "Dubai", 17, 2020, 4, 1, 8, 0));
            flightDAO.update(new Flight("Baku", "New-York", 14, 2020, 3, 28, 21, 10));
            flightDAO.update(new Flight("Baku", "New-York", 14, 2020, 3, 28, 17, 10));
            flightDAO.update(new Flight("Baku", "Berlin", 18, 2020, 3, 29, 13, 0));
            flightDAO.update(new Flight("Baku", "Berlin", 18, 2020, 3, 29, 7, 0));
            flightDAO.update(new Flight("Baku", "Rome", 4, 2020, 3, 28, 0, 5));
            flightDAO.update(new Flight("Baku", "Rome", 4, 2020, 3, 28, 7, 5));
            flightDAO.update(new Flight("Baku", "Madrid", 8, 2020, 4, 2, 9, 40));
            flightDAO.update(new Flight("Baku", "Madrid", 8, 2020, 4, 2, 11, 40));
            flightDAO.update(new Flight("Baku", "Warsaw", 25, 2020, 3, 28, 12, 15));
            flightDAO.update(new Flight("Baku", "Warsaw", 25, 2020, 3, 28, 20, 15));
            flightDAO.update(new Flight("Baku", "Wroclaw", 15, 2020, 3, 28, 17, 0));
            flightDAO.update(new Flight("Baku", "Wroclaw", 15, 2020, 3, 28, 7, 0));
        }
    }

    public Optional<Flight> get(String flightID) {
        return flightDAO.get(flightID);
    }

    public void save(Flight flight) {
        if (getAll().stream().anyMatch(f ->
                f.getFlightID().equals(flight.getFlightID()))) {

            String flightID = getAll().stream().filter(f ->
                    f.getFlightID().equals(flight.getFlightID()))
                    .findFirst().get().getFlightID();
            delete(flightID);
            flightDAO.update(flight);
        } else {
            flightDAO.update(flight);
        }
    }

    public boolean delete(String id) {
        boolean match = getAll().stream().anyMatch(flight ->
                flight.getFlightID().equals(id));
        if (match) {
            return flightDAO.delete(id);
        }
        return false;
    }

    public ArrayList<Flight> getAll() {
        return (ArrayList<Flight>) flightDAO.getAll();
    }
}
