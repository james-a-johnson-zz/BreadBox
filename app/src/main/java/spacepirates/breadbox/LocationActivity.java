package spacepirates.breadbox;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import spacepirates.breadbox.model.Location;
import spacepirates.breadbox.model.LocationDatabase;
import spacepirates.breadbox.model.Model;

public class LocationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //TODO locations not displaying properly, because model not initialized until after app arrives at locations. Model must be initialized in the handshake before the call

        Intent intent = getIntent();
        int i = intent.getIntExtra("getString(R.string.pass_location_key)", -1);
        Log.d("intent","int i in location = " + i);

        setContentView(R.layout.activity_location);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ArrayList<Location> locations = null;
        try {
            locations = Model.getInstance().getLocations();
        } catch (Exception e) {
            Model.getInstance().initializeLocationDatabase(getApplicationContext());
        }
        Location location = (Location) locations.get(i);

        Log.d("intent", "Location is: " + location.getName());

        TextView nameView = (TextView) findViewById(R.id.location_name_location_activity);
        TextView typeView = (TextView) findViewById(R.id.type_location_activity);
        TextView coordinatesView = (TextView) findViewById(R.id.coordinates_location_activity);
        TextView addressView = (TextView) findViewById(R.id.address_location_activity);
        TextView phoneNumberView = (TextView) findViewById(R.id.phone_location_activity);

        nameView.setText(location.getName());
        typeView.setText(location.getType());
        coordinatesView.setText("("+ location.getLatitude() + ", " + location.getLongitude() + ")");
        addressView.setText(location.getAddress());
        phoneNumberView.setText(location.getPhoneNumber());

    }

}
