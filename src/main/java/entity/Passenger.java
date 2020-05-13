package entity;

import java.io.Serializable;
import java.util.Objects;

import static utilities.RandomGen.genPassengerID;

public class Passenger implements Serializable {

    String name;//zayka
    String surname;//zaykayevic
    String id;//pis-pis

    public Passenger(String name, String surname) {
        this.name = name;
        this.surname = surname;
        id = genPassengerID();
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String represent() {
        return String.format("for %s %s", name, surname);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Passenger passenger = (Passenger) o;
        return Objects.equals(name, passenger.name) &&
                Objects.equals(surname, passenger.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname);
    }

}
