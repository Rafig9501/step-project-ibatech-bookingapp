package entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static utilities.RandomGen.genFlightID;

public class Flight implements Serializable {

    String flightID;
    String from;
    String to;
    String dateTime;
    int seats;

    public Flight(String from, String to, int seats, int yyyy, int MM, int dd, int hh, int mm) {
        flightID = genFlightID();
        this.from = from;
        this.to = to;
        this.seats = seats;
        this.dateTime = LocalDateTime.of(yyyy,MM,dd,hh,mm)
                .format(DateTimeFormatter.ofPattern("uuuu-MM-dd-HH:mm"));
    }

    public String getFlightID() {
        return flightID;
    }

    public int getSeats() {
        return seats;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setFlightID(String flightID) {
        this.flightID = flightID;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public String represent(){
        return String.format("%s - from %s to %s on %s, available seats - %d",flightID,from,to,dateTime,seats);
    }

    @Override
    public String toString() {
        return represent();
    }
}
