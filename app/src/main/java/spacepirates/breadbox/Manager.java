package spacepirates.breadbox;
import java.util.List;

public class Manager extends BasicUser {

    public Manager(String user, String pass) {
        super(user, pass);
    }

    //Return values are super iffy here. Subject to change later
    public int getStatistics() {
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
}
