package spacepirates.breadbox.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;


public class DonationItemDatabase {
    private static List<DonationItem> database;

    private List<DonationItem> database;

    public DonationItemDatabase(){
        database = new ArrayList<>();

    }
    public static void addInventory(List<DonationItem> list) {
    public DonationItemDatabase(Context context) { initializeDatabase(context);}

    public void initializeDatabase(Context context) {
        //TODO Donation Database must read in values.
        database = new ArrayList<>();
    }

    public void addInventory(List<DonationItem> list) {
        database.addAll(list);
    }

    public static void add(DonationItem item) {
        database.add(item);
    }

    public static List<DonationItem> getDatabase(){
        return database;
    }

    public List<DonationItem> getDonations() {return database;}
}
