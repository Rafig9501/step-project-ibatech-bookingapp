package ui.main;

import controller.Controller;
import controller.ControllerBooking;
import controller.ControllerFlight;
import controller.ControllerUser;
import dao.FlightDAO;
import dao.UserDAO;
import entity.User;
import service.ServiceBooking;
import service.ServiceFlight;

public class Main {

    public static void main(String[] args) throws Exception {

        Controller controller = new Controller();
        ControllerFlight flight = new ControllerFlight();
        controller.mainMenu();
    }
}
