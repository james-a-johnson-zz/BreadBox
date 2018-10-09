package spacepirates.breadbox;

import java.util.List;

public class BasicUser {

    protected String username;
    protected String password;

    public BasicUser(String user, String pass) {
        username = user;
        password = pass;
    }

    public List<Location> searchLocation() {
        return null;
    }

    public List<DonationItem> searchItem() {
        return null;
    }

    public String comment() {
        return "";
    }
}
