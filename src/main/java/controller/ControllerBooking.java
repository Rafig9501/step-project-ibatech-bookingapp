package controller;

import entity.Booking;
import entity.Flight;
import entity.Passenger;
import service.ServiceBooking;
import service.ServiceFlight;
import service.ServiceUser;
import ui.console.ConsoleOUT;
import utilities.Credentials;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class ControllerBooking {

    ServiceFlight serviceFlight;
    ServiceUser serviceUser;
    ServiceBooking serviceBooking;

    public ControllerBooking() throws IOException, ClassNotFoundException {
        serviceFlight = new ServiceFlight();
        serviceUser = new ServiceUser();
        serviceBooking = new ServiceBooking();
    }

    public boolean saveBooking(String name, String surname, String flightID) throws Exception {
        boolean saved = false;
        if (serviceUser.get(Credentials.getUserName()).isPresent()) {
            Booking booking = new Booking(serviceUser.get(Credentials.getUserName()).get(),
                    new Passenger(name, surname), flightID);
            saved = serviceBooking.save(booking);
        }
        return saved;
    }

    public boolean getAllBookings() throws IOException, ClassNotFoundException {
        ArrayList<Booking> bookings = serviceBooking.getAll();
        if (!bookings.isEmpty()){
        bookings.forEach(booking -> ConsoleOUT.print(booking.represent()));
        return true;
        }
        return false;
    }

    public boolean deleteBooking(String passengerName, String passengerSurname, String flightID) throws IOException, ClassNotFoundException {
        Booking booking;
        ArrayList<Booking> bookings = serviceBooking.getAll();
        boolean bookingExist = bookings.stream().anyMatch(b -> b.getPassenger().getName().equals(passengerName)
                && b.getPassenger().getSurname().equals(passengerSurname)
                && b.getFlight().getFlightID().equals(flightID));

        if (bookingExist) {
            booking = bookings.stream().filter(b -> b.getPassenger().getName().equals(passengerName)
                    && b.getPassenger().getSurname().equals(passengerSurname)
             && b.getFlight().getFlightID().equals(flightID)).findFirst()
                    .orElseThrow(() -> new RuntimeException("not found"));
            return serviceBooking.delete(booking.getPassenger().getId());
        } else return false;
    }

    public Optional<Booking> getBooking(String passengerName, String passengerSurname) throws IOException, ClassNotFoundException {
        ArrayList<Booking> bookings = serviceBooking.getAll();
        Booking booking = bookings.stream().filter(b -> b.getPassenger().getName().equals(passengerName)
                && b.getPassenger().getSurname().equals(passengerSurname)).findFirst()
                .orElseThrow(() -> new RuntimeException("not found"));
        return serviceBooking.get(booking.getPassenger().getId());
    }
}

// public Booking saveBooking(String name, String surname, String flightID) throws Exception {
//        Booking booking = null;
//        if (serviceUser.get(Credentials.getUserName()).isPresent()) {
//            booking = new Booking(serviceUser.get(Credentials.getUserName()).get(),
//                    new Passenger(name, surname), flightID);
//        }
//        boolean saved = serviceBooking.save(booking);
//        if (saved) {
//            return booking;
//        } else {
//            return null;
//        }
//    }