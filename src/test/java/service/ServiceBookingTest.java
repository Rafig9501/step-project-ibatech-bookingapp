package service;

import entity.Booking;
import entity.Flight;
import entity.Passenger;
import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utilities.Credentials;

import javax.xml.ws.Service;
import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

class ServiceBookingTest {

    ServiceBooking serviceBooking;
    Booking booking;
    User user;

    @BeforeEach
    void creatingInstances() throws IOException {
        serviceBooking = new ServiceBooking();
        user = new User("rafiq","rafiq");
        Flight flight = new Flight("Istanbul","Dubai",23,2020,3,22,10,15);
        flight.setFlightID("32 FDS");
        ServiceFlight serviceFlight = new ServiceFlight();
        serviceFlight.save(flight);
        Passenger passenger = new Passenger("Rafig","Mammadzada");
        passenger.setId("32d");
        booking = new Booking(user,passenger,"32 FDS");
    }

    @Test
    void get() {
        save();
        assertTrue(serviceBooking.get("32d").isPresent());
    }

    @Test
    void save() {
        assertTrue(serviceBooking.save(booking));
    }

    @Test
    void getAll() {
        save();
        Credentials.addingUser(user.getUserName(),user.getPassword());
        assertFalse(serviceBooking.getAll().isEmpty());
    }

    @Test
    void delete() {
        assertTrue(serviceBooking.delete("32d"));
    }
}