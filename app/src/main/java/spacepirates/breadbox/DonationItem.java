package spacepirates.breadbox;

import java.util.ArrayList;
import java.util.List;

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
        this(name, price, category, currentLocation, description, null);
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

        public void setLocation(Location L){
        this.history.moveLocations(L);
        this.currentLocation = L;
    }

    //Item history retrieval stuff

    public Location getCurrentLocation(){
        return currentLocation;
    }

    //public List<Location> getLocationHistory()

    public LocalDate getDateArrived() {
        return this.history.getDateArrived(currentLocation);
    }

    public LocalDate getDateArrived(Location L) {
        return this.history.getDateArrived(L);

    }




}