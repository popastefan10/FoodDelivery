package utils;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class IOUtils {
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
}
