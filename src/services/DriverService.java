package services;

import models.Driver;
import repositories.DriverRepository;
import utils.IOUtils;

import java.util.Map;
import java.util.Scanner;

public class DriverService {
    private static DriverService instance = null;
    private final DriverRepository driverRepository = DriverRepository.getInstance();
    private final AuditService auditService = AuditService.getInstance();
    private final PersonService personService = PersonService.getInstance();

    private DriverService() {
    }

    public static DriverService getInstance() {
        if (instance == null)
            instance = new DriverService();
        return instance;
    }

    public Driver readDriver(Scanner in) {
        System.out.println("Creating a new driver...");
        Driver driver = new Driver();

        personService.readPerson(in, driver);
        driver.setRating(IOUtils.readFloat(in, "Rating (0-5): ", 1));

        return driver;
    }

    public Driver readDriver(Scanner in, Driver defaultValue) {
        System.out.println("Updating driver...");
        Driver driver = new Driver();
        driver.setId(defaultValue.getId());

        personService.readPerson(in, driver, defaultValue);
        driver.setRating(IOUtils.readFloat(in, "Rating (0-5)", defaultValue.getRating(), 1));

        return driver;
    }

    public Integer readDriverId(Scanner in) {
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

    public void printDriver(Driver driver) {
        System.out.println("Driver with id " + driver.getId() + ":");
        personService.printPerson(driver);
        System.out.println("\tRating: " + driver.getRating());
    }

    public void printDriverShort(Driver driver) {
        System.out.printf("\t%s\t%s %s\n", driver.getId(), driver.getFirstName(), driver.getLastName());
    }

    public void create(Driver driver) {
        auditService.logAction("driver_create");
        driverRepository.create(driver);
    }

    public Driver[] getAll() {
        auditService.logAction("driver_get_all");
        return driverRepository.getAll().values().toArray(new Driver[0]);
    }

    public Driver getById(Integer driverId) {
        auditService.logAction("driver_get_by_id");
        return driverRepository.getById(driverId);
    }

    public Driver update(Driver driver) {
        auditService.logAction("driver_update");
        return driverRepository.update(driver);
    }

    public boolean delete(Integer driverId) {
        auditService.logAction("driver_delete");
        return driverRepository.delete(driverId);
    }
}
