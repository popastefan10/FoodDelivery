package services;

import models.Driver;
import repositories.DriverRepository;
import utils.IOUtils;

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

    public static Driver readDriver(Scanner in, Driver defaultDriver) {
        System.out.println("Updating driver...");
        Driver driver = new Driver();
        driver.setId(defaultDriver.getId());

        PersonService.readPerson(in, driver, defaultDriver);
        driver.setRating(IOUtils.readFloat(in, "Rating (0-5)", defaultDriver.getRating(), 1));

        return driver;
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
