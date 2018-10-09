package spacepirates.breadbox;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class Statistics{
    //methods to compute statistics for a location and a business
    //which means overloading a 'get stats' method
    // Total items by category.
    // Value of inventory by month.
    // Income (sell prices –value) per month.
    // Donations per month per location.

    //each location has-a statistics
    //each business has-a statistics

    //item tracking info
    List<Integer> monthlyDonations;
    List<Integer> monthlyDistributions;
    List<Double> monthlyIncome;

    int turnoverRate; //avg time items spend in inventory at location
    double inventoryValue;

    int dailyDonations;
    int dailyDistributions;

    //inventory sizes:
    int inventorySize; //current size
    int[] categoryInventorySize; //current size per category


    //constructors
    public Statistics(Location location) {
        monthlyDonations = new ArrayList<>();
        monthlyDistributions = new ArrayList<>();
        monthlyIncome = new ArrayList<>();
        categoryInventorySize = new int[Category.values().length];
        this.updateAll(location);
    }

    public Statistics(Business business) {
        this.updateAll(business);

    }

    //methods (Update All)
    public void updateAll(Location location) {
        this.updateInventorySize(location);
        this.updateDailyDonations(location);

        //update daily distributions
        //update donation rate & donation rate monthly list
        //update distribution rate & distribution rate monthly list
        //update turnover rate
        //update income & income monthly list
        //update inventory value & inventory value monthly list
    }

    public void updateAll(Business business) {

    }


    //Individual update methods
    public void updateDailyDonations(Location location) {
        List<DonationItem> locInventory = location.getInventory();
        dailyDonations = 0;
        int i = inventorySize-1; //assuming inventory sorted by date arrived
        //meaning the last items added will be at the back.

        LocalDate today = this.getDay(LocalDate.now());
        LocalDate donateDay = locInventory.get(i).getDateArrived();

        while(today.equals(donateDay)) {
            dailyDonations++;
            i--;
            donateDay = locInventory.get(i).getDateArrived();
        }
    }

    public void updateDailyDistributions(Location location) {
        dailyDistributions = location.getSoldToday().size();
    }

    public void updateInventorySize(Location location){
        List<DonationItem> locInventory = location.getInventory();
        inventorySize = locInventory.size();
        for(DonationItem d : locInventory) {
            categoryInventorySize[d.getCategory().ordinal()]++; //updates amount of items in each category
        }
    }


    //smaller add and remove operations
    //add timestamp to daily donations/distributions? or update every time
    public void addItem(DonationItem d) {
        inventorySize++;
        categoryInventorySize[d.getCategory().ordinal()]++;
        inventoryValue += d.getPrice();
        //dailyDonations++;

    }

    public void sellItem(DonationItem d) {
        inventorySize--;
        categoryInventorySize[d.getCategory().ordinal()]--;
        inventoryValue -= d.getPrice();
        //dailyDistributions++;
    }

    public LocalDate getDay(LocalDate day) {
        int thisDay = day.getDayOfMonth();
        int thisMonth = day.getMonthValue();
        int thisYear = day.getYear();
        return LocalDate.of(thisYear, thisMonth, thisDay);
    }


}
