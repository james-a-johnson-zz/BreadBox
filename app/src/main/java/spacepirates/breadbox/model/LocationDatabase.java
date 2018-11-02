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

    //TODO It would be convenient if the database could be initialized without context from an Activity
    //public LocationDatabase() {}

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

        return null;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public boolean removeLocation(Location l) {
        db.child(l.getAddress()).removeValue();
        return locations.remove(l);
    }

    public void updateLocation(Location l) {
        db.child(l.getAddress()).setValue(l);
    }

    public void updateAllLocations() {
        for (Location l : locations)
            this.updateLocation(l);
    }

}
