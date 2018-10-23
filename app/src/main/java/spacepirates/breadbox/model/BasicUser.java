package spacepirates.breadbox.model;

public class BasicUser extends User {

    public BasicUser(String user, UserType t) {
        super(user, t);
    }

    public String comment() {
        return "";
    }
}
