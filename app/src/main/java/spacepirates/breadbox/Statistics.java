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

    int donationRate; //in donations/month
    List<Integer> donationRatesYearly;

    int turnoverRate; //avg time items spend in inventory at location

    int distributionRate; //in sales/month
    List<Integer> distributionRatesYearly;

    int dailyDonations;
    int dailyDistributions;

    int inventorySize;
    int[] categoryInventorySize;

    public Statistics(Location location){
        locInventory = location.getInventory();
        inventorySize = locInventory.size();
        //this.updateCategoryInventories(locInventory); --> could make this method, but going to make
        //it in constructor for now
        categoryInventorySize = new int[Category.values().length];
        for(DonationItem d : locInventory){
            categoryInventorySize[d.getCategory().ordinal()]++; //updates amount of items in each category
        }


        dailyDonations = 0;
        int i = locInventory(inventorySize-1) //assuming inventory sorted by date arrived
        //meaning the last items added will be at the back.

        int nowDay = LocalDate.now().getDayOfMonth();
        int nowMonth = LocalDate.now().getMonthValue();
        int nowYear = LocalDate.now().getYear();

        while(nowDay == locInventory.get(i).getDateArrived().getDayOfMonth()
            && nowMonth == locInventory.get(i).getDateArrived().getMonthValue()
            && nowYear == locInventory.get(i).getDateArrived().getYear(){
            dailyDonations++;
            i--;
        }
        //update daily distributions
        //update donation rate & donation rate yearly
        //update distribution rate & distribution rate yearly
        //update turnover rate



    }

    public Statistics(Business business){

    }

    public void update(Location location){

    }

    public void update(Business business){

    }


}