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

public class LocationDatabase {

    private List<Location> locations;
    private DatabaseReference db;

    /** Null Location pattern, returned when no course is found */
    private final Location theNullLocation = new Location("No Locations", "none", 0, 0, "Not Found", "000-000-0000");

    public LocationDatabase() {
        db = FirebaseDatabase.getInstance().getReference("locations");
        this.locations = new ArrayList<>();
        initializeLocations();
    }

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

    public void addLocation(String name, String type, String latitude, String longitude, String address, String phoneNumber) {
        Location l = new Location(name, type, latitude, longitude, address, phoneNumber);
        this.addLocation(l);
    }

    public void addLocation(Location location) {
        locations.add(location);
        db.child(location.getAddress()).setValue(location);
    }

    public Location getLocationByAddress(String address) {
        for (Location l : locations)
            if (l.getAddress().equals(address))
                return l;

        return theNullLocation;
    }

    public Location getLocationByName(String name) {
        for (Location l : locations ) {
            //TODO need some way to find a specific location index? number? name?
            if (l.getName().equals(name)) return l;
        }
        return theNullLocation;
    }

    public List<Location> getLocations() {
        // If locationDatabsae is empty, return list with the null location.
        if (locations.size() == 0) {
            ArrayList<Location> noLocations = new ArrayList<>();
            noLocations.add(theNullLocation);
            return noLocations;
        }
        return locations;
    }

    public boolean removeLocation(Location l) {
        db.child(l.getAddress()).removeValue();
        return locations.remove(l);
    }

    public void updateLocation(Location l) {
        db.child(l.getAddress()).setValue(l);
    }

    public void updateLocation(DonationItem d) {
        Location l = getLocationByAddress(d.getAddress());
        l.addItem(d);
        this.updateLocation(l);
    }

    public void updateAllLocations() {
        for (Location l : locations)
            this.updateLocation(l);
    }

}
