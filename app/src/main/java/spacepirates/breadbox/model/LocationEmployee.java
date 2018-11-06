package spacepirates.breadbox.model;

public class LocationEmployee extends BasicUser {

    private Location location;

    /**
     * Constructor that takes in a type. User for calls from below by manager class.
     * Assumes a valid type is passed in. Should only be initialized to Location Manager,
     * or Location Employee.
     *
     * @param user
     * @param t
     * @param locus
     */
    public LocationEmployee(String user, UserType t, Location locus) {
            super(user, t);
        location = locus;
    }

    /**
     * Constructor for Location Employee, type is set to UserType.LOCATION_EMPLOYEE.
     *
     * @param user
     * @param locus
     */
    public LocationEmployee(String user, Location locus) {
        super(user, UserType.LOCATION_EMPLOYEE);
        location = locus;
    }


    public boolean addItem(DonationItem item) {
        return false;
    }

    public DonationItem sellItem(DonationItem item) {
        return null;
    }

    public int getStatistics() {
        return 0;
    }
}
