package entity;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static utilities.RandomGen.genFlightID;

public class Flight implements Serializable {

    String flightID;
    String from;
    String to;
    String dateTime;
    String month;
    String day;
    int seats;
    long dateTimeInUnix;

    public Flight(String from, String to, int seats, int yyyy, int MM, int dd, int hh, int mm) {
        flightID = genFlightID();
        this.from = from;
        this.to = to;
        this.seats = seats;
        this.month = String.valueOf(MM);
        this.day = String.valueOf(dd);
        this.dateTime = LocalDateTime.of(yyyy, MM, dd, hh, mm)
                .format(DateTimeFormatter.ofPattern("uuuu-MM-dd-HH:mm"));
        this.dateTimeInUnix = setDateTimeInUnix(yyyy, MM, dd, hh, mm);
    }

    public long setDateTimeInUnix(int yyyy, int MM, int dd, int hh, int mm) {
        this.dateTime = LocalDateTime.of(yyyy, MM, dd, hh, mm)
                .format(DateTimeFormatter.ofPattern("uuuu-MM-dd-HH:mm"));
        String sb = yyyy + "-" + MM + "-" + dd + " " + hh + ":" + mm + ":" + 0;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date;
        long dateTimeWithLong = 0;
        try {
            date = dateFormat.parse(sb);
            dateTimeWithLong = date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateTimeWithLong;
    }

    public long getDateTimeInUnix() {
        return dateTimeInUnix;
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

    public String getMonth() {
        return month;
    }

    public String getDay() {
        return day;
    }

    public String represent() {
        return String.format("%s - from %s to %s on %s, available seats - %d", flightID, from, to, dateTime, seats);
    }

}
