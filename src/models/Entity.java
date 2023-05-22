package models;

public abstract class Entity {
    static int count = 0;
    private int id;

    public Entity() {
        id = ++count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
