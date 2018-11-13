package spacepirates.breadbox;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import spacepirates.breadbox.model.Location;
import spacepirates.breadbox.model.Model;

/**
 * Fragment activity that resides in the navigational view like location view and search activities
 * Takes the user to a map view of all locations where they can click to view names
 * and phone numbers of locations
 */
public class LocationMapActivity extends Fragment implements OnMapReadyCallback {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //activity_location_map is actually a fragment?
        View view = inflater.inflate(R.layout.activity_location_map, container, false);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment)
                getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        return view;
    }
    /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    */

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        final double ATL_LAT = 33.7490;
        final double ATL_LONG = -84.3880;
        final float ZOOM = 10.0f;

        Model model = Model.getInstance();
        List<Location> locations;

        locations = model.getLocations();

        for (Location location : locations) {
            LatLng pin = new LatLng(location.getLatitude(), location.getLongitude());
            googleMap.addMarker(new MarkerOptions()
                    .position(pin)
                    .title(location.getName())
                    .snippet("Phone: " + location.getPhoneNumber()));
        }

        // Add a marker in Atlanta and move the camera to it because Atlanta is where it's at
        LatLng atlanta = new LatLng(ATL_LAT, ATL_LONG);
//        mMap.addMarker(new MarkerOptions().position(atlanta).title("Marker in Atlanta"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(atlanta, ZOOM));

//        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
//            @Override
//            public boolean onMarkerClick(Marker marker) {
//
//            }
//        });
    }
}
