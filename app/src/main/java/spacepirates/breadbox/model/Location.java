package spacepirates.breadbox.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//Implements parcelable, so activities bundle and put
//extra to pass a specific location to another activity

/**
 * Class that holds all information about a company's location,
 * including an inventory of items, contact info, address and global position
 */
@IgnoreExtraProperties
public class Location implements Parcelable, Serializable {

    private String name;
    private String type;
    private double latitude;
    private double longitude;
    private String address;
    private String phoneNumber;
    private List<DonationItem> inventory;
    // private List<Statistics> yearlyStats;
    //private Statistics stats; //this year's statistics
    private int inventoryMax;
    //used for implementing Parcelable
    private int mData;

    /**
     * Default constructor
     */
    public Location() {
        this.name = "Invalid";
        this.type = "Invalid";
        this.latitude = Double.NaN;
        this.longitude = Double.NaN;
        this.address = "Invalid";
        this.phoneNumber = "Invalid";
        inventory = new ArrayList<DonationItem>();
        inventoryMax = 100;
        //yearlyStats = new ArrayList<Statistics>();
        /*
        stats = new Statistics(this);
        yearlyStats.add(stats);
        */
    }

    /**
     * Main constructor for a Location
     * @param name          The name of the location
     * @param type          A location's type (store, warehouse, etc...)
     * @param latitude      Latitude position
     * @param longitude     Longitude position. Both of these are used for the map
     * @param address       Street address
     * @param phoneNumber   Phone included for contacting the location
     */
    public Location(String name, String type, double latitude, double longitude,
                    String address, String phoneNumber) {
        this.name = name;
        this.type = type;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.phoneNumber = phoneNumber;
        inventory = new ArrayList<DonationItem>();
        inventoryMax = 100;
        //yearlyStats = new ArrayList<Statistics>();
        /*
        stats = new Statistics(this);
        yearlyStats.add(stats);
        */
    }

    /**
     * This constructor handles the case where latitude and longitude
     * are not provided as double values but as some other number
     * @param name          The name of the location
     * @param type          A location's type (store, warehouse, etc...)
     * @param latitude      Latitude position
     * @param longitude     Longitude position. Both of these are used for the map
     * @param address       Street address
     * @param phoneNumber   Phone included for contacting the location
     */
    public Location(String name, String type, String latitude, String longitude,
                    String address, String phoneNumber) {
        this(name, type, Double.valueOf(latitude), Double.valueOf(longitude), address, phoneNumber);
    }

    @Override
    public String toString() {
        return (type + ": " + name + " at " + address + ". Call " + phoneNumber);
    }

    /**
     * @return The location's name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the location's name
     * @param name Data to set name to
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The location's type (A string)
     */
    public String getType() {
        return type;
    }

    /**
     * @param type Set a location's new type to this
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return The location's latitude
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * @param latitude New latitude to be set
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

/*
    public void setLatitude(String latitude) {
        this.latitude = new Double(latitude);
    }
*/

    /**
     * @return The location's longitude
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * @param longitude New longitude to be set
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /*public void setLongitude(String longitude) {
        this.longitude = new Double(longitude);
    }*/

    /**
     * @return The location's address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address Give a location this new address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return A location's phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param phoneNumber Change a location's phone number to this param
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Used to examine the inventory of the location
     * @return The location's collection of items
     */
    public List<DonationItem> getInventory() {
        return inventory;
    }

    /**
     * Adds an item to the location's inventory
     * @param d the donation item to be added
     */
    public void addItem(DonationItem d) {
        /*
        if (stats.getYearOfStats() != LocalDate.now().getYear()){this.updateYearOfStats();}
        this.stats.addUpdate(d);
        */
        inventory.add(d);
    }

    /**
     * Gives a double representing the amount of the inventory that is currently filled
     * @return A percentage of how much capacity is taken up
     */
    public double percentFull() {
        return ((double)inventory.size())/inventoryMax;
    }

    @Override
    public boolean equals(Object l) {
        if (!(l instanceof Location)) {
            return false;
        }
        return (((Location) l).getAddress().compareTo(this.getAddress()) == 0);
    }

      //not sure what return type should be here (could be bool)
//    public void sellItem(DonationItem d) {
//        /*
//        if (stats.getYearOfStats() != LocalDate.now().getYear()){this.updateYearOfStats();}
//        if(inventory.remove(d)){ //for now, do nothing if item did not exist
//            this.stats.sellUpdate(d);
//        }
//        */
//    }
//
      //use to move item to new location/set item location
//    public boolean removeItem(DonationItem d) {
//        /*
//        if (stats.getYearOfStats() != LocalDate.now().getYear()){this.updateYearOfStats();}
//        this.stats.removeUpdate(d);
//        */
//        return inventory.remove(d);
//    }
//
//    public void updateYearOfStats(){
//        /*
//        stats = new Statistics(this);
//        yearlyStats.add(stats);
//        */
//    }
//
//    /*
//    public Statistics getStats(){
//        return this.stats;
//    }
//    */
//
//    /*
//    public List<Statistics> getAllStats(){
//        return this.yearlyStats;
//    }
//    */
//
//
//    //test toString methods, can delete later/comment out
//    public String toStringTest() { //for testing purposes
//        String LocString = "";
//        LocString += "Name: " + name + "\n"
//            + "Location Type: " + type + "\n"
//            + "Address:" + address + "\n"
//            + "Inventory:" + "\n" + this.inventoryToString();
//        return LocString;
//    }
//
//    public String inventoryToString() {
//        String itemStr = "";
//        for(DonationItem d : inventory) {
//            itemStr += d.getName() + "\n";
//        }
//        return itemStr;
//    }

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

    // this is used to regenerate your object.
    // All Parcelables must have a CREATOR that implements these two methods
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

