package spacepirates.breadbox;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import spacepirates.breadbox.model.Location;

import java.util.ArrayList;

public class LocationsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArrayList locations = new ArrayList();
        locations.add(new Location("Joe's", "", 0,0,"Htine", ""));
        locations.add(new Location("Yo momma's", "", 0, 0, "A-town", ""));

        setContentView(R.layout.activity_locations);


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.locations_recycler);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        LocationRecyclerAdapter adapter = new LocationRecyclerAdapter(locations);
        recyclerView.setAdapter(adapter);

    }




}
