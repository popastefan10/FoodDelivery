package menu;

public class MenuOption {
    private String description;
    private Runnable action;

    public MenuOption(String description) {
        this.description = description;
    }

    public MenuOption(String description, Runnable action) {
        this.description = description;
        this.action = action;
    }

    public String getDescription() {
        return description;
    }

    public void runAction() {
        action.run();
    }
}
