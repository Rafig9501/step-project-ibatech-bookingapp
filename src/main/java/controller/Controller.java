package controller;

import ui.console.ConsoleIN;
import ui.console.ConsoleOUT;
import utilities.Credentials;

import java.io.IOException;

import static utilities.Constants_.*;

public class Controller {

    ControllerUser controllerUser;
    ControllerBooking controllerBooking;
    ControllerFlight controllerFlight;

    public Controller() throws IOException, ClassNotFoundException {
        controllerUser = new ControllerUser();
        controllerFlight = new ControllerFlight();
        controllerBooking = new ControllerBooking();
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
            case EXIT:
                break;
        }
    }

    void signIn() throws Exception {
        ConsoleOUT.userName();
        String username = ConsoleIN.reader();
        ConsoleOUT.password();
        String password = ConsoleIN.reader();
        boolean saveUser = controllerUser.saveUser(username, password);
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
        if (controllerUser.service.getAll().stream().anyMatch(user -> user.getUserName().equals(username)
                && user.getPassword().equals(password))) {
            Credentials.addingUser(username, password);
            ConsoleOUT.printingMenuSignedIn();
            switch (ConsoleIN.menuSignIn()) {
                case SHOW_FLIGHTS:
                    controllerFlight.getAllFlights();
                    logIn(username, password);
                    break;
                case MAKE_BOOKING:
                    makeBooking(username, password);
                    break;
                case SHOW_MY_BOOKINGS:
                    if (controllerBooking.getAllBookings()) {
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
            }
        } else {
            ConsoleOUT.failed();
            mainMenu();
        }
    }

    void makeBooking(String username, String password) throws Exception {
        ConsoleOUT.passengerName();
        String name = ConsoleIN.reader();
        ConsoleOUT.passengeSurname();
        String surname = ConsoleIN.reader();
        ConsoleOUT.flightId();
        String flightID = ConsoleIN.reader();
        boolean savedBooking = controllerBooking.saveBooking(name, surname, flightID);
        if (savedBooking) {
            ConsoleOUT.bookingMade();
            logIn(username, password);
        }
        else {
            ConsoleOUT.bookingDidntMade();
            logIn(username,password);
        }
    }

    void deleteUser() throws Exception {
        ConsoleOUT.userName();
        String username = ConsoleIN.reader();
        ConsoleOUT.password();
        String password = ConsoleIN.reader();
        if (controllerUser.deleteUser(username, password)) {
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
        ConsoleOUT.passengerName();
        String passengerName = ConsoleIN.reader();
        ConsoleOUT.passengeSurname();
        String passengerSurname = ConsoleIN.reader();
        ConsoleOUT.flightId();
        String flightID = ConsoleIN.reader();
        boolean deleted = controllerBooking.deleteBooking(passengerName, passengerSurname,flightID);
        if (!deleted) {
            ConsoleOUT.notSuccesful();
            logIn(username, password);
        } else {
            ConsoleOUT.bookingDeleted();
            logIn(username, password);
        }
    }
}
