package spacepirates.breadbox.model;

public class LocationEmployee extends BasicUser {

    private Location location;

    public LocationEmployee(String user, String pass, Location locus) {
        super(user, pass);
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
