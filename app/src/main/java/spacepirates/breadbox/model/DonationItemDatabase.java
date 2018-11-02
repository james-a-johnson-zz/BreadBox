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

public class DonationItemDatabase {
    private List<DonationItem> database;
    private DatabaseReference db;

    public DonationItemDatabase() {
        database = new ArrayList<>();

        db = FirebaseDatabase.getInstance().getReference("donations");
        initializeDatabase();
    }

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

    public void addInventory(List<DonationItem> list) {
        for (DonationItem di : list)
            this.addItem(di);
    }

    public void addItem(DonationItem item) {
        db.child(item.getId()).setValue(item);
        database.add(item);
    }

    public List<DonationItem> getDatabase() {
        return database;
    }

    public List<DonationItem> getDonations() {
        Log.d("DonationDB", "Size is: " + database.size());
        return database;
    }

    public Queue<DonationItem> getItemsByCategory(List<DonationItem> list, Category cat) {
        PriorityQueue<DonationItem> ret = new PriorityQueue<DonationItem>();
        for (DonationItem d : list) {
            if (d.getCategory() == cat) {
                ret.add(d);
            }
        }
        return ret;
    }

    public void removeItem(DonationItem di) {
        db.child(di.getId()).removeValue();
        database.remove(di);
    }

    public Queue<DonationItem> getItemsByName(List<DonationItem> list, final String name) {
        /*
            PriorityQueue<DonationItem> ret = new PriorityQueue<DonationItem>(list.size(),
                (DonationItem a, DonationItem b) -> a.getName().compareTo(name)
                - b.getName().compareTo(name));
                */
        PriorityQueue<DonationItem> ret = new PriorityQueue<>(list.size(), new Comparator<DonationItem>() {
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
