package spacepirates.breadbox.model;

import java.util.HashMap;
import java.util.Map;

public class GuestUser extends User {
    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> databaseRepr = new HashMap<>();
        databaseRepr.put("type", type);
        return databaseRepr;
    }
}
