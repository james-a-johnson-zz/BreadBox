package spacepirates.breadbox;

import java.util.List;
import java.util.ArrayList;
import java.util.LocalDate;

public class History {
    private ArrayList<LocalDate> datesMoved;
    private ArrayList<Location> Locations;

    public History(Location location) {
        this.datesMoved.add(LocalDate.now());
        Locations.add(location);

    }

    public void moveLocations(Location L) {
        this.datesMoved.add(LocalDate.now());
        //do we really need the WHOLE date? we could just save day-month-year
        Locations.add(L);
    }

    public LocalDate getDateArrived(Location L) {
        int index = Locations.lastIndexOf(L);
        return datesMoved.get(index);
    }

    public Location getLocationOnDate(LocalDate date) {
        if (date.compareto(datesMoved(0)) < 0 ) {
            throw new IllegalArgumentException("The object was not yet"
                + "donated at this time");
        }
        if (date.compareto(datesMoved(datesMoved.size()-1)) > 0 ) {
            throw new IllegalArgumentException("Date checked cannot be in the"
                + "future");
        } //the later the greater
        for (int i = 0; i < datesMoved.size(); i++){
            if (date.compareto(datesMoved(i)) < 0){
                return Locations(i-1);
            }
        }
        return Locations(i-1);
    }

    public String[] getLocationHistory() {
        String[] locHistory = new String[Locations.size()];
        for(i = 0; i < Locations.size(); i++) {
            locHistory[i] = "Arrived at" + Locations(i).getName
                + "on" + datesMoved(i).toString;
            //make a getter method for location database that takes
            //in a location name

        }
        return locHistory;
    }

    public String toString(){
        String[] hist = this.getLocationHistory();
        String histoPrint = "";
        for(String h : hist){
            histoPrint = histoPrint + h + "\n";
        }
        return histoPrint;
    }

}