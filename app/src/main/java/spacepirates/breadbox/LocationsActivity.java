package spacepirates.breadbox;

import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class LocationsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArrayList locations;

        setContentView(R.layout.activity_locations);
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

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        LocationCardAdapter adapter = new LocationCardAdapter(locations);
        recyclerView.setAdapter(adapter);

    }


    public class LocationCardAdapter extends RecyclerView.Adapter<LocationCardAdapter.LocationViewHolder> {
        List locations;

        public static class LocationViewHolder extends RecyclerView.ViewHolder {
            CardView cv;
            TextView personName;

            LocationViewHolder(View itemView) {
                super(itemView);
                cv = (CardView) itemView.findViewById(R.id.locationCard);
                personName = (TextView) itemView.findViewById(R.id.person_name);
            }

        }

        public int getItemCount() {
            return locations.size();
        }

        public LocationCardAdapter(List<Location> locations) {
            this.locations = locations;
        }

        @Override
        public LocationViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_location_card_fragement, viewGroup, false);
            LocationViewHolder lvh = new LocationViewHolder(v);
            return lvh;
        }

        @Override
        public void onBindViewHolder(LocationViewHolder locationViewHolder, int i) {
            LocationViewHolder.personName.setText(locations.get(i).name);
        }


    }



}
