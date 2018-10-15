package spacepirates.breadbox.model;

import java.util.List;

public class BasicUser extends User {

    protected String username;
    protected String password;

    public BasicUser(String user, String pass) {
        username = user;
        password = pass;
    }

    public String comment() {
        return "";
    }
}
