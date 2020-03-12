package pojo;

public class Booking {

    User user;
    String name;
    String surname;
    Flight flight;

    public Booking(User user, String name, String surname, Flight flight) {
        this.user = user;
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

    public User getUser() {
        return user;
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
