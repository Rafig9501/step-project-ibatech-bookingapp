package controller;

import entity.Flight;
import service.ServiceFlight;
import ui.console.ConsoleOUT;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class ControllerFlight {

    ServiceFlight service;

    public ControllerFlight() throws IOException, ClassNotFoundException {
        service = new ServiceFlight();
    }

    public Optional<Flight> getFlight(String flightID) throws IOException, ClassNotFoundException {
        return service.get(flightID);
    }

    public void getAllFlights() throws IOException, ClassNotFoundException {
        ArrayList<Flight> flights = service.getAll();
        flights.forEach(flight -> ConsoleOUT.print(flight.represent()));
    }
}
