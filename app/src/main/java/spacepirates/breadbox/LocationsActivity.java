package spacepirates.breadbox;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;

import java.util.ArrayList;

import spacepirates.breadbox.model.Location;
import spacepirates.breadbox.model.Model;

public class LocationsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Model model = Model.getInstance();
        ArrayList<Location> locations;

        final DrawerLayout mDrawerLayout;
        mDrawerLayout = findViewById(R.id.drawer_layout);
        /**
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView = new NavigationView(getApplicationContext());
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                // set item as selected to persist highlight
                menuItem.setChecked(true);
                // close drawer when item is tapped
                mDrawerLayout.closeDrawers();

                int selected = menuItem.getItemId();
                Intent intent = null;
                Context context = getApplicationContext();
                switch(selected) {
                    case R.id.drawer_menu_locations:
                        intent = new Intent(getApplicationContext(), LocationsActivity.class);
                        break;
                    case R.id.drawer_menu_logout:
                        //TODO need a logout acitivity / method in model
                        intent = new Intent(getApplicationContext(), LoginActivity.class);
                        break;
                }
                //or not current activity?
                if (intent != null) {
                    context.startActivity(intent);
                }
                return true;
            }
        });
         **/


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

    }




}
