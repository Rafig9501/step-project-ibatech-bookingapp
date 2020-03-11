package utilities;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.*;

public class RandomGen {

    static List<String> ID = new ArrayList<>();

    public static String genFlightID() {

        String alphabetic = RandomStringUtils.randomAlphabetic(3);
        String numeric = RandomStringUtils.randomNumeric(2);
        String random = numeric + " " + alphabetic.toUpperCase();
        if (!(ID.contains(random))){
            ID.add(random);
            return random;
        }
        else return genFlightID();
    }
}

