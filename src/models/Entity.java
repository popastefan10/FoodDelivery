package models;

import java.util.UUID;

public abstract class Entity {
    private UUID id;

    public Entity() {
        id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }
}
