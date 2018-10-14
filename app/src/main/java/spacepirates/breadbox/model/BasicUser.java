package spacepirates.breadbox.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BasicUser extends User {

    public BasicUser(String user, UserType t) {
        username = user;
        type = t;
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

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> databaseRepr = new HashMap<>();
        databaseRepr.put("username", username);
        databaseRepr.put("type", type);
        return databaseRepr;
    }
}
