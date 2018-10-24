package spacepirates.breadbox.model;

public class Manager extends LocationEmployee {

    public Manager(String user, Location locus) {
        super(user, UserType.MANAGER, locus);
    }

    public Manager(String user, UserType ut, Location locus) {
        super(user, ut, locus);
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
}
