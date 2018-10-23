package spacepirates.breadbox.model;

import android.util.Log;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;


public class DonationItem {
    private String name;
    private double price;
    private Category category;
    private List<Tag> tags;
    private String description;
    //private photo photo;
    private BasicUser donor;
    private Location currentLocation;
    private History history;

    //constructors
    public DonationItem(String name, double price, Category category,
        Location currentLocation) {
        this(name, price, category, currentLocation, "None");
    }

    public DonationItem(String name, double price, Category category,
        Location currentLocation, String description) {
        this(name, price, category, currentLocation, description, null, null);
    }

    public DonationItem(String name, double price, Category category,
        Location currentLocation, String description, BasicUser donor) {
        this(name, price, category, currentLocation, description, donor, null);
    }

    public DonationItem(String name, double price, Category category,
        Location currentLocation, String description, List<Tag> tags) {
        this(name, price, category, currentLocation, description, null, tags);
    }

    public DonationItem(String name, double price, Category category,
        Location currentLocation, String description, BasicUser donor,
        List<Tag> tags) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.currentLocation = currentLocation;
        this.description = description;
        this.donor = donor;
        this.tags = tags;
        this.history = new History(currentLocation);
        Log.d("DonationItem", "Adding to Location: " + currentLocation);

        try {
            currentLocation.addItem(this);
        } catch (NullPointerException e) {
            if (name.equals("No Donations Found")) {
                //Don't add. Initialized the null donation in the database.
                //something the model does.
                //this is why donation items shouldn't be added to a location here.
            } else {
                throw e;
            }
        }
    }


    //getters and setters
    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return this.price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setTags(Tag... tagArr) {
        for(Tag t : tagArr){
            this.tags.add(t);
        }
    }

    public void addTag(Tag t){ this.tags.add(t); }

    public List<Tag> getTags() {
        return this.tags;
    }

    public void setDonor(BasicUser donor){
        this.donor = donor;
    }

    public BasicUser getDonor(){
        return this.donor;
    }

    //used if item is being transferred from location to location. Updates history accordingly
    public void moveLocation(Location L){
        this.history.moveLocations(L);
        Location oldLocation = currentLocation;
        this.currentLocation = L;
        oldLocation.removeItem(this);
        currentLocation.addItem(this);
    }

    //used if correction needs to be made to item's current location (in case of user mistake)
    //overwrites item's history
    public void setLocation(Location L){
        this.history.setLastLocation(L, currentLocation);
        Location oldLocation = currentLocation;
        this.currentLocation = L;
        oldLocation.removeItem(this);
        currentLocation.addItem(this);
    }

    public Location getCurrentLocation(){
        return this.currentLocation;
    }

    public LocalDate getDateInCirculation(){
        return this.history.getLocationTime().get(0).getValue();
    }

    public LocalDate getDateArrived() {
        return this.getDateArrived(currentLocation);
    }

    public LocalDate getDateArrived(Location L) {
        return this.history.getDateArrived(L);
    }

    public History getHistory() {
        return this.history;
    }

    public List<Map.Entry<Location, LocalDate>> getPastLocations() {
        return this.history.getLocationTime();
    }

    //need to implement this with DonationItemDatabase in future
    public void sell() {
        this.history.setSellDate(LocalDate.now());
        this.currentLocation.sellItem(this);
    }

    //returns the date an item was sold (does not handle exception where item was not sold)
    public LocalDate getDateSold() {
        return this.history.getSellDate();
    }

    public boolean getSold(){
        return this.history.getSold();
    }

    //toStrings for tests, can delete/comment out later
//    public String toString(){
//        String histString = this.history.toString();
//        String finString = "Name : " + name + "\n"
//            + "Price: $" + price + "\n"
//            + "Category: " + category + "\n";
//        if (this.history.getSold()){
//            finString += "Last Located at: " + currentLocation.getName() + "\n";
//        } else {
//            finString += "Location: " + currentLocation.getName() + "\n";
//        }
//
//        finString += "History: " + "\n"+ histString;
//        return finString;
//    }

}