package spacepirates.breadbox;


import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import spacepirates.breadbox.model.Location;

import java.util.List;

public class LocationRecyclerAdapter extends RecyclerView.Adapter<LocationRecyclerAdapter.LocationViewHolder> {

    List<Location> locations;

    public static class LocationViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView locationName;
        TextView address;

        LocationViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.location_card);
            locationName = (TextView)itemView.findViewById(R.id.location_name);
            address = (TextView)itemView.findViewById(R.id.address);
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
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.location_card, viewGroup, false);
        LocationViewHolder lvh = new LocationViewHolder(view);
        return lvh;
    }

    @Override
    public void onBindViewHolder(LocationViewHolder locationViewHolder, int i) {
        locationViewHolder.locationName.setText(locations.get(i).getName());
        locationViewHolder.address.setText(locations.get(i).getAddress());
    }

    @Override
    public int getItemCount() {
        return locations.size();
    }
}