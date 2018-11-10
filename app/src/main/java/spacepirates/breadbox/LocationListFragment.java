package spacepirates.breadbox;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

import spacepirates.breadbox.model.Location;
import spacepirates.breadbox.model.Model;



public class LocationListFragment extends Fragment {
    RecyclerView recyclerView;

    /**
     @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.fragment_location_list);

        List<Location> locations = Model.getInstance().getLocations();

        recyclerView = findViewById(R.id.locations_recycler);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager linearLayoutManager =
                                    new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        LocationRecyclerAdapter adapter = new LocationRecyclerAdapter(locations);
        recyclerView.setAdapter(adapter);
    }
    **/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_location_list, container, false);
        List<Location> locations = Model.getInstance().getLocations();

        recyclerView = view.findViewById(R.id.locations_recycler);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        LocationRecyclerAdapter adapter = new LocationRecyclerAdapter(locations);
        recyclerView.setAdapter(adapter);

        Button mapButton = view.findViewById(R.id.map_activity_button);
        mapButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, LocationMapActivity.class);
                context.startActivity(intent);
            }
        });

        return view;
    }
}
