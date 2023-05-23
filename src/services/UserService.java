package services;

import models.User;
import utils.IOUtils;

import java.util.Scanner;

public class UserService {
    private static UserService instance = null;

    private UserService() {
    }

    public static UserService getInstance() {
        if (instance == null)
            instance = new UserService();
        return instance;
    }

    public void readUser(Scanner in, User user) {
        user.setEmail(IOUtils.readString(in, "Email: ", 1));
        user.setPhoneNumber(IOUtils.readString(in, "Phone number: ", 1));
    }

    public void readUser(Scanner in, User user, User defaultUser) {
        user.setEmail(IOUtils.readString(in, "Email", defaultUser.getEmail(), 1));
        user.setPhoneNumber(IOUtils.readString(in, "Phone number", defaultUser.getPhoneNumber(), 1));
    }

    public void printUser(User user) {
        System.out.println("\tEmail: " + user.getEmail());
        System.out.println("\tPhone number: " + user.getPhoneNumber());
    }
}
