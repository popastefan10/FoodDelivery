package menu;

import utils.IOUtils;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private final String description;
    private final ArrayList<MenuOption> menuOptions;

    public Menu(String description) {
        this.description = description;
        this.menuOptions = new ArrayList<>();
    }

    public void addOption(String description, Runnable action) {
        menuOptions.add(new MenuOption(description, action));
    }

    public void run(Scanner in) {
        System.out.println();
        System.out.println(IOUtils.ANSI_GREEN + description + IOUtils.ANSI_RESET);
        System.out.println();
        System.out.println("Available actions are:");
        for (int option = 0; option < menuOptions.size(); option++) {
            System.out.print(IOUtils.ANSI_GREEN + "\t" + option + IOUtils.ANSI_RESET);
            System.out.println(". " + menuOptions.get(option).getDescription());
        }

        int option = IOUtils.readInt(
                in, String.format("Enter an option (%d-%d): ", 0, menuOptions.size() - 1), 0);
        System.out.println();
        menuOptions.get(option).runAction();
    }
}
