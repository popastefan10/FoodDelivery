package services;

import models.Person;
import utils.IOUtils;

import java.util.Scanner;

public class PersonService {
    public static void readPerson(Scanner in, Person person) {
        UserService.readUser(in, person);
        person.setFirstName(IOUtils.readString(in, "First name: ", 1));
        person.setLastName(IOUtils.readString(in, "Last name: ", 1));
    }

    public static void readPerson(Scanner in, Person person, Person defaultPerson) {
        UserService.readUser(in, person, defaultPerson);
        person.setFirstName(IOUtils.readString(in, "First name", defaultPerson.getFirstName(), 1));
        person.setLastName(IOUtils.readString(in, "Last name", defaultPerson.getLastName(), 1));
    }

    public static void printPerson(Person person) {
        UserService.printUser(person);
        System.out.println("\tFirst name: " + person.getFirstName());
        System.out.println("\tLast name: " + person.getLastName());
    }
}
