package spacepirates.breadbox.model;

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
}
