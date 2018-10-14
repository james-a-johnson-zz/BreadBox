package spacepirates.breadbox.model;


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
}
