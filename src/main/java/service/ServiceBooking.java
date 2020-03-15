package service;

import dao.BookingDAO;
import entity.Booking;
import entity.Flight;
import utilities.Credentials;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class ServiceBooking {

    BookingDAO bookingDAO;
    ServiceFlight serviceFlight;

    public ServiceBooking() throws IOException, ClassNotFoundException {
        bookingDAO = new BookingDAO();
        serviceFlight = new ServiceFlight();
    }

    public Optional<Booking> get(String passengerID) throws IOException, ClassNotFoundException {
        return bookingDAO.get(passengerID);
    }

    public boolean save(Booking booking) throws Exception {

        serviceFlight.getAll().stream().filter(f ->
                f.getFlightID().equals(booking.getFlightID())).findFirst().map(flight -> {
                   if (flight.getSeats() > 0){
                       flight.setSeats(flight.getSeats() - 1);
                       try {
                           serviceFlight.delete(flight.getFlightID());
                           serviceFlight.save(flight);
                           delete(booking.getPassenger().getId());
                           booking.setFlight(flight);
                           bookingDAO.update(booking);
                           return true;
                       } catch (Exception e) {
                           e.printStackTrace();
                           return false;
                       }
                   }
                   return false;
                });
        return false;
    }

    public ArrayList<Booking> getAll() throws IOException, ClassNotFoundException {
        return  (ArrayList<Booking>) bookingDAO.getAll().stream().filter(booking ->
                booking.getUser().getUserName().equals(Credentials.getUserName()))
                .collect(Collectors.toList());
    }

    public boolean delete(String passengerID) throws IOException, ClassNotFoundException {
        bookingDAO.delete(passengerID);
        return false;
    }
}
// Flight flight = serviceFlight.getAll().stream().filter(f ->
//                f.getFlightID().equals(booking.getFlightID())).findFirst().get();
//
//        if (flight.getSeats() > 0) {
//            flight.setSeats(flight.getSeats() - 1);
//            serviceFlight.delete(flight.getFlightID());
//            serviceFlight.save(flight);
//            delete(booking.getPassenger().getId());
//            booking.setFlight(flight);
//            bookingDAO.update(booking);
//            return true;
//        }
//        return false;