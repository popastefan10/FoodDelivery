package utils;

import java.util.Scanner;

public class IOUtils {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";

    public static void printError(String errorMessage) {
        System.out.println(ANSI_RED + errorMessage + ANSI_RESET);
    }

    public static String readString(Scanner in, String prompt, int indentation) {
        System.out.print("\t".repeat(indentation) + prompt);
        return in.nextLine();
    }

    public static String readString(Scanner in, String prompt, String defaultValue, int indentation) {
        System.out.print("\t".repeat(indentation) + prompt + " (default: " + defaultValue + "): ");
        String input = in.nextLine();
        return input.isEmpty() ? defaultValue : input;
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
                printError(errorMessage);
            }
        }
    }

    public static Integer readInt(Scanner in, String prompt, Integer defaultValue, int indentation) {
        prompt = "\t".repeat(indentation) + prompt + " (default: " + defaultValue + "): ";
        String errorMessage = "\t".repeat(indentation) + "Please enter a valid integer!";

        while (true) {
            System.out.print(prompt);
            String intString = in.nextLine();
            if (intString.isEmpty())
                return defaultValue;
            try {
                return Integer.parseInt(intString);
            } catch (NumberFormatException e) {
                printError(errorMessage);
            }
        }
    }

    public static float readFloat(Scanner in, String prompt, int indentation) {
        prompt = "\t".repeat(indentation) + prompt;
        String errorMessage = "\t".repeat(indentation) + "Please enter a valid real number!";

        while (true) {
            System.out.print(prompt);
            String floatString = in.nextLine();
            try {
                return Float.parseFloat(floatString);
            } catch (NumberFormatException | NullPointerException e) {
                printError(errorMessage);
            }
        }
    }

    public static float readFloat(Scanner in, String prompt, Float defaultValue, int indentation) {
        prompt = "\t".repeat(indentation) + prompt + " (default: " + defaultValue + "): ";
        String errorMessage = "\t".repeat(indentation) + "Please enter a valid real number!";

        while (true) {
            System.out.print(prompt);
            String floatString = in.nextLine();
            if (floatString.isEmpty()) {
                return defaultValue;
            }
            try {
                return Float.parseFloat(floatString);
            } catch (NumberFormatException | NullPointerException e) {
                printError(errorMessage);
            }
        }
    }

    public static boolean readBoolean(Scanner in, String prompt, int indentation) {
        prompt = "\t".repeat(indentation) + prompt + " (y/n): ";
        String errorMessage = "\t".repeat(indentation) + "Please enter yes (y) or no (n)!";

        while (true) {
            System.out.print(prompt);
            String booleanString = in.nextLine();
            if (booleanString.toLowerCase().startsWith("y"))
                return true;
            else if (booleanString.toLowerCase().startsWith("n"))
                return false;
            else
                printError(errorMessage);
        }
    }
}
