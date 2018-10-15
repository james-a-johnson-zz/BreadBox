package spacepirates.breadbox.model;


import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

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

    /** Null Object pattern, returned when no course is found */
    private final Location theNullLocation = new Location("No Locations", "none", 0, 0, "Not Found", "000-000-0000");


    /**
     * make a new model
     */
    private Model() {
        Log.d("Model", "Initialized Model, without context");
        locationDatabase = null;
        _currentUser = nullUser;
    }

    private Model(Context context) {
        Log.d("Model", "Initialized Model, with context");
        locationDatabase = new LocationDatabase(context);
        _currentUser = nullUser;
    }

    public void initializeLocationDatabase (Context context) {
        locationDatabase = new LocationDatabase(context);
    }
    
    public ArrayList<Location> getLocations() throws LocationDatabaseNotInitializedException{
        if (locationDatabase == null) {
            throw new LocationDatabaseNotInitializedException();
        }
        return locationDatabase.getLocations(); }

    /**
     * add a location to the app.  checks if the location is already entered
     *
     * uses O(n) linear search for course
     *
     * @param location  the course to be added
     * @return true if added, false if a duplicate
     */
    public boolean addLocation(Location location) throws LocationDatabaseNotInitializedException{
        if (locationDatabase == null) {
            throw new LocationDatabaseNotInitializedException();
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
    public Location getCurrentLocation() throws LocationDatabaseNotInitializedException{
        if (locationDatabase == null) {
            throw new LocationDatabaseNotInitializedException();
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
    public Location getLocationByNumber (String number) throws LocationDatabaseNotInitializedException{
        if (locationDatabase == null) {
            throw new LocationDatabaseNotInitializedException();
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

    /**
     * Exception thrown when app tries to retrieve from location database before it is initialized.
     */
    public class LocationDatabaseNotInitializedException extends RuntimeException {

    }

}
