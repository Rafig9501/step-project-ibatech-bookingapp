package dao;

import pojo.Flight;

import java.util.Collection;
import java.util.Optional;

public class FlightDAO implements DAO<Flight> {

    @Override
    public Optional<Flight> get(String id) {
        return Optional.empty();
    }

    @Override
    public void save(Flight flight) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Collection<Flight> getAll() {
        return null;
    }
}
