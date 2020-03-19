package ui.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleIN {

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static String reader() throws IOException {
        return reader.readLine();
    }

    public static String menuLogIn() throws IOException {

        switch (reader()) {
            case "1":
                return "1";
            case "2":
                return "2";
            case "3":
                return "3";
            case "4":
                return "4";
            default:
                ConsoleOUT.print("Please enter right command");
                return menuLogIn();
        }
    }

    public static String menuSignIn() throws IOException {

        switch (reader()) {
            case "1":
                return "1";
            case "2":
                return "2";
            case "3":
                return "3";
            case "4":
                return "4";
            case "5":
                return "5";
            case "6":
                return "6";
            default:
                ConsoleOUT.print("Please enter right command");
                return menuSignIn();
        }
    }
}
