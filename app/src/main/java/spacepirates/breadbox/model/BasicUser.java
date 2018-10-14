package spacepirates.breadbox.model;

import java.util.List;

public class BasicUser {

    protected String username;

    public BasicUser(String user) {
        username = user;
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
