package spacepirates.breadbox.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BasicUser extends User {
    String password;

    public BasicUser(String user, UserType t, String password) {
        super(user, t);
        this.password = password;
    }

    public BasicUser(String user, String password) {
        super(user, UserType.BASIC);
        this.password = password;
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
