package spacepirates.breadbox.model;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.google.firebase.database.DatabaseReference;

//Implements parceble, so activities bundle and put extra to pass a specific location to another activity
public class Location implements Parcelable, Serializable {

    private String name;
    private String type;
    private double latitude;
    private double longitude;
    private String address;
    private String phoneNumber;
    private List<DonationItem> inventory;
    private List<Statistics> yearlyStats;
    private Statistics stats; //this year's statistics
    private int inventoryMax;
    //used for implementing Parcelable
    private int mData;

    public Location() {
        this.name = "Invalid";
        this.type = "Invalid";
        this.latitude = Double.NaN;
        this.longitude = Double.NaN;
        this.address = "Invalid";
        this.phoneNumber = "Invalid";
    }

    public Location(String name, String type, double latitude, double longitude, String address, String phoneNumber) {
        this.name = name;
        this.type = type;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.phoneNumber = phoneNumber;
        inventory = new ArrayList<DonationItem>();
        inventoryMax = 100;
        yearlyStats = new ArrayList<Statistics>();
        stats = new Statistics(this);
        yearlyStats.add(stats);
    }

    public Location(String name, String type, String latitude, String longitude, String address, String phoneNumber) {
        this(name, type, Double.valueOf(latitude), Double.valueOf(longitude), address, phoneNumber);
    }

    //Only use with correctly formatted csv
    public Location(String[] arr) {
        this(arr[1], arr[8], arr[2], arr[3], arr[4], arr[9]);
    }

    public String toString() {
        return (type + ": " + name + " at " + address + ". Call " + phoneNumber);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
/*
    public void setLatitude(String latitude) {
        this.latitude = new Double(latitude);
    }
*/
    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /*public void setLongitude(String longitude) {
        this.longitude = new Double(longitude);
    }*/

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<DonationItem> getInventory() {
        return inventory;
    }

    public void addItem(DonationItem d) {
        if (stats.getYearOfStats() != LocalDate.now().getYear()){this.updateYearOfStats();}
        this.stats.addUpdate(d);
        inventory.add(d);
    }

    public double percentFull() {
        return ((double)inventory.size())/inventoryMax;
    }

    @Override
    public boolean equals(Object l) {
        if (l == null || !(l instanceof Location)) {
            return false;
        }
        return (((Location) l).getAddress().compareTo(this.getAddress()) == 0);
    }



    public void sellItem(DonationItem d) { //not sure what return type should be here (could be bool)
        if (stats.getYearOfStats() != LocalDate.now().getYear()){this.updateYearOfStats();}
        if(inventory.remove(d)){ //for now, do nothing if item did not exist
            this.stats.sellUpdate(d);
        }
    }

    public boolean removeItem(DonationItem d) { //use to move item to new location/set item location
        if (stats.getYearOfStats() != LocalDate.now().getYear()){this.updateYearOfStats();}
        this.stats.removeUpdate(d);
        return inventory.remove(d);
    }

    public void updateYearOfStats(){
        stats = new Statistics(this);
        yearlyStats.add(stats);
    }

    public Statistics getStats(){
        return this.stats;
    }

    public List<Statistics> getAllStats(){
        return this.yearlyStats;
    }


    //test toString methods, can delete later/comment out
    public String toStringTest() { //for testing purposes
        String LocString = "";
        LocString += "Name: " + name + "\n"
            + "Location Type: " + type + "\n"
            + "Address:" + address + "\n"
            + "Inventory:" + "\n" + this.inventoryToString();
        return LocString;

    }

    public String inventoryToString() {
        String itemStr = "";
        for(DonationItem d : inventory) {
            itemStr += d.getName() + "\n";
        }
        return itemStr;
    }

    /* everything below here is for implementing Parcelable */

    // 99.9% of the time you can just ignore this
    @Override
    public int describeContents() {
        return 0;
    }

    // write your object's data to the passed-in Parcel
    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(mData);
    }

    // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
    public static final Parcelable.Creator<Location> CREATOR = new Parcelable.Creator<Location>() {
        public Location createFromParcel(Parcel in) {
            Log.d("Location", "Creating from Parcel");
            return new Location(in);
        }

        public Location[] newArray(int size) {
            return new Location[size];
        }
    };

    // example constructor that takes a Parcel and gives you an object populated with it's values
    private Location(Parcel in) {
        mData = in.readInt();
    }

}

