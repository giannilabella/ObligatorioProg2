package controllers;

import entities.Driver;
import uy.edu.um.prog2.adt.list.MyList;
import uy.edu.um.prog2.adt.list.MySinglyLinkedList;

public class DriverController {
    private static DriverController INSTANCE;
    private final MyList<Driver> drivers;

    private DriverController() {
        this.drivers = new MySinglyLinkedList<>();
    }

    public static DriverController getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DriverController();
        }

        return INSTANCE;
    }

    public void create(String fullName) {
        fullName = fullName.trim();
        String[] splitName = fullName.split(" ", 2);
        String name = splitName[0];
        String surname = splitName[1];

        Driver driver = new Driver(name, surname, fullName);
        drivers.add(driver);
    }

    public MyList<Driver> getDrivers() {
        return drivers;
    }

    public int getDriversCount() {
        return drivers.size();
    }
}
