package spacepirates.breadbox.model;

public class Manager extends BasicUser {

    private Business business;

    public Manager(String user, String pass, Business bus) {
        super(user, pass);
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
}
