package spacepirates.breadbox.model;


import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


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
    
    public ArrayList<Location> getLocations() throws DatabaseNotInitializedException{
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
     * add a location to the app.  checks if the location is already entered
     *
     * uses O(n) linear search for course
     *
     * @param location  the course to be added
     * @return true if added, false if a duplicate
     */
    public boolean addLocation(Location location) throws DatabaseNotInitializedException{
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
     * Exception thrown when app tries to retrieve from location database before it is initialized.
     */
    public class DatabaseNotInitializedException extends RuntimeException {

    }

}
