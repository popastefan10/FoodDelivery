package models;

public abstract class Entity {
    static int count = 0;
    private final int id;

    public Entity() {
        id = ++count;
    }

    public int getId() {
        return id;
    }
}
