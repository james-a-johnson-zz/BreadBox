import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;

public static class LocationDatabase {

    private static ArrayList<Location> locations;

    public static void main(String[] args) {
        Scanner scan = new Scanner(new File("LocationData.csv"));
        ArrayList<String[]> lines = new ArrayList<>();
        while(scan.hasNextLine()) {
            lines.add(scan.getNextLine().split(","));
        }
        locations = new ArrayList<Location>();
        for (String[] arr : lines) {
            locations.add(new Location(arr));
        }
}