package spacepirates.breadbox;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import spacepirates.breadbox.model.Location;
import spacepirates.breadbox.model.LocationDatabase;

import java.util.ArrayList;

public class LocationsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LocationDatabase l = new LocationDatabase(LocationsActivity.this);
        ArrayList locations = l.getLocations();

        setContentView(R.layout.activity_locations);


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.locations_recycler);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        LocationRecyclerAdapter adapter = new LocationRecyclerAdapter(locations);
        recyclerView.setAdapter(adapter);

    }




}
