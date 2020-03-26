package service;

import dao.BookingDAO;
import entity.Booking;
import entity.Flight;
import utilities.Credentials;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

public class BookingService {

    BookingDAO bookingDAO;
    FlightService flightService;

    public BookingService() throws IOException {
        bookingDAO = new BookingDAO();
        flightService = new FlightService();
    }

    public Optional<Booking> get(String bookingID) {
        return bookingDAO.get(bookingID);
    }

    public boolean save(Booking booking) {
        Flight flight = null;
        if (flightService.getAll().stream().anyMatch(f ->
                f.getFlightID().equals(booking.getFlightID()))) {
            flight = flightService.getAll().stream().filter(f ->
                    f.getFlightID().equals(booking.getFlightID())).findFirst().get();
        }

        if (flight != null && flight.getSeats() > 0) {
            flight.setSeats(flight.getSeats() - 1);
            flightService.delete(flight.getFlightID());
            flightService.save(flight);
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

    public boolean delete(String bookingID) {
        if (bookingDAO.file.exists()) {
            return bookingDAO.delete(bookingID);
        }
        return false;
    }
}