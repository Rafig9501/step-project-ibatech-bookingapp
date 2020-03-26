package controller;

import entity.Flight;
import ui.console.ConsoleIN;
import ui.console.ConsoleOUT;
import utilities.Credentials;

import java.io.IOException;
import java.util.Optional;

import static utilities.Constants_.*;

public class Controller {

    UserController userController;
    BookingController bookingController;
    FlightController flightController;

    public Controller() throws IOException {
        userController = new UserController();
        flightController = new FlightController();
        bookingController = new BookingController();
    }

    public void mainMenu() throws Exception {
        ConsoleOUT.printingMenuLogIn();
        switch (ConsoleIN.menuLogIn()) {
            case SIGN_IN:
                signIn();
                break;
            case LOG_IN:
                ConsoleOUT.userName();
                String username = ConsoleIN.reader();
                ConsoleOUT.password();
                String password = ConsoleIN.reader();
                logIn(username, password);
                break;
            case DELETE_USER:
                deleteUser();
                break;
            case EXIT_IN_LOG_IN:
                break;
        }
    }

    void signIn() throws Exception {
        ConsoleOUT.userName();
        String username = ConsoleIN.reader();
        ConsoleOUT.password();
        String password = ConsoleIN.reader();
        boolean saveUser = userController.saveUser(username, password);
        if (saveUser) {
            ConsoleOUT.registered();
            Credentials.addingUser(username, password);
            mainMenu();
        } else {
            ConsoleOUT.notRegistered();
            mainMenu();
        }
    }

    void logIn(String username, String password) throws Exception {
        if (userController.service.getAll().stream().anyMatch(user -> user.getUserName().equals(username)
                && user.getPassword().equals(password))) {
            Credentials.addingUser(username, password);
            ConsoleOUT.printingMenuSignedIn();
            switch (ConsoleIN.menuSignIn()) {
                case SHOW_FLIGHTS_ALL_FLIGHTS:
                    flightController.getAllFlights();
                    logIn(username, password);
                    break;
                case SHOW_FLIGHTS_WITHIN_24_HOURS:
                    flightController.getAllFlightsWithin24hours();
                    logIn(username, password);
                    break;
                case SHOW_INFORMATION_ABOUT_FLIGHT_BY_ID:
                    ConsoleOUT.flightId();
                    String flightID = ConsoleIN.reader();
                    Optional<Flight> flight = flightController.getFlight(flightID);
                    if (flight.isPresent()) flight.ifPresent((f -> ConsoleOUT.print(f.represent())));
                    else ConsoleOUT.wrongFlightID();
                    logIn(username, password);
                    break;
                case MAKE_BOOKING:
                    makeBooking(username, password);
                    break;
                case SHOW_MY_BOOKINGS:
                    if (bookingController.getAllBookings()) {
                        logIn(username, password);
                        break;
                    } else {
                        ConsoleOUT.thereIsNoBooking();
                        logIn(username, password);
                    }
                    break;
                case CANCEL_BOOKING:
                    cancelBooking(username, password);
                    break;
                case LOG_OUT:
                    Credentials.deletingUser(username, password);
                    mainMenu();
                    break;
                case EXIT_IN_SIGNED_IN:
                    break;
            }
        } else {
            ConsoleOUT.failed();
            mainMenu();
        }
    }

    void makeBooking(String username, String password) throws Exception {
        ConsoleOUT.destination();
        String destination = ConsoleIN.reader();
        ConsoleOUT.month();
        String month = ConsoleIN.reader();
        ConsoleOUT.day();
        String day = ConsoleIN.reader();
        boolean exist = bookingController.flghtsInThisDirection(destination, month, day);
        int passengerAmount;
        if (!exist) {
            ConsoleOUT.wrongBookingDetails();
            logIn(username, password);
        } else {
            ConsoleOUT.passengerAmount();
            try {
                passengerAmount = Integer.parseInt(ConsoleIN.reader());
                makingBookingWithAmount(passengerAmount, username, password);
            } catch (Exception e) {
                ConsoleOUT.wrongAmount();
                logIn(username, password);
            }
        }
    }

    private void makingBookingWithAmount(int amount, String username, String password) throws Exception {
        for (int i = 0; i < amount; i++) {
            ConsoleOUT.passengerName();
            String name = ConsoleIN.reader();
            ConsoleOUT.passengeSurname();
            String surname = ConsoleIN.reader();
            ConsoleOUT.flightId();
            String flightID = ConsoleIN.reader();
            boolean savedBooking = bookingController.saveBooking(name, surname, flightID);
            if (savedBooking) {
                ConsoleOUT.bookingMade();
            } else {
                ConsoleOUT.bookingDidntMade();
                logIn(username, password);
            }
        }
        logIn(username, password);
    }

    void deleteUser() throws Exception {
        ConsoleOUT.userName();
        String username = ConsoleIN.reader();
        ConsoleOUT.password();
        String password = ConsoleIN.reader();
        if (userController.deleteUser(username, password)) {
            deleteSuccess(username, password);
        } else {
            deleteFailed();
        }
    }

    void deleteFailed() throws Exception {
        ConsoleOUT.failed();
        mainMenu();
    }

    void deleteSuccess(String username, String password) throws Exception {
        ConsoleOUT.userDeleted();
        Credentials.deletingUser(username, password);
        mainMenu();
    }

    void cancelBooking(String username, String password) throws Exception {
        ConsoleOUT.bookingID();
        String bookingID = ConsoleIN.reader();
        boolean deleted = bookingController.deleteBooking(bookingID);
        if (!deleted) {
            ConsoleOUT.wrongBookingID();
            logIn(username, password);
        } else {
            ConsoleOUT.bookingDeleted();
            logIn(username, password);
        }
    }
}
