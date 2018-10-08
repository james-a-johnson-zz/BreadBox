package spacepirates.breadbox;

import java.util.LocalDate;
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
    int donationRate; //in donations/this month
    List<Integer> donationRatesMonthly;

    int turnoverRate; //avg time items spend in inventory at location

    int distributionRate; //in sales/this month
    List<Integer> distributionRatesMonthly;

    int dailyDonations;
    int dailyDistributions;

    //inventory sizes:
    int inventorySize; //current size
    int[] categoryInventorySize; //current size per category

    //pricing:
    int income; //in income/this month
    List<Integer> incomeMonthly;

    int inventoryValue; //in inventoryValue/this month
    List<Integer> inventoryValueMonthly;

    public Statistics(Location location){
        this.update(location);
    }

    public Statistics(Business business){
        this.update(business);

    }

    public void update(Location location){

        locInventory = location.getInventory();
        inventorySize = locInventory.size();
        //this.updateCategoryInventories(locInventory); --> could make this method, but going to make
        //it in constructor for now
        categoryInventorySize = new int[Category.values().length];
        for(DonationItem d : locInventory){
            categoryInventorySize[d.getCategory().ordinal()]++; //updates amount of items in each category
        }


        dailyDonations = 0;
        int i = locInventory(inventorySize-1); //assuming inventory sorted by date arrived
        //meaning the last items added will be at the back.

        int nowDay = LocalDate.now().getDayOfMonth();
        int nowMonth = LocalDate.now().getMonthValue();
        int nowYear = LocalDate.now().getYear();

        while(nowDay == locInventory.get(i).getDateArrived().getDayOfMonth()
            && nowMonth == locInventory.get(i).getDateArrived().getMonthValue()
            && nowYear == locInventory.get(i).getDateArrived().getYear()){
            dailyDonations++;
            i--;
        }

        //update daily distributions
        //update donation rate & donation rate monthly list
        //update distribution rate & distribution rate monthly list
        //update turnover rate
        //update income & income monthly list
        //update inventory value & inventory value monthly list
    }

    public void update(Business business){

    }


}
