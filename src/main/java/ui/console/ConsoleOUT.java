package ui.console;

public class ConsoleOUT {

    public static void print(String string) {
        System.out.println(string);
    }

    public static void printingMenuLogIn() {

        print("==========================================");
        print("            TICKET BOOKING APP          ");
        print("==========================================");
        print("");
        print("   1. Sign In ");
        print("   2. Log In ");
        print("   3. Delete User");
        print("   4. Exit ");
        print("==========================================");
    }

    public static void printingMenuSignedIn() {

        print("==========================================");
        print("            TICKET BOOKING APP          ");
        print("==========================================");
        print("");
        print("   1. Show Flights");
        print("   2. Make a booking");
        print("   3. Show my bookings");
        print("   4. Cancel my booking");
        print("   5. Log out");
        print("   6. Exit");
        print("==========================================");
    }

    public static void userName() {
        print("Please enter your username :");
    }

    public static void password() {
        print("Please enter password : ");
    }

    public static void registered() {
        print("You have been registered succesfully");
    }

    public static void passengerName() {
        print("Please enter passenger name");
    }

    public static void passengeSurname() {
        print("Please enter passenger surname");
    }

    public static void notRegistered() {
        print("Registration wasn't succesful, please enter another login");
    }

    public static void flightId() {
        print("Please enter ID of flight");
    }

    public static void bookingMade() {
        print("Booking has been made");
    }

    public static void bookingDidntMade() {
        print("Please enter right name, surname and flight ID");
    }

    public static void notSuccesful() {
        print("Please enter right name, surname and flight ID");
    }

    public static void failed() {
        print("Please enter right login and password");
    }

    public static void userDeleted() {
        print("This user has been deleted!");
    }

    public static void bookingDeleted() {
        print("Booking has been deleted");
    }

    public static void thereIsNoBooking() {
        print("There is no booking");
    }
}
