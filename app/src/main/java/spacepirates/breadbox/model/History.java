package spacepirates.breadbox.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class History {
    private Map<Location, LocalDate> locationHistory;
    private LocalDate sellDate;
    private boolean sold;

    public History(Location location) {
        locationHistory = new HashMap<>();
        locationHistory.put(location, this.getDay(LocalDate.now()));
        sold = false;

    }

    public void moveLocations(Location L) {
        this.locationHistory.put(L, this.getDay(LocalDate.now()));
    }

    public void setLastLocation(Location replace, Location curr) {
        LocalDate date = locationHistory.remove(curr);
        locationHistory.put(replace, date);
    }

    public LocalDate getDateArrived(Location L) {
        return locationHistory.get(L);
    }

    public Location getLocationOnDate(LocalDate date) {
        date = this.getDay(date);
        Set<Map.Entry<Location, LocalDate>> entrySet= locationHistory.entrySet();
        for(Map.Entry<Location, LocalDate> e: entrySet){
            if (e.getValue() == date){
                return e.getKey();
            }
        }
        return null; //or noSuchElement exception, not sure which is better here
    }

    public List<Map.Entry<Location, LocalDate>> getLocationTime() {
        Set<Map.Entry<Location, LocalDate>> entrySet= locationHistory.entrySet();
        List<Map.Entry<Location, LocalDate>> sortedLocations = new ArrayList<>();
        for(Map.Entry<Location, LocalDate> e: entrySet){
            if (sortedLocations.size() == 0){
                sortedLocations.add( e);
            } else {
                for(int i = 0; i < sortedLocations.size(); i++){
                    if (sortedLocations.get(i).getValue().compareTo(e.getValue()) > 0){
                        sortedLocations.add(i+1, e);
                    }
                }
            }
        }
        return sortedLocations;
    }

    public void setSellDate(LocalDate day){
        sold = true;
        sellDate = this.getDay(day);

    }

    public LocalDate getSellDate(){
        return sellDate;
    }

    public boolean getSold(){
        return sold;
    }

    public LocalDate getDay(LocalDate day) {
        int thisDay = day.getDayOfMonth();
        int thisMonth = day.getMonthValue();
        int thisYear = day.getYear();
        return LocalDate.of(thisYear, thisMonth, thisDay);
    }



}