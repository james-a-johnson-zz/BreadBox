package spacepirates.breadbox;

import java.util.ArrayList;

public class Location {

    private String name;
    private String type;
    private double latitude;
    private double longitude;
    private String address;
    private String phoneNumber;
    // private ArrayList<DonationItem> inventory;
    private int inventoryMax;

    public Location(String name, String type, double latitude, double longitude, String address, String phoneNumber) {
        this.name = name;
        this.type = type;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.phoneNumber = phoneNumber;
        // inventory = new ArrayList<DonationItem>();
        inventoryMax = 100;
    }

    public Location(String name, String type, String latitude, String longitude, String address, String phoneNumber) {
        this(name, type, new Double(latitude), new Double(longitude), address, phoneNumber);
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

    public void setLatitude(String latitude) {
        this.latitude = new Double(latitude);
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = new Double(longitude);
    }

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

    // public ArrayList<DonationItem> getInventory() {
    //     return inventory;
    // }

    // public void addItem(DonationItem d) {
    //     inventory.add(d);
    // }

    // public double percentFull() {
    //     return ((double)inventory.size())/inventoryMax;
    // }

    public boolean equals(Location l) {
        if (!(l isInstanceOf Location) || !(l.getAddress().compareTo(this.getAddress() == 0))) {
            return false;
        } else {
            return true;
        }
    }
}