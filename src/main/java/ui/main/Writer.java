package ui.main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Writer {

    public static void main(String[] args) throws IOException {

        File file = new File("C:\\Users\\User\\Desktop\\IdeaProjects\\StepProjectFlights\\files\\object.bin");
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        List<String> list = new ArrayList<>();
        list.add("AAA");
        list.add("BBB");
        list.add("CCC");

        oos.writeBoolean(true);
        oos.writeInt(42);
        oos.writeObject(list);
        oos.writeObject("Hello2");

        oos.close();
        fos.close();
    }
}
