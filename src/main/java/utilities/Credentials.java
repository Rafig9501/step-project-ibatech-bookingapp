package utilities;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class Credentials {

    public static Map<String, String> loggedIn = new HashMap<>();

    public static void addingUser(String userName, String password) {
        loggedIn.put(userName, password);
    }

    public static void deletingUser(String userName, String password) {
        loggedIn.remove(userName, password);
    }

    public static String getUserName() {
        return loggedIn.keySet().stream().findFirst().get();
    }
}
