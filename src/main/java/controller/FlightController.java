package controller;

import entity.Flight;
import service.FlightService;
import ui.console.ConsoleOUT;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.time.temporal.ChronoUnit.SECONDS;


public class FlightController {

    FlightService service;

    public FlightController() {
        service = new FlightService();
    }

    public Optional<Flight> getFlight(String flightID) {
        return service.get(flightID);
    }

    public List<Flight> getAllFlights() {
        ArrayList<Flight> flights = service.getAll();
        flights.forEach(flight -> ConsoleOUT.print(flight.represent()));
        return flights;
    }

    public void getAllFlightsWithin24hours() {
        service.getAll().stream().filter(this::isWithin24hour)
                .forEach(flight -> ConsoleOUT.print(flight.represent()));
    }

    public boolean isWithin24hour(Flight flight) {
        LocalDateTime flightDateTime = Instant.ofEpochMilli(flight.getDateTimeInUnix()).atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime localDateNow = LocalDateTime.now();
        long between = SECONDS.between(localDateNow, flightDateTime);
        return between <= 86400L;
    }
}