package service;

import dao.BookingDAO;
import entity.Booking;
import entity.Flight;
import utilities.Credentials;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

public class ServiceBooking {

    BookingDAO bookingDAO;
    ServiceFlight serviceFlight;

    public ServiceBooking() throws IOException {
        bookingDAO = new BookingDAO();
        serviceFlight = new ServiceFlight();
    }

    public Optional<Booking> get(String passengerID) {
        return bookingDAO.get(passengerID);
    }

    public boolean save(Booking booking) {
        Flight flight = null;
        if (serviceFlight.getAll().stream().anyMatch(f ->
                f.getFlightID().equals(booking.getFlightID()))) {
            flight = serviceFlight.getAll().stream().filter(f ->
                    f.getFlightID().equals(booking.getFlightID())).findFirst().get();
        }

        if (flight != null && flight.getSeats() > 0) {
            flight.setSeats(flight.getSeats() - 1);
            serviceFlight.delete(flight.getFlightID());
            serviceFlight.save(flight);
            delete(booking.getPassenger().getId());
            booking.setFlight(flight);
            return bookingDAO.update(booking);
        }
        return false;
    }

    public ArrayList<Booking> getAll() {
        if (bookingDAO.file.exists()) {
            return (ArrayList<Booking>) bookingDAO.getAll().stream().filter(booking ->
                    booking.getUser().getUserName().equals(Credentials.getUserName()))
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    public boolean delete(String passengerID) {
        if (bookingDAO.file.exists()) {
            return bookingDAO.delete(passengerID);
        }
        return false;
    }
}