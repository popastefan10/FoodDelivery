package services;

import models.Driver;
import repositories.DriverRepository;
import utils.IOUtils;

import java.util.Map;
import java.util.Scanner;

public class DriverService {
    private static final DriverRepository driverRepository = DriverRepository.getInstance();

    public static Driver readDriver(Scanner in) {
        System.out.println("Creating a new driver...");
        Driver driver = new Driver();

        PersonService.readPerson(in, driver);
        driver.setRating(IOUtils.readFloat(in, "Rating (0-5): ", 1));

        return driver;
    }

    public static Driver readDriver(Scanner in, Driver defaultValue) {
        System.out.println("Updating driver...");
        Driver driver = new Driver();
        driver.setId(defaultValue.getId());

        PersonService.readPerson(in, driver, defaultValue);
        driver.setRating(IOUtils.readFloat(in, "Rating (0-5)", defaultValue.getRating(), 1));

        return driver;
    }

    public static Integer readDriverId(Scanner in) {
        Map<Integer, Driver> drivers = driverRepository.getAll();
        System.out.println("Here are all available drivers:");
        for (Driver driver : drivers.values())
            printDriverShort(driver);
        System.out.println();

        while (true) {
            Integer driverId = IOUtils.readInt(in, "Choose a driver id: ", 0);
            if (drivers.containsKey(driverId))
                return driverId;
            System.out.println(IOUtils.ANSI_RED + "There is no driver with this id!" + IOUtils.ANSI_RESET);
        }
    }

    public static void printDriver(Driver driver) {
        System.out.println("Driver with id " + driver.getId() + ":");
        PersonService.printPerson(driver);
        System.out.println("\tRating: " + driver.getRating());
    }

    public static void printDriverShort(Driver driver) {
        System.out.printf("\t%s\t%s %s\n", driver.getId(), driver.getFirstName(), driver.getLastName());
    }

    public static void create(Driver driver) {
        driverRepository.create(driver);
    }

    public static Driver[] getAll() {
        return driverRepository.getAll().values().toArray(new Driver[0]);
    }

    public static Driver getById(Integer driverId) {
        return driverRepository.getById(driverId);
    }

    public static Driver update(Driver driver) {
        return driverRepository.update(driver);
    }

    public static boolean delete(Integer driverId) {
        return driverRepository.delete(driverId);
    }
}
