package spacepirates.breadbox.model;


import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

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
    private DonationItem theNullDonation = new DonationItem("No Donations Found", 0, Category.APPAREL, theNullLocation);

    /**
     * make a new model
     */
    private Model() {
        Log.d("Model", "Initialized Model, without context");
        locationDatabase = null;
        donationItemDatabase = null;
        _currentUser = nullUser;
    }

    private Model(Context context) {
        Log.d("Model", "Initialized Model, with context");
        locationDatabase = new LocationDatabase(context);
        donationItemDatabase = new DonationItemDatabase(context);
        _currentUser = nullUser;
    }

    public void initializeDatabases (Context context) {
        locationDatabase = new LocationDatabase(context);
        donationItemDatabase =  new DonationItemDatabase(context);
    }
    
    public ArrayList<Location> getLocations() throws DatabaseNotInitializedException {
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
    public ArrayList<DonationItem> filterDonationItems(Location location, Category category) {
                return null;
    }
    //TODO There should be filterDonationItem methods implmented for every way a donation should be filtered.

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
        //locationDatabase.add(Location);
        return true;
    }

    /**
     *
     * @return  the currently selected location
     */
    public Location getCurrentLocation() throws DatabaseNotInitializedException{
        if (locationDatabase == null) {
            throw new DatabaseNotInitializedException();
        }

        return _currentLocation;
    }

    public void setCurrentLocation(Location location) { _currentLocation = location; }

    /**
     * Return a location that has matching number.
     * This uses an O(n) linear search.
     *
     * @param number the number of the location to find
     * @return  the location with that number or the NullLocation if no such number exists.
     *
     */
    public Location getLocationByNumber (String number) throws DatabaseNotInitializedException{
        if (locationDatabase == null) {
            throw new DatabaseNotInitializedException();
        }
        for (Location l : locationDatabase.getLocations() ) {
            //TODO need some way to find a specific location index? number? name?
            //if (l.getNumber().equals(number)) return l;
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

        // If donationDatabsae is empty, return list with the null donation.
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
    }
    
    
    
    /**
     * Exception thrown when app tries to retrieve from location database before it is initialized.
     */
    private class DatabaseNotInitializedException extends RuntimeException {

    }

}
