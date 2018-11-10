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
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Database of all donation items for the company
 * Uses firebase to store all of the items
 */
public class DonationItemDatabase {
    private List<DonationItem> database;
    private DatabaseReference db;

    /** Null Donation pattern, returned when no donations are found.
     *  Current default category is apparel. Fails curing run if category is null.
     */
    private final DonationItem theNullDonation = new DonationItem("No Donations Found", 0, Category.APPAREL);

    /**
     * Constructor that initializes the database
     * No parameters are necessary since all modifications are handled in the initialization method
     */
    public DonationItemDatabase() {
        database = new ArrayList<>();

        db = FirebaseDatabase.getInstance().getReference("donations");
        initializeDatabase();
    }

    /**
     * Method to handle technicalities of initializing database on firebase's end
     * Includes adding every element of data as well as the case where data does not exist
     */
    public void initializeDatabase() {
        ValueEventListener addItems = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists())
                    throw new DatabaseException("Could not load donations");

                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    database.add(child.getValue(DonationItem.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                throw new DatabaseException("Could not load donations");
            }
        };

        db.addListenerForSingleValueEvent(addItems);
    }

    /**
     * Method for quick adding of an entire inventory if necessary
     * @param list List of items (inventory) from a location
     */
    public void addInventory(List<DonationItem> list) {
        for (DonationItem di : list)
            this.addItem(di);
    }

    /**
     * Adds a single donation item to the database
     * @param item The item to be added
     */
    public void addItem(DonationItem item) {
        db.child(item.getId()).setValue(item);
        database.add(item);
    }

    /**
     * Gets all donation items stored in the database
     * @return A list of donation items
     */
    public List<DonationItem> getDatabase() {
        return database;
    }

    /**
     * Identical method to getDatabase() but also includes log messaging for testing
     * @return List of donation items
     */
    public List<DonationItem> getDonations() {
        // If donationDatabase is empty, return list with the null donation.
        if (database.size() == 0) {
            ArrayList<DonationItem> noDonations = new ArrayList<>();
            noDonations.add(theNullDonation);
            return noDonations;
        }
        return database;
    }

    /**
     * Filtering method that uses a queue to filter by category
     * @param list      List of items to be filtered
     * @param cat       Category that returned items will belong to
     * @return          A queue of all items that fall under the given category
     */
    public Queue<DonationItem> getItemsByCategory(List<DonationItem> list, Category cat) {
        PriorityQueue<DonationItem> ret = new PriorityQueue<>();
        for (DonationItem d : list) {
            if (d.getCategory() == cat) {
                ret.add(d);
            }
        }
        return ret;
    }

    /**
     * Removes a single item from the database
     * @param di Item to remove
     */
    public void removeItem(DonationItem di) {
        db.child(di.getId()).removeValue();
        database.remove(di);
    }

    /**
     * Similar to getItemsByCategory() but instead filters by the items' name
     * @param list      List of items to filter
     * @param name      Name to get item(s) by
     * @return          A filtered list of items that have similar names. Closer names appear sooner
     */
    public Queue<DonationItem> getItemsByName(List<DonationItem> list, final String name) {
        /*
            PriorityQueue<DonationItem> ret = new PriorityQueue<DonationItem>(list.size(),
                (DonationItem a, DonationItem b) -> a.getName().compareTo(name)
                - b.getName().compareTo(name));
                */
        PriorityQueue<DonationItem> ret = new PriorityQueue<>(list.size(),
                new Comparator<DonationItem>() {
            @Override
            public int compare(DonationItem donationItem, DonationItem t1) {
                return donationItem.getName().compareTo(name) - (t1.getName().compareTo(name));
            }
        });
            for (DonationItem d: list) {
                ret.add(d);
            }
            return ret;
        }
}
