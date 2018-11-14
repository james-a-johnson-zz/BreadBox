package spacepirates.breadbox;

import org.junit.*;
import static org.junit.Assert.*;

import spacepirates.breadbox.model.LocationDatabase;
import spacepirates.breadbox.model.Location;

import java.util.ArrayList;
import java.util.List;

public class GetLocationByNameTest {
    private final Location nullLocation = new Location.LocationBuilder("No Locations")
            .type("none")
            .latitude(0.0)
            .longitude(0.0)
            .address("Not Found")
            .phoneNumber("000-000-0000")
            .build();
    @Test
    public void NoLocationsReturnsNullLocation() {
        LocationDatabase db = new LocationDatabase(new ArrayList<Location>());
        assertEquals(nullLocation, db.getLocationByName("Alex"));
    }

    @Test
    public void LookForLocationNotInDatabase() {
        Location fun = new Location.LocationBuilder("No Locations")
                .type("none")
                .latitude(0.0)
                .longitude(0.0)
                .address("Not Found")
                .phoneNumber("000-000-0000")
                .build();
        ArrayList<Location> ls = new ArrayList<>();
        ls.add(fun);
        LocationDatabase db = new LocationDatabase(ls);
        assertEquals(db.getLocationByName("Boring"), nullLocation);
    }

    @Test
    public void FindLocationInListOfOne() {
        Location fun = new Location.LocationBuilder("No Locations")
                .type("none")
                .latitude(0.0)
                .longitude(0.0)
                .address("Not Found")
                .phoneNumber("000-000-0000")
                .build();
        ArrayList<Location> ls = new ArrayList<>();
        ls.add(fun);
        LocationDatabase db = new LocationDatabase(ls);
        assertEquals(db.getLocationByName("Fun Location"), fun);
    }

    @Test
    public void FindLocationInLongList() {
        ArrayList<Location> longList = new ArrayList<>();
        String name = "L";
        for (int i = 0; i < 100; i++, name += "L") {
            longList.add(new Location.LocationBuilder("No Locations")
                    .type("none")
                    .latitude(0.0)
                    .longitude(0.0)
                    .address("Not Found")
                    .phoneNumber("000-000-0000")
                    .build());
        }
        Location last = new Location.LocationBuilder("Last One")
                .type("none")
                .latitude(0.0)
                .longitude(0.0)
                .address("Not Found")
                .phoneNumber("000-000-0000")
                .build();
        longList.add(last);
        LocationDatabase db = new LocationDatabase(longList);
        assertEquals(last, db.getLocationByName("Last One"));
    }
}
