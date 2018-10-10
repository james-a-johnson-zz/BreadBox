package spacepirates.breadbox;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

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
        currentLocation.addItem(this);
    }


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

    public List<Tag> getTags() {
        return this.tags;
    }

    public void setDonor(BasicUser donor){
        this.donor = donor;
    }

    public BasicUser getDonor(){
        return this.donor;
    }

    public void moveLocation(Location L){
        this.history.moveLocations(L);
        currentLocation.removeItem(this);
        this.currentLocation = L;
        currentLocation.addItem(this);
    }

    public void setLocation(Location L){
        this.history.setLastLocation(L); //DIFFERENT FROM MOVE LOCATIONS
        currentLocation.removeItem(this);
        this.currentLocation = L;
        currentLocation.addItem(this);
    }

    //Item history retrieval stuff

    public Location getCurrentLocation(){
        return currentLocation;
    }

    public LocalDate getDateInCirculation(){
        Location L = this.history.getLocations().get(0);
        return this.history.getDateArrived(L);
    }

    public LocalDate getDateArrived() {
        return this.history.getDateArrived(currentLocation);
    }

    public LocalDate getDateArrived(Location L) {
        return this.history.getDateArrived(L);

    }

    public History getHistory() {
        return this.history;
    }

    public List<Location> getPastLocations() {
        return this.history.getLocations();
    }

    public void sell() {
        this.history.setSellDate(LocalDate.now());
        currentLocation.sellItem(this);
    }

    public LocalDate getDateSold() {
        return this.history.getSellDate();
    }

    public boolean getSold(){
        return this.history.getSold();
    }

    public String toString(){
        String histString = this.history.toString();
        String finString = "Name : " + name + "\n"
            + "Price: $" + price + "\n"
            + "Category: " + category + "\n";
        if (this.history.getSold()){
            finString += "Last Located at: " + currentLocation.getName() + "\n";
        } else {
            finString += "Location: " + currentLocation.getName() + "\n";
        }

        finString += "History: " + "\n"+ histString;
        return finString;
    }

    //public




}