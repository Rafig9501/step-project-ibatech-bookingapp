package controller;

import ui.console.ConsoleIN;
import ui.console.ConsoleOUT;
import utilities.Credentials;

import java.io.IOException;

public class Controller {

    ControllerUser controllerUser;
    ControllerBooking controllerBooking;
    ControllerFlight controllerFlight;

    public Controller() throws IOException, ClassNotFoundException {
        controllerUser = new ControllerUser();
        controllerFlight = new ControllerFlight();
        controllerBooking = new ControllerBooking();
    }

    public void mainMenu() throws IOException, ClassNotFoundException {
        ConsoleOUT.printingMenuLogIn();
        switch (ConsoleIN.menuLogIn()) {
            case 1:
                signIn();
                break;
            case 2:
                logIn();
                break;
            case 3:
                break;
        }
    }

    public void signIn() throws IOException, ClassNotFoundException {
        ConsoleOUT.userName();
        String username = ConsoleIN.reader();
        ConsoleOUT.password();
        String password = ConsoleIN.reader();
        ControllerUser controllerUser = new ControllerUser();
        boolean saveUser = controllerUser.saveUser(username, password);
        if (saveUser) {
            ConsoleOUT.registered();
            Credentials.addingUser(username,password);
            mainMenu();
        } else {
            ConsoleOUT.notRegistered();
            signIn();
        }
    }

    public void logIn() throws IOException, ClassNotFoundException {
        ConsoleOUT.userName();
        String username = ConsoleIN.reader();
        ConsoleOUT.password();
        String password = ConsoleIN.reader();
        if (controllerUser.service.getAll().stream().anyMatch(user -> user.getUserName().equals(username)
            && user.getPassword().equals(password))) {
            Credentials.addingUser(username,password);
            ConsoleOUT.printingMenuSignedIn();
            switch (ConsoleIN.menuSignIn()) {
                case 1:
                    controllerFlight.getAllFlights();
                    break;
                case 2:
                    ConsoleOUT.passengerName();
                    String name = ConsoleIN.reader();
                    ConsoleOUT.passengeSurname();
                    String surname = ConsoleIN.reader();
                    ConsoleOUT.flightId();
                    String flightID = ConsoleIN.reader();
                    try {
                        controllerBooking.saveBooking(name, surname, flightID);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }break;
                case 3:
                    controllerBooking.getAllBookings();
            }
        }else {
            ConsoleOUT.loginFailed();
            mainMenu();
        }
    }
}
