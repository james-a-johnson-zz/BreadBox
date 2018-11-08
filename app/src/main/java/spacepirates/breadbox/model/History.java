package spacepirates.breadbox.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Functional class that tracks an item's location history
 */
public class History {
    private Map<Location, LocalDate> locationHistory;
    private LocalDate sellDate;
    private boolean sold;

    /**
     * Initializes a history class for use on a single item
     * @param location The specific item that the instance will track
     */
    public History(Location location) {
        locationHistory = new HashMap<>();
        locationHistory.put(location, this.getDay(LocalDate.now()));
        sold = false;

    }

    /**
     * Method for when an item needs to be relocated
     * @param L The new location (destination) of the item
     */
    public void moveLocations(Location L) {
        this.locationHistory.put(L, this.getDay(LocalDate.now()));
    }

    /**
     * Modifies the last location that an item has in its history
     * @param replace   The location to replace the target location
     * @param curr      The target location to be removed
     */
    public void setLastLocation(Location replace, Location curr) {
        LocalDate date = locationHistory.remove(curr);
        locationHistory.put(replace, date);
    }

    /**
     * Get the date an item arrived at a specific location
     * @param L The target location
     * @return  The date that the item arrived there
     */
    public LocalDate getDateArrived(Location L) {
        return locationHistory.get(L);
    }

//
//    public Location getLocationOnDate(LocalDate date) {
//        date = this.getDay(date);
//        Set<Map.Entry<Location, LocalDate>> entrySet= locationHistory.entrySet();
//        for(Map.Entry<Location, LocalDate> e: entrySet){
//            if (e.getValue() == date){
//                return e.getKey();
//            }
//        }
//        return null; //or noSuchElement exception, not sure which is better here
//    }
//
//    public List<Map.Entry<Location, LocalDate>> getLocationTime() {
//        Set<Map.Entry<Location, LocalDate>> entrySet= locationHistory.entrySet();
//        List<Map.Entry<Location, LocalDate>> sortedLocations = new ArrayList<>();
//        for(Map.Entry<Location, LocalDate> e: entrySet){
//            if (sortedLocations.size() == 0){
//                sortedLocations.add( e);
//            } else {
//                for(int i = 0; i < sortedLocations.size(); i++){
//                    if (sortedLocations.get(i).getValue().compareTo(e.getValue()) > 0){
//                        sortedLocations.add(i+1, e);
//                    }
//                }
//            }
//        }
//        return sortedLocations;
//    }
//
//    public void setSellDate(LocalDate day){
//        sold = true;
//        sellDate = this.getDay(day);
//
//    }
//
//    public LocalDate getSellDate(){
//        return sellDate;
//    }
//
//    public boolean getSold(){
//        return sold;
//    }

    /**
     * Get the current date from a given day
     * @param day The given day
     * @return The date that that day falls on in the current year
     */
    public LocalDate getDay(LocalDate day) {
        int thisDay = day.getDayOfMonth();
        int thisMonth = day.getMonthValue();
        int thisYear = day.getYear();
        return LocalDate.of(thisYear, thisMonth, thisDay);
    }



}