package utils;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class IOUtils {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static String readString(Scanner in, String prompt, int indentation) {
        System.out.print("\t".repeat(indentation) + prompt);
        return in.nextLine();
    }

    public static LocalTime readLocalTime(Scanner in, String prompt, int indentation) {
        prompt = "\t".repeat(indentation) + prompt;
        String errorMessage = "\t".repeat(indentation) + "Please enter a valid time in HH:mm:ss format!";

        while (true) {
            System.out.print(prompt);
            String localTimeString = in.nextLine();
            try {
                return LocalTime.parse(localTimeString);
            } catch (DateTimeParseException e) {
                System.out.println(errorMessage);
            }
        }
    }

    public static int readInt(Scanner in, String prompt, int indentation) {
        prompt = "\t".repeat(indentation) + prompt;
        String errorMessage = "\t".repeat(indentation) + "Please enter a valid integer!";

        while (true) {
            System.out.print(prompt);
            String intString = in.nextLine();
            try {
                return Integer.parseInt(intString);
            } catch (NumberFormatException e) {
                System.out.println(errorMessage);
            }
        }
    }
}
