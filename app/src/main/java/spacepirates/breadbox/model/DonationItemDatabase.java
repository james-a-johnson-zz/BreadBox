package spacepirates.breadbox.model;

import java.util.ArrayList;
import java.util.List;


public class DonationItemDatabase {
    List<DonationItem> database;

    public DonationItemDatabase(){
        database = new ArrayList<>();
    }

    public void addInventory(List<DonationItem> list) {
        database.addAll(list);
    }

    public void addItem(DonationItem item) {
        database.add(item);
    }


}
