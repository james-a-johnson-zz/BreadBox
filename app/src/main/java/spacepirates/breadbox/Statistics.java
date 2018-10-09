package spacepirates.breadbox;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.ArrayList;

public class Statistics{
    //methods to compute statistics for a location and a business
    //which means overloading a 'get stats' method

    //each location has-a statistics
    //each business has-a statistics

    //item tracking info
    int year;
    int[] monthlyDonations;
    int[] monthlyDistributions;
    int[] monthlyIncome;
    int[] monthlyTurnoverTimes;
    int[] monthlyTurnovers;
    //to get turnover rate, divide turnover time for some month by turnover num
    //for that month

    //daily sold and added trackers
    private List<DonationItem> soldDaily;
    private List<DonationItem> addedDaily;

    //inventory:
    double inventoryValue;
    int inventorySize; //current size
    int[] categoryInventorySize; //current size per category


    //constructors
    public Statistics(Location location) {
        monthlyDonations = new int[12];
        monthlyDistributions = new int[12];
        monthlyIncome = new int[12];
        monthlyTurnoverTimes = new int[12];
        monthlyTurnovers = new int[12];

        soldDaily = new ArrayList<DonationItem>();
        addedDaily = new ArrayList<DonationItem>();
        year = this.getDay(LocalDate.now()).getYear();

        categoryInventorySize = new int[Category.values().length];
        //this.updateAll(location);
    }

    public Statistics(Business business) {
        //this.updateAll(business);

    }

    public void addUpdate(DonationItem item){
        if (addedDaily.size() != 0 && addedDaily.get(addedDaily.size()-1).getDateInCirculation()
            == item.getDateInCirculation()){
            addedDaily.add(item);
        } else {
            addedDaily = new ArrayList<DonationItem>();
            addedDaily.add(item);
        }
        monthlyDonations[item.getDateInCirculation().getMonthValue()-1]++;
        inventoryValue += item.getPrice();
        inventorySize++;
        categoryInventorySize[item.getCategory().ordinal()]++;
    }


    public void sellUpdate(DonationItem item){
        if (soldDaily.size() != 0 && soldDaily.get(soldDaily.size()-1).getDateSold()
            == item.getDateSold()){
            soldDaily.add(item);
        } else {
            soldDaily = new ArrayList<DonationItem>();
            soldDaily.add(item);
        }
        monthlyIncome[item.getDateSold().getMonthValue()-1] += item.getPrice();
        monthlyDistributions[item.getDateSold().getMonthValue()-1]++;
        this.removeUpdate(item);

    }

    public void removeUpdate(DonationItem item){
        inventoryValue -= item.getPrice();
        inventorySize--;
        categoryInventorySize[item.getCategory().ordinal()]--;

        monthlyTurnovers[item.getDateSold().getMonthValue()-1] ++;
        Period turnoverTime = item.getDateInCirculation().until​(item.getDateSold());
        monthlyTurnoverTimes[item.getDateSold().getMonthValue()-1] += turnoverTime.getMonths();
    }


    public int getDailyDistributions(){
        return soldDaily.size();
    }

    public int getDailyDonations(){
        return addedDaily.size();
    }

    public LocalDate getDay(LocalDate day) {
        int thisDay = day.getDayOfMonth();
        int thisMonth = day.getMonthValue();
        int thisYear = day.getYear();
        return LocalDate.of(thisYear, thisMonth, thisDay);
    }

    public int getYearOfStats(){
        return this.year;
    }


}
