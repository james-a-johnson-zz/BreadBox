package spacepirates.breadbox.model;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Queue;
import java.util.PriorityQueue;

//We need to make sure classes and methods are implemented good.
/** Prof talks about message chains being bad
 * ie: model.getLocations.getLocation.getName
 * We should write our model so that we don't do that.
 * alluded to M10  being where message chains are checked by an auto-grader.
 *
 * Model Acts as a liaison between system and databases
 */
public final class Model {
    /** Singleton instance */
    private static final Model _instance = new Model();

    /**
     * Returns the instance of the model. This is vital for classes that utilize it
     * @return _instance of the model
     */
    public static Model getInstance() { return _instance; }

    //user logged in and operating app
    private User _currentUser;

    //instance of location database
    private LocationDatabase locationDatabase;

    /** the currently selected course, defaults to first course */
    private Location _currentLocation;

    private DonationItemDatabase donationItemDatabase;


    /**
     * public model used for testing
     */
    public Model() {
        this.initializeDatabases();
        //_currentUser = nullUser;
    }

    private void initializeDatabases() {
        locationDatabase = new LocationDatabase();
        donationItemDatabase = new DonationItemDatabase();
    }

    /**
     * Get locations stored in the system. Works with location database
     * @return The stored locations
     * @throws DatabaseNotInitializedException Simple exception that represents a nonexistent
     *                                         Database since it never got initialized
     */
    public List<Location> getLocations() throws DatabaseNotInitializedException {
        if (locationDatabase == null) {
            throw new DatabaseNotInitializedException();
        }
        return locationDatabase.getLocations();
}

    /**
     * Searches for a location by address using getLocationByAddress in LocationDatabase
     * @param address the address being searched for
     * @return The location if it is found, otherwise a nullLocation sentinel value
     */
    public Location getLocationByAddress(String address) {
        return locationDatabase.getLocationByAddress(address);
    }

    /**
     * Get all donation items from Donation Item database
     * @return The donation items from the database
     */
    public List<DonationItem> getDonationItems() {
        return donationItemDatabase.getDonations();
    }

