package pojo;

public class Booking {

    String name;
    String surname;
    Flight flight;

    public Booking(String name, String surname, Flight flight) {
        this.name = name;
        this.surname = surname;
        this.flight = flight;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Flight getFlight() {
        return flight;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", flight=" + flight +
                '}';
    }
}
