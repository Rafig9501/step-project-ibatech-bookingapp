package controller;

import entity.Booking;
import entity.Flight;
import entity.Passenger;
import service.BookingService;
import service.FlightService;
import service.UserService;
import ui.console.ConsoleOUT;
import utilities.Credentials;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class BookingController {

    FlightService flightService;
    UserService userService;
    BookingService bookingService;

    public BookingController() throws IOException {
        flightService = new FlightService();
        userService = new UserService();
        bookingService = new BookingService();
    }

    public boolean saveBooking(String name, String surname, String flightID) {
        boolean saved = false;
        if (userService.get(Credentials.getUserName()).isPresent()) {
            Booking booking = new Booking(userService.get(Credentials.getUserName()).get(),
                    new Passenger(name, surname), flightID);
            booking.setFlight(flightService.get(flightID).get());
            Credentials.setBookingID(booking.getBookingID());
            saved = bookingService.save(booking);
        }
        return saved;
    }

    public boolean getAllBookings() {
        ArrayList<Booking> bookings = bookingService.getAll();
        if (!bookings.isEmpty()) {
            bookings.forEach(booking -> ConsoleOUT.print(booking.represent()));
            return true;
        }
        return false;
    }

    public boolean deleteBooking(String bookingID) {
        if (getBooking(bookingID).isPresent()) {
            int seats = getBooking(bookingID).get().getFlight().getSeats();
            Flight flight = getBooking(bookingID).get().getFlight();
            flight.setSeats(seats+1);
            flightService.save(flight);
            return bookingService.delete(getBooking(bookingID).get().getBookingID());
        } else return false;
    }

    public Optional<Booking> getBooking(String bookingID) {
        ArrayList<Booking> bookings = bookingService.getAll();
        return bookings.stream().filter(b -> b.getBookingID().equals(bookingID)).findFirst();
    }

    public boolean flghtsInThisDirection(String destination, String month, String day) {
        List<Flight> flights = flightService.getAll().stream()
                .filter(f -> f.getTo().equals(destination)
                        && f.getMonth().equals(month)
                        && f.getDay().equals(day)).collect(Collectors.toList());
        if (!flights.isEmpty()) {
            flights.forEach(f -> ConsoleOUT.print(f.represent()));
            return true;
        } else return false;
    }
}