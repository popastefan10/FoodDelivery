package services;

import models.Person;
import utils.IOUtils;

import java.util.Scanner;

public class PersonService extends UserService {
    public void createPerson(Scanner in, Person person) {
        super.createUser(in, person);
        person.setFirstName(IOUtils.readString(in, "First name: ", 1));
        person.setLastName(IOUtils.readString(in, "Last name: ", 1));
    }

    public void printPerson(Person person) {
        super.printUser(person);
        System.out.println("\tFirst name: " + person.getFirstName());
        System.out.println("\tLast name: " + person.getLastName());
    }
}
