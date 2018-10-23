package spacepirates.breadbox.model;

import java.util.HashMap;
import java.util.Map;

public class GuestUser extends User {

    GuestUser() {
        super("Guest", UserType.GUEST);
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> databaseRepr = new HashMap<>();
        databaseRepr.put("username", username);
        databaseRepr.put("type", type);
        return databaseRepr;
    }
}
