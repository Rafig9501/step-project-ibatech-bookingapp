package pojo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static utilities.RandomGen.genFlightID;

public class Flight implements Serializable {

    String flightID;
    String from;
    String to;
    String dateTime;

    public Flight(String from, String to, int yyyy, int MM, int dd, int hh, int mm) {
        flightID = genFlightID();
        this.from = from;
        this.to = to;
        this.dateTime = LocalDateTime.of(yyyy,MM,dd,hh,mm)
                .format(DateTimeFormatter.ofPattern("uuuu-MM-dd-HH:mm"));;
    }

    public String getFlightID() {
        return flightID;
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

    @Override
    public String toString() {
        return "Flight{" +
                "flightID='" + flightID + '\'' +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", dateTime='" + dateTime + '\'' +
                '}';
    }
}