    /**
     * Get donation items by category through the Model
     * @param list Donation item list to filter
     * @param cat  Category to filter by
     * @return     The filtered list
     */
    public List<DonationItem> filterDonationItems(Iterable<DonationItem> list, Category cat) {
        PriorityQueue<DonationItem> ret = new PriorityQueue<>();
        List<DonationItem> srt = new ArrayList<>();
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

    /**
     * Filter donation items by name
     * @param name The name filter
     * @return     The filtered ArrayList
     */
    public List<DonationItem> filterDonationItems(String name) {
        List<DonationItem> srt = new ArrayList<>();
        Queue<DonationItem> ret = new PriorityQueue<>(donationItemDatabase.getDonations());
        while(!ret.isEmpty()) {
            srt.add(ret.poll());
        }
        return srt;
    }

    /**
     * Identical to filter by category above but returns an ArrayList
     * Also does not need to have a list of donation items passed in,
     * relies on the system's collection of all donation items in the database
     * @param cat  Category to filter by
     * @return     The filtered list
     */
    public List<DonationItem> filterDonationItems(Category cat) {
        PriorityQueue<DonationItem> ret = new PriorityQueue<>();
        List<DonationItem> srt = new ArrayList<>();
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

    //Decide how to implement the filter:
//     Idea: What if the filter was an interface? Then locations and DonationItems could implement
//      the same filter, and Filter using their own sets of data?
//
//      Seems like a pointless exercise, I feel that the Model should probably just handle all the
//      filtering. Over-complicates by spreading out filtering duty, for a gain I don't see.

//    /**
//     * Filters DonationItems.
//     *
//     * @param location
//     * @param category
//     *@return Returns an ArrayList of all the DonationItems in a specified category at a location.
//     */
//    public List<DonationItem> filterDonationItems(Location location, Category category) {
//        return filterDonationItems(location.getInventory(), category);
//    }

    /**
     * Identical to filter donation items by name but returns a List
     * @param list      List to filter
     * @param input     Name to filter by
     * @return          The filtered result
     */
    //There should be filterDonationItem methods implemented for every way we can filter.
    public List<DonationItem> filterDonationItems(
            Collection<DonationItem> list, final String input) {
        // PriorityQueue<DonationItem> ret = new PriorityQueue<DonationItem>(list.size(),
        //     (DonationItem a, DonationItem b) -> a.getName().compareTo(name)
        //     - b.getName().compareTo(name));
        Queue<DonationItem>ret = new PriorityQueue<>(list.size(),
                new Comparator<DonationItem>() {
            @Override
            public int compare(DonationItem donationItem, DonationItem t1) {
                String itemText = donationItem.getName();
                String t1Text = t1.getName();
                return itemText.compareTo(input) - (t1Text.compareTo(input));
            }
        });
        List<DonationItem> srt = new ArrayList<>();
        ret.addAll(list);
        while(!ret.isEmpty()) {
            srt.add(ret.poll());
        }
        return srt;
    }

//    public List<DonationItem> filterDonationItems(Location location, String input) {
//        return filterDonationItems(location.getInventory(), input);
//    }

    // public Queue<Location> filterLocations(List<Location> list, String input) {
    //     return locationDatabase.getLocationsByAddress(list, input);
    // }

    /**
     * Add a location to the app.  checks if the location is already entered
     *
     * Uses O(n) linear search for course
     *
     * @param location  the course to be added
     * @throws DatabaseNotInitializedException Simple exception that represents a nonexistent
     *                                         Database since it never got initialized
     */
    public void addLocation(Location location) throws DatabaseNotInitializedException {
        if (locationDatabase == null) {
            throw new DatabaseNotInitializedException();
        }
        locationDatabase.addLocation(location);
    }

    /**
     * @return  the currently selected location
     * @throws DatabaseNotInitializedException Simple exception that represents a nonexistent
     *                                         Database since it never got initialized
     */
    public Location getCurrentLocation() {
        return _currentLocation;
    }

    /**
     * Sets current location being used by the model
     *
     * @param location The current location that the model should use
     */
    public void setCurrentLocation(Location location) { _currentLocation = location; }

    /**
     * Return a location that has matching name.
     * This uses an O(n) linear search.
     *
     * @param name the name of the location to find
     * @return  the location with that name or the NullLocation if no such name exists.
     * @throws DatabaseNotInitializedException Simple exception that represents a nonexistent
     *                                         Database since it never got initialized
     */
    public Location getLocationByName(String name) throws DatabaseNotInitializedException {
        if (locationDatabase == null) {
            throw new DatabaseNotInitializedException();
        }
        return locationDatabase.getLocationByName(name);
    }

    /**
     * Set the current user logged in to the app.
     *
     * @param user User to be set
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

    /**
     * Gets all donation items unless the database is empty
     * If it is, then return a list with the null donation (sentinel value)
     * @return An ArrayList of donation items from the database
     * @throws DatabaseNotInitializedException Simple exception that represents a nonexistent
     *                                         Database since it never got initialized
     */
    public ArrayList<DonationItem> getDonations() throws DatabaseNotInitializedException{
        if (donationItemDatabase == null) {
            throw new DatabaseNotInitializedException();
        }
        return (ArrayList<DonationItem>) donationItemDatabase.getDonations();
    }

    /**
     * Adds a donation item to the donation item database.
     * Currently donations are added to location inventories in the donation item constructor.
     * That should likely be done by this method.
     *
     * @param donation item to be added
     * @return true if the item was added successfully and location database is updated
     */
    public boolean addDonationItem(DonationItem donation) {
        String name = donation.getName();
        if ((name.isEmpty())
                || (donation.getPrice() == 0)) {
            return false;
        } else {
            donationItemDatabase.addItem(donation);
            locationDatabase.updateLocation(donation);
            return true;
        }
    }

    /**
     * Exception thrown when app tries to retrieve from location database before it is initialized.
     */
    private class DatabaseNotInitializedException extends RuntimeException {

    }

}
