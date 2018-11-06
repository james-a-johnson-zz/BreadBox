package spacepirates.breadbox.model;


import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Queue;
import java.util.PriorityQueue;

//TODO Make sure classes and methods are implmented good.
/** Prof talks about message chains being bad
 * ie: model.getLocations.getLocation.getName
 * We should write our model so that we don't do that.
 * alluded to M10  being where message chains are checked by an autograder.
 */
public class Model {
    /** Singleton instance */
    private static final Model _instance = new Model();
    public static Model getInstance() { return _instance; }

    //user logged in and operating app
    private User _currentUser;

    private final User nullUser = new GuestUser();

    //instance of location database
    private LocationDatabase locationDatabase;

    /** the currently selected course, defaults to first course */
    private Location _currentLocation;

    /** Null Location pattern, returned when no course is found */
    private final Location theNullLocation = new Location("No Locations", "none", 0, 0, "Not Found", "000-000-0000");

    private DonationItemDatabase donationItemDatabase;

    /** Null Donation pattern, returned when no donations are found.
     *  Current default category is apparel. Fails curing run if category is null.
     */
    private DonationItem theNullDonation = new DonationItem("No Donations Found", 0, Category.APPAREL);

    /**
     * make a new model
     */
    private Model() {
        Log.d("Model", "Initialized Model, without context");
        this.initializeDatabases();
        _currentUser = nullUser;
    }

    private Model(Context context) {
        this();
    }

    private void initializeDatabases() {
        locationDatabase = new LocationDatabase();
        donationItemDatabase = new DonationItemDatabase();
    }

    public List<Location> getLocations() throws DatabaseNotInitializedException {
        if (locationDatabase == null) {
            throw new DatabaseNotInitializedException();
        }
        // If locationDatabsae is empty, return list with the null location.
        if (locationDatabase.getLocations().size() == 0) {
            ArrayList<Location> noLocations = new ArrayList<>();
            noLocations.add(theNullLocation);
            return noLocations;
        }
        return locationDatabase.getLocations();
    }

    public Location getLocationByAddress(String address) {
        Location l = locationDatabase.getLocationByAddress(address);
        if (l == null)
            return theNullLocation;
        else
            return l;
    }

    public List<DonationItem> getDonationItems() {
        return donationItemDatabase.getDonations();
    }

    public List<DonationItem> filterDonationItems(List<DonationItem> list, Category cat) {
        PriorityQueue<DonationItem> ret = new PriorityQueue<DonationItem>();
        ArrayList<DonationItem> srt = new ArrayList<>();
        for (DonationItem d: list) {
            if (d.getCategory() == cat) {
                ret.add(d);
            }
        }
        while(!ret.isEmpty()) {
            srt.add(ret.poll());
        }
        return srt;
    }

    public ArrayList<DonationItem> filterDonationItems(String name) {
        PriorityQueue<DonationItem> ret = new PriorityQueue<DonationItem>();
        ArrayList<DonationItem> srt = new ArrayList<>();
        for (DonationItem d: donationItemDatabase.getDonations()) {
            if (d.getName().equals(name)) {
                ret.add(d);
            }
        }
        while(!ret.isEmpty()) {
            srt.add(ret.poll());
        }
        return srt;
    }

    public ArrayList<DonationItem> filterDonationItems(Category cat) {
        PriorityQueue<DonationItem> ret = new PriorityQueue<DonationItem>();
        ArrayList<DonationItem> srt = new ArrayList<>();
        for (DonationItem d: donationItemDatabase.getDonations()) {
            if (d.getCategory() == cat) {
                ret.add(d);
            }
        }
        while(!ret.isEmpty()) {
            srt.add(ret.poll());
        }
        return srt;
    }

    /**
     * //TODO Decide how to implement the filter.
     * Idea: What if the filter was an interface? Then locations and DonationItems could implement
     * the same filter, and Filter using their own sets of data?
     *
     * Seems like a pointless excersise, I feel that the Model should probably just handle all the
     * filtering. Overcomplicates by spreading out filtering duty, for a gain I don't see.
     */

    /**
     * Filters DonationItems.
     *
     * @param location
     * @param category
     * @return Returns an ArrayList of all the DonationItems in a specified category at a location.
     */
    public List<DonationItem> filterDonationItems(Location location, Category category) {
        return filterDonationItems(location.getInventory(), category);
    }

