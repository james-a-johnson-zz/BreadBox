package spacepirates.breadbox.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;


public class DonationItemDatabase {
    private List<DonationItem> database;

    public DonationItemDatabase() {
        database = new ArrayList<>();

    }

    public DonationItemDatabase(Context context) {
            initializeDatabase(context);
        }

        public void initializeDatabase (Context context){
            //TODO Donation Database must read in values.
            database = new ArrayList<>();
        }

        public void addInventory (List < DonationItem > list) {
            database.addAll(list);
        }

        public void addItem(DonationItem item){
            database.add(item);
        }

        public List<DonationItem> getDatabase () {
            return database;
        }

        public List<DonationItem> getDonations () {
            return database;
        }

        public Queue<DonationItem> getItemsByCategory(List<DonationItem> list, Category cat) {
            PriorityQueue<DonationItem> ret = new PriorityQueue<DonationItem>();
            for (DonationItem d: list) {
                if (d.getCategory() == cat) {
                    ret.add(d);
                }
            }
            return ret;
        }

        public Queue<DonationItem> getItemsByName(List<DonationItem> list, String name) {
            PriorityQueue<DonationItem> ret = new PriorityQueue<DonationItem>(list.size(),
                (DonationItem a, DonationItem b) -> a.getName().compareTo(name)
                - b.getName().compareTo(name));
//            PriorityQueue<DonationItem>ret = new PriorityQueue<>(list.size(), new Comparator<DonationItem>() {
//                @Override
//                public int compare(DonationItem donationItem, DonationItem t1) {
//                    return donationItem.getName().compareTo(name) - (t1.getName().compareTo(name));
//                }
//            });
            for (DonationItem d: list) {
                ret.add(d);
            }
            return ret;
        }

}
