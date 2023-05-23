package services;

import models.User;
import utils.IOUtils;

import java.util.Scanner;

public class UserService {
    public static void readUser(Scanner in, User user) {
        user.setEmail(IOUtils.readString(in, "Email: ", 1));
        user.setPhoneNumber(IOUtils.readString(in, "Phone number: ", 1));
    }

    public static void readUser(Scanner in, User user, User defaultUser) {
        user.setEmail(IOUtils.readString(in, "Email", defaultUser.getEmail(), 1));
        user.setPhoneNumber(IOUtils.readString(in, "Phone number", defaultUser.getPhoneNumber(), 1));
    }

    public static void printUser(User user) {
        System.out.println("\tEmail: " + user.getEmail());
        System.out.println("\tPhone number: " + user.getPhoneNumber());
    }
}
