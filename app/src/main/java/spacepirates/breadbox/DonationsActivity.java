package spacepirates.breadbox;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

import spacepirates.breadbox.model.DonationItem;
import spacepirates.breadbox.model.Model;

/**
 * Activity that allows a user to add a donation item to the system
 */
public class DonationsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Model model = Model.getInstance();
        ArrayList<DonationItem> donations = model.getDonations();

        int size = donations.size();
        Log.d("Donations", "size donations list: " + size);

        setContentView(R.layout.activity_donations);

        RecyclerView recyclerView = findViewById(R.id.donations_rv);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager linearLayoutManager =
                new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        DonationItemRecyclerAdapter adapter = new DonationItemRecyclerAdapter(donations);
        recyclerView.setAdapter(adapter);
    }


}
