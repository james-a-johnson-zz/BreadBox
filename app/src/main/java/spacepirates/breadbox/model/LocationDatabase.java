package spacepirates.breadbox.model;

import android.content.Context;
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

    private Context mContext;

    //TODO It would be convenient if the database could be initialized without context from an Activity
    //public LocationDatabase() {}

    public LocationDatabase(Context context) {
        mContext = context;
        db = FirebaseDatabase.getInstance().getReference("locations");
        locations = initializeLocations(context);
    }

    private List<Location> initializeLocations(Context context) {
        Log.d("LocationDatabase", "Initializing Database.");
        final List<Location> locations = new ArrayList<Location>();

        ValueEventListener initLocations = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists()) {
                    throw new DatabaseException("Database could not initialize");
                }
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    locations.add(ds.getValue(Location.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                throw new DatabaseException("Database could not initialize");
            }
        };

        db.addListenerForSingleValueEvent(initLocations);

        return locations;
    }

    public void addLocation(String name, String type, String latitude, String longitude, String address, String phoneNumber) {
        Location l = new Location(name, type, latitude, longitude, address, phoneNumber);
        this.addLocation(l);
    }

    public void addLocation(Location location) {
        locations.add(location);
        db.child(location.getAddress()).setValue(location);
    }

    public List<Location> getLocations() {
        return locations;
    }

    public boolean removeLocation(Location l) {
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
