package spacepirates.breadbox.model;

import java.util.List;
import java.util.UUID;


public class DonationItem implements Comparable<DonationItem> {
    private String name;
    private double price;
    private Category category;
    private List<Tag> tags;
    private String description;
    private final UUID id;
    // private photo photo;
    private User donor;
    private String address;
    // private Location currentLocation;
    // private History history;

    //constructors
    public DonationItem() {
        this(null, -1, null);
    }
    public DonationItem(String name, double price, Category category) {
        this(name, price, category, "None");
    }

    public DonationItem(String name, double price, Category category,
                        String description) {
        this(name, price, category, description, null, null, null);
    }

    public DonationItem(String name, double price, Category category,
                        String description, BasicUser donor) {
        this(name, price, category, description, donor, null, null);
    }

    public DonationItem(String name, double price, Category category,
                        String description, List<Tag> tags) {
        this(name, price, category, description, null, tags, null);
    }

    public DonationItem(String name, double price, Category category,
                        String description, User donor, List<Tag> tags, String address) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.description = description;
        this.donor = donor;
        this.tags = tags;
        this.address = address;
        id = UUID.randomUUID();
    }

    //getters and setters

    public UUID getId() {
        return id;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public User getDonor() {
        return this.donor;
    }

    @Override
    public int compareTo(DonationItem other) {
        return this.getName().compareTo(other.getName());
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