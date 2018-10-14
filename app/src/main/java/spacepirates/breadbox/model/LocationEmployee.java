package spacepirates.breadbox.model;

public class LocationEmployee extends BasicUser {

    private Location location;

    public LocationEmployee(String user, UserType t, Location locus) {
        super(user, t);
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
