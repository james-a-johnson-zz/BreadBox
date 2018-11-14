package spacepirates.breadbox.model;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Database of all donation items for the company
 * Uses Fire base to store all of the items
 */
public class DonationItemDatabase {
    private final List<DonationItem> database;
    private final DatabaseReference db;

    /** Null Donation pattern, returned when no donations are found.
     *  Current default category is apparel. Fails curing run if category is null.
     */
    private final DonationItem theNullDonation = new DonationItem
            .DonationItemBuilder("No Donations Found").
            price(0.0)
            .category(Category.APPAREL)
            .build();

    /**
     * Constructor that initializes the database
     * No parameters are necessary since all modifications are handled in the initialization method
     */
    public DonationItemDatabase() {
        database = new ArrayList<>();

        FirebaseDatabase firebaseInstance = FirebaseDatabase.getInstance();
        db = firebaseInstance.getReference("donations");
        initializeDatabase();
    }

    /**
     * Constructor that initializes the database
     * @param list the list of initial donationItems (for testing purposes)
     */
    public DonationItemDatabase(List<DonationItem> list) {
        database = list;
        db = null;
    }


    /**
     * Method to handle technicalities of initializing database on Fire base's end
     * Includes adding every element of data as well as the case where data does not exist
     */
    private void initializeDatabase() {
        ValueEventListener addItems = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists()) {
                    throw new DatabaseException("Could not load donations");
                }

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
    public void addInventory(Iterable<DonationItem> list) {
        for (DonationItem di : list) {
            this.addItem(di);
        }
    }

    /**
     * Adds a single donation item to the database
     * @param item The item to be added
     */
    public void addItem(DonationItem item) {
        DatabaseReference reference = db.child(item.getId());
        reference.setValue(item);
        database.add(item);
    }

    /**
     * Gets all donation items stored in the database
     * @return A list of donation items
     */
    public List<DonationItem> getDatabase() {
        return Collections.unmodifiableList(database);
    }

    /**
     * Identical method to getDatabase() but also includes log messaging for testing
     * @return List of donation items
     */
    public List<DonationItem> getDonations() {
        // If donationDatabase is empty, return list with the null donation.
        if (database.isEmpty()) {
            List<DonationItem> noDonations = new ArrayList<>();
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
    public Queue<DonationItem> getItemsByCategory(Iterable<DonationItem> list, Category cat) {
        Queue<DonationItem> ret = new PriorityQueue<>();
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
        DatabaseReference reference = db.child(di.getId());
        reference.removeValue();
        database.remove(di);
    }

    /**
     * Similar to getItemsByCategory() but instead filters by the items' name
     * @param list      List of items to filter
     * @param name      Name to get item(s) by
     * @return          A filtered list of items that have similar names. Closer names appear sooner
     */
    public Queue<DonationItem> getItemsByName(Collection<DonationItem> list, final String name) {
        /*
            PriorityQueue<DonationItem> ret = new PriorityQueue<DonationItem>(list.size(),
                (DonationItem a, DonationItem b) -> a.getName().compareTo(name)
                - b.getName().compareTo(name));
                */
        Queue<DonationItem> ret = new PriorityQueue<>(list.size(),
                new Comparator<DonationItem>() {
            @Override
            public int compare(DonationItem donationItem, DonationItem t1) {
                String itemName = donationItem.getName();
                String t1Name = t1.getName();
                return itemName.compareTo(name) - (t1Name.compareTo(name));
            }
        });

        ret.addAll(list);
        return ret;
        }
}
