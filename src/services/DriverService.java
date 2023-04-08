package services;

import models.Driver;
import utils.IOUtils;

import java.util.Scanner;

public class DriverService extends PersonService {
    public Driver readDriver(Scanner in) {
        System.out.println("Creating a new driver...");
        Driver driver = new Driver();

        super.readPerson(in, driver);

        driver.setStartOfWorkingHours(IOUtils.readLocalTime(in, "Start of working hours (in HH:mm:ss format): ", 1));
        driver.setEndOfWorkingHours(IOUtils.readLocalTime(in, "End of working hours (in HH:mm:ss format): ", 1));

        return driver;
    }

    public void printDriver(Driver driver) {
        System.out.println("Driver with id " + driver.getId() + ":");
        super.printPerson(driver);
        System.out.println("\tStart of working hours: " + driver.getStartOfWorkingHours());
        System.out.println("\tEnd of working hours: " + driver.getEndOfWorkingHours());
    }
}
