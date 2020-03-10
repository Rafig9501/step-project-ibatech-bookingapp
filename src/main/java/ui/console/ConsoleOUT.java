package ui.console;

public class ConsoleOUT {

    public static void print(String string){
        System.out.println(string);
    }

    public static void printingMenuLogIn(){

        print("==========================================");
        print("            TICKET BOOKING APP          ");
        print("==========================================");
        print("");
        print("   1. Sign In ");
        print("   2. Log In ");
        print("   3. Exit ");
    }

    public static void printingMenuSignedIn(){

        print("==========================================");
        print("            TICKET BOOKING APP          ");
        print("==========================================");
        print("");
        print("   1. Show Timetable");
        print("   2. Search for a flight");
        print("   3. Make a booking");
        print("   4. Show my bookings");
        print("   5. Cancel my booking");
        print("   6. Log out");
        print("   7. Exit");
    }
}
