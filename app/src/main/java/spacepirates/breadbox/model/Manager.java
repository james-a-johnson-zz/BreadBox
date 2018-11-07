package spacepirates.breadbox.model;

/**
 * Manager is a type of LocationEmployee that has more capabilities and modifying power
 * Manager is able to view statistics for location
 * as well as manage employees that work there
 */
public class Manager extends LocationEmployee {

    /**
     * Main constructor
     * @param user      Username
     * @param locus     Location that they supervise
     */
    public Manager(String user, Location locus) {
        super(user, UserType.MANAGER, locus);
    }

    /**
     * Constructor for different usertypes?
     * @param user      Username
     * @param ut        User type
     * @param locus     Location that they supervise
     */
    public Manager(String user, UserType ut, Location locus) {
        super(user, ut, locus);
    }

//    //Return values are super iffy here. Subject to change later
//    public int getStatistics() {
//        return 0;
//    }
//
//    public boolean addLocation(Location locus) {
//        return false;
//    }
//
//    public Location removeLocation(Location locus) {
//        return null;
//    }
//
//    public boolean addEmployee(LocationEmployee employee) {
//        return false;
//    }
//
//    public LocationEmployee removeEmployee(LocationEmployee employee) {
//        return null;
//    }
}
