package spacepirates.breadbox.model;

import java.util.HashMap;
import java.util.Map;

public class Manager extends BasicUser {

    private Business business;

    public Manager(String user, UserType t, Business bus) {
        super(user, t);
        business = bus;
    }

    //Return values are super iffy here. Subject to change later
    public int getStatistics(Business bus) {
        return 0;
    }

    public boolean addLocation(Location locus) {
        return false;
    }

    public Location removeLocation(Location locus) {
        return null;
    }

    public boolean addEmployee(LocationEmployee employee) {
        return false;
    }

    public LocationEmployee removeEmployee(LocationEmployee employee) {
        return null;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> databaseRepr = new HashMap<>();
        databaseRepr.put("username", username);
        databaseRepr.put("type", type);
        databaseRepr.put("business", business);
        return databaseRepr;
    }
}