    //TODO There should be filterDonationItem methods implmented for every way a donation should be filtered.
    public List<DonationItem> filterDonationItems(List<DonationItem> list, final String input) {
        // PriorityQueue<DonationItem> ret = new PriorityQueue<DonationItem>(list.size(),
        //     (DonationItem a, DonationItem b) -> a.getName().compareTo(name)
        //     - b.getName().compareTo(name));
        PriorityQueue<DonationItem>ret = new PriorityQueue<>(list.size(), new Comparator<DonationItem>() {
            @Override
            public int compare(DonationItem donationItem, DonationItem t1) {
                return donationItem.getName().compareTo(input) - (t1.getName().compareTo(input));
            }
        });
        ArrayList<DonationItem> srt = new ArrayList<>();
        for (DonationItem d: list) {
            ret.add(d);
        }
        while(!ret.isEmpty()) {
            srt.add(ret.poll());
        }
        return srt;
    }

    public List<DonationItem> filterDonationItems(Location location, String input) {
        return filterDonationItems(location.getInventory(), input);
    }

    // public Queue<Location> filterLocations(List<Location> list, String input) {
    //     return locationDatabase.getLocationsByAddress(list, input);
    // }

    /**
     * add a location to the app.  checks if the location is already entered
     *
     * uses O(n) linear search for course
     *
     * @param location  the course to be added
     * @return true if added, false if a duplicate
     */
    public boolean addLocation(Location location) throws DatabaseNotInitializedException {
        if (locationDatabase == null) {
            throw new DatabaseNotInitializedException();
        }
        for (Location l : locationDatabase.getLocations() ) {
            if (l.equals(location)) return false;
        }
        //TODO write method in location database to add locations
        locationDatabase.addLocation(location);
        return true;
    }

    /**
     * @return  the currently selected location
     */
    public Location getCurrentLocation() throws DatabaseNotInitializedException {
        if (locationDatabase == null) {
            throw new DatabaseNotInitializedException();
        }

        return _currentLocation;
    }

    public void setCurrentLocation(Location location) { _currentLocation = location; }

    /**
     * Return a location that has matching name.
     * This uses an O(n) linear search.
     *
     * @param name the name of the location to find
     * @return  the location with that name or the NullLocation if no such name exists.
     *
     */
    public Location getLocationByName(String name) throws DatabaseNotInitializedException{
        if (locationDatabase == null) {
            throw new DatabaseNotInitializedException();
        }
        for (Location l : locationDatabase.getLocations() ) {
            //TODO need some way to find a specific location index? number? name?
            if (l.getName().equals(name)) return l;
        }
        return theNullLocation;
    }

    /**
     * Set the current user logged in to the app.
     *
     * @param user
     */
    public void setCurrentUser(User user) {
        _currentUser = user;
    }

    /**
     * Returns current User logged in or null/ guest user otherwise.
     *
     * @return the current user
     */
    public User getCurrentUser() {
        return _currentUser;
    }

    public ArrayList<DonationItem> getDonations() throws DatabaseNotInitializedException{
        if (donationItemDatabase == null) {
            throw new DatabaseNotInitializedException();
        }

        // If donationDatabase is empty, return list with the null donation.
        if (donationItemDatabase.getDonations().size() == 0) {
            ArrayList<DonationItem> noDonations = new ArrayList<>();
            noDonations.add(theNullDonation);
            return noDonations;
        }
        return (ArrayList<DonationItem>) donationItemDatabase.getDonations();
    }

    /**
     * Adds a donation item to the donation item database.
     * Currently donations are added to location inventories in the donation item constructor.
     * That should likely be done by this method.
     *
     * @param donation
     */
    public void addDonationItem(DonationItem donation) {
        donationItemDatabase.addItem(donation);
        Location addedTo = this.getLocationByAddress(donation.getAddress());
        addedTo.addItem(donation);
        locationDatabase.updateLocation(addedTo);
    }

    /**
     * Exception thrown when app tries to retrieve from location database before it is initialized.
     */
    private class DatabaseNotInitializedException extends RuntimeException {

    }

}
