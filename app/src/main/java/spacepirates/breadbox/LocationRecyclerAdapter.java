package spacepirates.breadbox;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import spacepirates.breadbox.model.Location;

import java.util.List;

public class LocationRecyclerAdapter
        extends RecyclerView.Adapter<LocationRecyclerAdapter.LocationViewHolder> {

    List<Location> locations;

    public static class LocationViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView locationName;
        TextView address;
        TextView locationType;

        LocationViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.location_card);
            locationName = (TextView)itemView.findViewById(R.id.location_name);
            address = (TextView)itemView.findViewById(R.id.address);
            locationType = (TextView) itemView.findViewById(R.id.location_type);
        }
    }

    LocationRecyclerAdapter(List<Location> locations){
        this.locations = locations;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public LocationViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.location_card, viewGroup, false);
        LocationViewHolder lvh = new LocationViewHolder(view);
        return lvh;
    }

    @Override
    public void onBindViewHolder(LocationViewHolder locationViewHolder, final int i) {
        Location loc = locations.get(i);
        locationViewHolder.locationName.setText(loc.getName());
        locationViewHolder.address.setText(loc.getAddress());
        locationViewHolder.locationType.setText("- " + loc.getType());

        locationViewHolder.cv.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, LocationActivity.class);

                    // send int i to locationActivity as location to view
                    // must reference location not send location copy
                    // so database may be edited in activity
                    //
                    // i : index in locations of current location.
                    // matches up with index in locationsDatabase as long as locationsDatabase
                    // matches 1 to 1 with the indexes in this recycler.
                    // might need a better implementation
                    //
                    // pass_location_key holds the key pair, must be referenced to pull location
                    //intent.putExtra(getString(R.string.pass_location_key), new Integer(i));
                Log.d("intent","int i in recycler = " + i);
                intent.putExtra("getString(R.string.pass_location_key)", new Integer(i));
                context.startActivity(intent);
            }
        }));
    }

    @Override
    public int getItemCount() {
        return locations.size();
    }
}