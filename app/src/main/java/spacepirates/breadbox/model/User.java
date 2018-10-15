package spacepirates.breadbox.model;

import java.util.Map;

//TODO other user classes must extend user
public abstract class User {
    String username;
    UserType type;

    String getUsername() {
        return username;
    }

    UserType getType() {
        return type;
    }

    public abstract Map<String, Object> toMap();
}
