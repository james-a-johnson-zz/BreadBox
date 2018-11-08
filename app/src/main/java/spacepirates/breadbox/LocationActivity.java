package spacepirates.breadbox;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import spacepirates.breadbox.model.Location;
import spacepirates.breadbox.model.Model;

public class LocationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        final int i = intent.getIntExtra(
                "getString(R.string.pass_location_key)", -1);
        Log.d("intent","int i in location = " + i);

        setContentView(R.layout.activity_location);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        List<Location> locations = Model.getInstance().getLocations();
        final Location location = locations.get(i);

        Log.d("intent", "Location is: " + location.getName());

        TextView nameView = findViewById(R.id.location_name_location_activity);
        TextView typeView = findViewById(R.id.type_location_activity);
        TextView coordinatesView = findViewById(R.id.coordinates_location_activity);
        TextView addressView = findViewById(R.id.address_location_activity);
        TextView phoneNumberView = findViewById(R.id.phone_location_activity);
        RecyclerView donationsRV = findViewById(R.id.location_donations_list_rv);

        nameView.setText(location.getName());
        typeView.setText(location.getType());
        coordinatesView.setText("("+ location.getLatitude() + ", " + location.getLongitude() + ")");
        addressView.setText(location.getAddress());
        phoneNumberView.setText(location.getPhoneNumber());

        //Populates recycler view with cards containng
        //information about all the donations at a location.
        RecyclerView.Adapter donationsAdapter =
                new DonationItemRecyclerAdapter(location.getInventory());
        donationsRV.setAdapter(donationsAdapter);
        donationsRV.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, AddDonationItemActivity.class);
                //intent.putExtra(getString(R.string.pass_location_key), location);
                //intent.putExtra("location", location);
                //intent.putExtra("location", (Serializable)location);

                intent.putExtra("location_index", i);
                context.startActivity(intent);
            }
        });

    }

}
