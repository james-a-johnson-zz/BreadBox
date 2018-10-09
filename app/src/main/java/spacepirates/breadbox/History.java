package spacepirates.breadbox;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

public class History {
    private ArrayList<LocalDate> datesMoved;
    private ArrayList<Location> Locations;
    private LocalDate sellDate;

    public History(Location location) {
        this.datesMoved.add(this.getDay(LocalDate.now()));
        Locations.add(location);

    }

    public void moveLocations(Location L) {
        this.datesMoved.add(this.getDay(LocalDate.now()));
        //do we really need the WHOLE date? we could just save day-month-year
        Locations.add(L);
    }

    public void setLastLocation(Location L) {
        this.Locations.add(Locations.size()-1, L);
    }

    public LocalDate getDateArrived(Location L) {
        int index = Locations.lastIndexOf(L);
        return datesMoved.get(index);
    }

    public Location getLocationOnDate(LocalDate date) {
        if (date.compareTo(datesMoved.get(0)) < 0 ) {
            throw new IllegalArgumentException("The object was not yet"
                + "donated at this time");
        }
        if (date.compareTo(datesMoved.get(datesMoved.size()-1)) > 0 ) {
            throw new IllegalArgumentException("Date checked cannot be in the"
                + "future");
        } //the later the greater
        int i = 0;
        for (; i < datesMoved.size(); i++){
            if (date.compareTo(datesMoved.get(i)) < 0){
                return Locations.get(i-1);
            }
        }
        return Locations.get(i-1);
    }

    public List<Location> getLocations() {
        return this.Locations;
    }

    public void setSellDate(LocalDate day){
        sellDate = this.getDay(day);

    }

    public LocalDate getSellDate(){
        return sellDate;
    }

    public LocalDate getDay(LocalDate day) {
        int thisDay = day.getDayOfMonth();
        int thisMonth = day.getMonthValue();
        int thisYear = day.getYear();
        return LocalDate.of(thisYear, thisMonth, thisDay);
    }


    //toString stuff

    public String[] getLocationHistoryString() {
        String[] locHistory = new String[Locations.size()];
        for(int i = 0; i < Locations.size(); i++) {
            locHistory[i] = "Arrived at" + Locations.get(i).getName()
                + "on" + datesMoved.get(i).toString();
            //make a getter method for location database that takes
            //in a location name

        }
        return locHistory;
    }

    public String toString() {
        String[] locHistoryArr = this.getLocationHistoryString();
        String locHistory = "";
        for(String s: locHistoryArr){
            locHistory += s + "\n";
        }
        return locHistory;

    }


}