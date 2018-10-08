package spacepirates.breadbox;

import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;

public class LocationDatabase {

    private static ArrayList<Location> locations;

    public static void main(String[] args) {
        try {
            Scanner scan = new Scanner(new File("LocationData.csv"));
            scan.nextLine(); //Skipping line that indicates the format of the CSV
            ArrayList<String[]> lines = new ArrayList<>();
            while(scan.hasNextLine()) {
                lines.add(scan.nextLine().split(","));
            }
            locations = new ArrayList<Location>();
            for (String[] arr : lines) {
                if (arr.length == 11) {
                    locations.add(new Location(arr));
                }
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

    public static void addLocation(String name, String type, String latitude, String longitude, String address, String phoneNumber) {
        locations.add(new Location(name, type, latitude, longitude, address, phoneNumber));
    }

    public static ArrayList<Location> getLocations() {
        return locations;
    }

    public static Location removeLocation(Location l) {
        return locations.remove(l);
    }


}