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

        ArrayList locations = new ArrayList();

        setContentView(R.layout.activity_locations);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        LocationCardAdapter adapter = new LocationCardAdapter(locations);
        recyclerView.setAdapter(adapter);

    }


    public class LocationCardAdapter extends RecyclerView.Adapter<LocationCardAdapter.LocationViewHolder> {
        List<Location> locations;

        public class LocationViewHolder extends RecyclerView.ViewHolder {
            CardView cv;
            TextView locationName;
            TextView address;

            LocationViewHolder(View itemView) {
                super(itemView);
                cv = (CardView) itemView.findViewById(R.id.location_card);
                locationName = (TextView) itemView.findViewById(R.id.location_name);
                address = (TextView) itemView.findViewById(R.id.address);
            }

        }

        @Override
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
            //LocationViewHolder.locationName.setText(((Location)locations.get(i)).name);
        }


    }



}
