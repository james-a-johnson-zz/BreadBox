package spacepirates.breadbox;

import java.util.List;
import java.util.ArrayList;
import java.util.LocalDate;

public class History{
    private ArrayList<LocalDate> datesMoved;
    private ArrayList<Location> Locations;

    public History(Location location){
        this.datesMoved.add(LocalDate.now());
        Locations.add(location);

    }

    public void moveLocations(Location L){
        this.datesMoved.add(LocalDate.now());
        Locations.add(L);
    }

}