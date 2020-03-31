package utilities;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Credentials {

    public static Map<String, String> loggedIn = new HashMap<>();
    public static String bookingID;

    public static void addingUser(String userName, String password) {
        loggedIn.put(userName, password);
    }

    public static void deletingUser(String userName, String password) {
        loggedIn.remove(userName, password);
    }

    public static String getUserName() {
        return loggedIn.keySet().stream().map(String::toString).collect(Collectors.joining());
    }

    public static void setBookingID(String bookingID_){
        bookingID = bookingID_;
    }
}
