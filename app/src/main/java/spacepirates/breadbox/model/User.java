package spacepirates.breadbox.model;

import java.util.Map;

public abstract class User {
    String username;
    UserType type;

    User (String username, UserType type) {
        this.username = username;
        this.type = type;
    }

    String getUsername() {
        return username;
    }

    UserType getType() {
        return type;
    }

    //TODO implement toMap() in children of user. Not really sure what this should do. -Alexander
    public abstract Map<String, Object> toMap();
}
