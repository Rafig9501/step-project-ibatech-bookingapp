package utilities;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.List;

public class RandomGen {

    static List<String> flightID = new ArrayList<>();
    static List<String> passengerID = new ArrayList<>();
    static List<String> bookingID = new ArrayList<>();

    public static String genFlightID() {

        String alphabetic = RandomStringUtils.randomAlphabetic(3);
        String numeric = RandomStringUtils.randomNumeric(2);
        String random = numeric + " " + alphabetic.toUpperCase();
        if (!(flightID.contains(random))) {
            flightID.add(random);
            return random;
        } else return genFlightID();
    }

    public static String genPassengerID() {
        String numeric = RandomStringUtils.randomNumeric(2);
        if (!(passengerID.contains(numeric))) {
            passengerID.add(numeric);
            return numeric;
        } else return genPassengerID();
    }

    public static String genBookingID(){
        String numeric = RandomStringUtils.randomNumeric(3);
        if (!(bookingID.contains(numeric))) {
            bookingID.add(numeric);
            return numeric;
        } else return genPassengerID();
    }
}
