package spacepirates.breadbox;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import spacepirates.breadbox.model.Location;
import spacepirates.breadbox.model.Model;

public class LocationsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Model model = Model.getInstance();
        ArrayList<Location> locations;

        // Checks that location database is initialized and populates it if it is not.
        try {
            locations = model.getLocations();
        } catch (Exception e) {
            model.initializeDatabases(getApplicationContext());
            locations = model.getLocations();
        }

        int size = locations.size();
        Log.d("Locations", "size locations list: " + size);

        setContentView(R.layout.activity_locations);

        RecyclerView recyclerView = findViewById(R.id.locations_recycler);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        LocationRecyclerAdapter adapter = new LocationRecyclerAdapter(locations);
        recyclerView.setAdapter(adapter);

        Button mapDisp = findViewById(R.id.displayMapButton);
        mapDisp.setText("Display Map");
        mapDisp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Model model = Model.getInstance();
                Context context = view.getContext();
                Intent intent = new Intent(context, LocationMapActivity.class);
                context.startActivity(intent);
            }
        });

    }




}
