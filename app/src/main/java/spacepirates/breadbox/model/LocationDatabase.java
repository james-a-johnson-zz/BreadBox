package spacepirates.breadbox.model;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class LocationDatabase{

    private ArrayList<Location> locations;

    private Context mContext;

    //TODO It would be convenient if the database could be initialized without context from an Activity
    //public LocationDatabase() {}

    public LocationDatabase(Context context) {
        mContext = context;
        locations = initializeLocations(context);
    }

    private ArrayList<Location> initializeLocations(Context context) {
        Log.d("LocationDatabase", "Initializing Database.");
        ArrayList<Location> locations = new ArrayList<Location>();
        try {

            //TODO Find context independent way to load csv. Safer for initializing model
            //working on a way to read file that isn't dependent on context
            /**
            File file = null;
            try {
                file = new File("BreadBox.app.src.main.assets.LocationData.csv");
            } catch (Exception e) {
                Log.e("LocationDatbase: ", "Failed to read file");
            }
            InputStream is = new FileInputStream(file);
            */

            InputStream is = mContext.getAssets().open("LocationData.csv");


            BufferedReader read = new BufferedReader(new InputStreamReader(is));
            read.readLine(); //Skipping line that indicates the format of the CSV
            ArrayList<String[]> lines = new ArrayList<>();
            String line;
            while((line = read.readLine()) != null) {
                lines.add(line.split(","));
            }
            for (String[] arr : lines) {
                locations.add(new Location(arr));
            }

            addLocation("Test Store", "Store", "45.5", "0", "1111 Fake Street", "(555) 555 - 5555");

            for (Location l : locations) {
                System.out.println(l);
            }
        } catch(Exception e) {
            System.out.println(e);
            System.out.println("Hit an exception");
        }
        Log.d("Location Database", "locations size: " + locations.size());
        return locations;
    }

    public void addLocation(String name, String type, String latitude, String longitude, String address, String phoneNumber) {
        locations.add(new Location(name, type, latitude, longitude, address, phoneNumber));
    }

    public void addLocation(Location location) {
        locations.add(location);
    }

    public void addLocation(Location location, DatabaseReference locReference) {
        locations.add(location);
        locReference.child(location.getAddress()).setValue(location);
    }

    public ArrayList<Location> getLocations() {
        return locations;
    }

    public boolean removeLocation(Location l) {
        return locations.remove(l);
    }


}
