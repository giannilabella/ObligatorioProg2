package entities;

import java.util.Objects;

public class Driver {
    private final String name;
    private final String surname;
    private final String fullName;

    public Driver(String name, String surname, String fullName) {
        this.name = name;
        this.surname = surname;
        this.fullName = fullName;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getFullName() {
        return fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Driver driver = (Driver) o;
        return Objects.equals(fullName, driver.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName);
    }
}
