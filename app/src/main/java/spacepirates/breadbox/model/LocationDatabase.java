package spacepirates.breadbox.model;

import android.content.Context;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class LocationDatabase {

    private ArrayList<Location> locations;

    private Context mContext;

    public LocationDatabase() {
        locations = new ArrayList<>();
    }

    public LocationDatabase(Context context) {
        mContext = context;

        locations = new ArrayList<Location>();
        try {
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
    }

    public void addLocation(String name, String type, String latitude, String longitude, String address, String phoneNumber) {
        locations.add(new Location(name, type, latitude, longitude, address, phoneNumber));
    }

    public ArrayList<Location> getLocations() {
        return locations;
    }

    public boolean removeLocation(Location l) {
        return locations.remove(l);
    }


}