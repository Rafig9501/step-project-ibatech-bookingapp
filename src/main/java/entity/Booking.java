package entity;

import utilities.RandomGen;

import java.io.Serializable;

import static utilities.RandomGen.*;

public class Booking implements Serializable {

    User user;
    Passenger passenger;
    String flightID;
    Flight flight;
    String bookingID;

    public Booking(User user, Passenger passenger, String flightID) {
        this.user = user;
        this.passenger = passenger;
        this.flightID = flightID;
        this.bookingID = genBookingID();
    }

    public User getUser() {
        return user;
    }

    public String getBookingID() {
        return bookingID;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public String getFlightID() {
        return flightID;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public void setFlightID(String flightID) {
        this.flightID = flightID;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public String represent() {
        return String.format("%s %s %s", passenger.represent(), flight.represent()," booking ID is " + bookingID);
    }
    
}
