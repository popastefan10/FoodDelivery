package services;

import models.Person;
import utils.IOUtils;

import java.util.Scanner;

public class PersonService {
    private static PersonService instance = null;
    private final UserService userService = services.UserService.getInstance();

    private PersonService() {
    }

    public static PersonService getInstance() {
        if (instance == null)
            instance = new PersonService();
        return instance;
    }

    public void readPerson(Scanner in, Person person) {
        userService.readUser(in, person);
        person.setFirstName(IOUtils.readString(in, "First name: ", 1));
        person.setLastName(IOUtils.readString(in, "Last name: ", 1));
    }

    public void readPerson(Scanner in, Person person, Person defaultPerson) {
        userService.readUser(in, person, defaultPerson);
        person.setFirstName(IOUtils.readString(in, "First name", defaultPerson.getFirstName(), 1));
        person.setLastName(IOUtils.readString(in, "Last name", defaultPerson.getLastName(), 1));
    }

    public void printPerson(Person person) {
        userService.printUser(person);
        System.out.println("\tFirst name: " + person.getFirstName());
        System.out.println("\tLast name: " + person.getLastName());
    }
}
