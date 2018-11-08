package spacepirates.breadbox.model;

import android.util.Log;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Statistics{
    //methods to compute statistics for a location and a business
    //which means overloading a 'get stats' method

    //each location has-a statistics
    //each business has-a statistics

    //item tracking info
    int year;
    int[] monthlyDonations;
    int[] monthlyDistributions;
    double[] monthlyIncome;
    int[] monthlyTurnoverTimes;
    int[] monthlyTurnovers;
    String[] months = {"January", "February", "March", "April", "May", "June",
        "July", "August", "September", "October", "Novemver", "December"};
    //to get turnover rate, divide turnover time for some month by turnover num
    //for that month

    //daily sold and added trackers
    private List<DonationItem> soldDaily;
    private List<DonationItem> addedDaily;

    //inventory:
    double inventoryValue;
    int inventorySize; //current size
    Map<Category, Integer> categoryInventorySize;
    //int[] categoryInventorySize; //current size per category


    //constructors
    public Statistics(Location location) {
        monthlyDonations = new int[12];
        monthlyDistributions = new int[12];
        monthlyIncome = new double[12];
        monthlyTurnoverTimes = new int[12];
        monthlyTurnovers = new int[12];
        categoryInventorySize = new HashMap<>();

        soldDaily = new ArrayList<DonationItem>();
        addedDaily = new ArrayList<DonationItem>();
        year = this.getDay(LocalDate.now()).getYear();

        this.updateInventoryStats(location);

    }

//    public Statistics(Business business) {
//
//    }

    public void addUpdate(DonationItem item){
        /*
        if (addedDaily.size() != 0 && addedDaily.get(0).getDateInCirculation()
            .equals(item.getDateInCirculation())){
            addedDaily.add(item);
        } else {
            addedDaily = new ArrayList<DonationItem>();
            addedDaily.add(item);
        }
        monthlyDonations[item.getDateInCirculation().getMonthValue()-1]++;
        */
        inventoryValue += item.getPrice();
        inventorySize++;
        Log.d("Statistics", "categoryInventorySize: " + categoryInventorySize.size());
        Log.d("Statistics", "new item category: " + item.getCategory());
        if (categoryInventorySize.containsKey(item.getCategory())){
            Category cat = item.getCategory();
            categoryInventorySize.put(cat, categoryInventorySize.get(cat)+1);
        } else {
            categoryInventorySize.put(item.getCategory(), 1);
        }
    }


    public void sellUpdate(DonationItem item){
        /*
        if (soldDaily.size() != 0 && soldDaily.get(soldDaily.size()-1).getDateSold()
            .equals(item.getDateSold())){
            soldDaily.add(item);
        } else {
            soldDaily = new ArrayList<DonationItem>();
            soldDaily.add(item);
        }
        monthlyIncome[item.getDateSold().getMonthValue()-1] += item.getPrice();
        monthlyDistributions[item.getDateSold().getMonthValue()-1]++;
        this.removeUpdate(item);
        */
    }

    public void removeUpdate(DonationItem item){
        /*
        inventoryValue -= item.getPrice();
        inventorySize--;
        Category cat = item.getCategory();
        categoryInventorySize.put(cat, categoryInventorySize.get(cat)-1);
        LocalDate dateMoved = item.getDateArrived();

        if (item.getSold()){
            dateMoved = item.getDateSold();
        }

        monthlyTurnovers[dateMoved.getMonthValue()-1] ++;
        int turnoverTime = item.getDateInCirculation().getMonthValue()
                - dateMoved.getMonthValue();
        monthlyTurnoverTimes[dateMoved.getMonthValue()-1] += turnoverTime;
        */
    }

    public void updateInventoryStats(Location location){
        int inventorySize = location.getInventory().size();
        int inventoryValue = 0;

        List<DonationItem> inventory = location.getInventory();
        for (DonationItem item : inventory){
            inventoryValue += item.getPrice();
            if (categoryInventorySize.containsKey(item.getCategory())){
                Category cat = item.getCategory();
                categoryInventorySize.put(cat, categoryInventorySize.get(cat)+1);
            } else {
                categoryInventorySize.put(item.getCategory(), 1);
            }
        }

    }


    public int getDailyDistributions() {
        return soldDaily.size();
    }

    public int getDailyDonations() {
        return addedDaily.size();
    }

    public LocalDate getDay(LocalDate day) {
        int thisDay = day.getDayOfMonth();
        int thisMonth = day.getMonthValue();
        int thisYear = day.getYear();
        return LocalDate.of(thisYear, thisMonth, thisDay);
    }

    public int getYearOfStats() {
        return this.year;
    }

    //test toString methods, can delete later/comment out
    public String toString() {
        String statString = "Statistics Today: " + "\n"
            + "Daily Distributions: " + getDailyDistributions() + "\n"
            + "Daily Donations: " + getDailyDonations() + "\n";
        statString += "Statistics " + this.year + "\n"
            + "Monthly Income:" + "\n";
        statString += this.MonthlyIncomeString();
        return statString;
    }

    public String MonthlyIncomeString() {
        String incomeString = "";
        for(int i = 0; i < months.length; i++) {
            incomeString += String.format("%-10s %.2f %n",months[i],monthlyIncome[i]);
        }
        return incomeString;
    }


}
