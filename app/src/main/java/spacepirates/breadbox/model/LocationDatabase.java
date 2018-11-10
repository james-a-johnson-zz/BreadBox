package spacepirates.breadbox.model;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Like DonationItemDatabase, this is a DB for all of the company locations
 * Also uses Firebase to externally store data
 */
public class LocationDatabase {

    private List<Location> locations;
    private DatabaseReference db;

    /** Null Location pattern, returned when no course is found */
    private final Location theNullLocation = new Location("No Locations", "none", 0, 0, "Not Found", "000-000-0000");
    /**
     * Initializes the database
     * Location additions are handled in initializeLocations
     */
    public LocationDatabase() {
        db = FirebaseDatabase.getInstance().getReference("locations");
        this.locations = new ArrayList<>();
        initializeLocations();
    }

    /**
     * Puts all locations into the database as packages of data
     * Handles cases where location data is invalid or does not exist
     */
    private void initializeLocations() {
        Log.d("LocationDB", "Initializing Database.");
        final List<Location> locations = new ArrayList<Location>();

        ValueEventListener initLocations = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists()) {
                    throw new DatabaseException("Database could not initialize");
                }
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Location curr = ds.getValue(Location.class);
                    Log.d("LocationDB", "Received: " + curr.getAddress());
                    locations.add(curr);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                throw new DatabaseException("Database could not initialize");
            }
        };

        db.addListenerForSingleValueEvent(initLocations);
        Log.d("LocationDB", "Size: " + locations.size());
        this.locations = locations;
    }

    /**
     * Add a location to the database.
     * Includes all necessary parameters for generating a new location
     * @param name          The name of the location
     * @param type          A location's type (store, warehouse, etc...)
     * @param latitude      Latitude position
     * @param longitude     Longitude position. Both of these are used for the map
     * @param address       Street address
     * @param phoneNumber   Phone included for contacting the location
     */
    public void addLocation(String name, String type, String latitude, String longitude,
                            String address, String phoneNumber) {
        Location l = new Location(name, type, latitude, longitude, address, phoneNumber);
        this.addLocation(l);
    }

    /**
     * Add an already existing location to the database
     *
     * @param location Location to be added
     * @return Boolean if location successfully added
     */
    public boolean addLocation(Location location) {
        if (location == null)
            return false;
        if (locations.contains(location))
            return false;
        locations.add(location);
        db.child(location.getAddress()).setValue(location);
        return true;
    }

    /**
     * Find a location in the database by its address
     * @param address The key address in the search
     * @return The location if it is found, null otherwise
     */
    public Location getLocationByAddress(String address) {
        for (Location l : locations)
            if (l.getAddress().equals(address))
                return l;

        return theNullLocation;
    }

    /**
     * @return All locations being stored in the database
     */
    public List<Location> getLocations() {
        if (locations.size() == 0) {
            List<Location> none = new ArrayList<>();
            none.add(theNullLocation);
            return none;
        }
        return locations;
    }

    /**
     * Removes a location from the database
     * @param l Location to be removed
     * @return True if the operation was successful, false otherwise
     */
    public boolean removeLocation(Location l) {
        db.child(l.getAddress()).removeValue();
        return locations.remove(l);
    }

    /**
     * Update a location's data
     * @param l A given location with the data to update
     */
    public void updateLocation(Location l) {
        db.child(l.getAddress()).setValue(l);
    }

    /**
     * Updates a location to now include the given donation item
     *
     * @param di The donation item to add
     */
    public void updateLocation(DonationItem di) {
        Location l = this.getLocationByAddress(di.getAddress());
        l.addItem(di);
        updateLocation(l);
    }

    /**
     * Performs the above method on all locations in database
     */
    public void updateAllLocations() {
        for (Location l : locations)
            this.updateLocation(l);
    }

    /**
     * Finds a location by given name
     *
     * @param name Name of location to find
     * @return Location with given name or null location
     */
    public Location getLocationByName(String name) {
        for (Location l : locations)
            if (name.equals(l.getName()))
                return l;
        return theNullLocation;
    }
}
