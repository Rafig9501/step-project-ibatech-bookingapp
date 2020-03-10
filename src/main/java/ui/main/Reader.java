package ui.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class Reader {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        File file = new File("C:\\Users\\User\\Desktop\\IdeaProjects\\StepProjectFlights\\files\\object.bin");
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);

        boolean readed1 = ois.readBoolean(); // Boolean
        int readed2 = ois.readInt(); // Int
        List readed3 = (ArrayList) ois.readObject(); // Object

        ois.close();
        fis.close();

        System.out.println(readed1);
        System.out.println(readed2);
        System.out.println(readed3);
    }
}