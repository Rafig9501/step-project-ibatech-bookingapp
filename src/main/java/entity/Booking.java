package entity;

import java.io.Serializable;

public class Booking implements Serializable {

    User user;
    Passenger passenger;
    String flightID;
    Flight flight;

    public Booking(User user, Passenger passenger, String flightID) {
        this.user = user;
        this.passenger = passenger;
        this.flightID = flightID;
    }

    public User getUser() {
        return user;
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

    public String represent(){
        return String.format("%s %s",passenger.represent(),flight.represent());
    }

    @Override
    public String toString() {
        return "Booking{" +
                "user=" + user +
                ", passenger=" + passenger +
                ", flight=" + flight +
                '}';
    }
}
