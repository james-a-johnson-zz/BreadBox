package spacepirates.breadbox.model;

import java.util.ArrayList;
import java.util.List;


public class DonationItemDatabase {
    private static List<DonationItem> database;

    public DonationItemDatabase(){
        database = new ArrayList<>();

    }

    public static void addInventory(List<DonationItem> list) {
        database.addAll(list);
    }

    public static void add(DonationItem item) {
        database.add(item);
    }

    public static List<DonationItem> getDatabase(){
        return database;
    }

}
