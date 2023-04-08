package services;

import models.Customer;
import models.Driver;
import utils.IOUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

public class DriverService {
    private static final Map<UUID, Driver> driversMap = new HashMap<UUID, Driver>();

    public static Driver readDriver(Scanner in) {
        System.out.println("Creating a new driver...");
        Driver driver = new Driver();

        PersonService.readPerson(in, driver);

        driver.setStartOfWorkingHours(IOUtils.readLocalTime(in, "Start of working hours (in HH:mm:ss format): ", 1));
        driver.setEndOfWorkingHours(IOUtils.readLocalTime(in, "End of working hours (in HH:mm:ss format): ", 1));

        return driver;
    }

    public static void printDriver(Driver driver) {
        System.out.println("Driver with id " + driver.getId() + ":");
        PersonService.printPerson(driver);
        System.out.println("\tStart of working hours: " + driver.getStartOfWorkingHours());
        System.out.println("\tEnd of working hours: " + driver.getEndOfWorkingHours());
    }

    public static void printDriverShort(Driver driver) {
        System.out.printf("\t%s\t%s %s\n", driver.getId().toString(), driver.getFirstName(), driver.getLastName());
    }

    public static void addDriver(Driver driver) {
        driversMap.put(driver.getId(), driver);
    }

    public static Driver[] getDrivers() {
        return driversMap.values().toArray(new Driver[0]);
    }

    public static Driver getDriverById(UUID driverId) {
        return driversMap.get(driverId);
    }
}
