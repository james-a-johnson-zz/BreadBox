package spacepirates.breadbox.model;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * The main item that locations store and track
 * Stored in a location's inventory as a list of items
 * Has many values to identify itself and also be filtered by
 */
public class DonationItem implements Comparable<DonationItem> {
    private String name;
    private double price;
    private Category category;
    private List<Tag> tags;
    private String description;
    private final String id;
    // private photo photo;
    // private User donor;
    private String address;
    // private Location currentLocation;
    // private History history;

    public static class DonationItemBuilder {
        private final String builderName;
        private double builderPrice;
        private Category builderCategory;
        private List<Tag> builderTags;
        private String builderDescription;
        private String builderAddress;

        /**
         * Builder that allows for many combinations of constructions of a Donation Item
         * Parameters are placeholders for instance variables in donation item class
         * @param newName name
         * @param newPrice price
         * @param newCategory category
         * @param newTags tags
         * @param newDescription description
         * @param newAddress address
         */
        public DonationItemBuilder(
                String newName,
                Double newPrice,
                Category newCategory,
                List<Tag> newTags,
                String newDescription,
                String newAddress) {
            builderName = newName;
            builderPrice = newPrice;
            builderCategory = newCategory;
            builderTags = newTags;
            builderDescription = newDescription;
            builderAddress = newAddress;
        }

        /**
         * Beginning constructor that takes the name
         * @param newName name
         */
        public DonationItemBuilder(String newName) {
            builderName = newName;
        }

        /**
         * Adds the respective variable to the builder
         * @param newPrice price
         * @return this so that the call can be chained
         */
        public DonationItemBuilder price(Double newPrice) {
            builderPrice = newPrice;
            return this;
        }

        /**
         * Adds the respective variable to the builder
         * @param newCategory category
         * @return this so that the call can be chained
         */
        public DonationItemBuilder category(Category newCategory) {
            builderCategory = newCategory;
            return this;
        }

        /**
         * Adds the respective variable to the builder
         * @param newTags tags
         * @return this so that the call can be chained
         */
        public DonationItemBuilder tags(List<Tag> newTags) {
            builderTags = newTags;
            return this;
        }

        /**
         * Adds the respective variable to the builder
         * @param newDescription description
         * @return this so that the call can be chained
         */
        public DonationItemBuilder description(String newDescription) {
            builderDescription = newDescription;
            return this;
        }

        /**
         * Adds the respective variable to the builder
         * @param newAddress address
         * @return this so that the call can be chained
         */
        public DonationItemBuilder address(String newAddress) {
            builderAddress = newAddress;
            return this;
        }

        /**
         * Build the donation item based on parameters passed into builder methods
         * This should always come at the end of the chain
         * @return A newly constructed donation item
         */
        public DonationItem build() {
            return new DonationItem(this);
        }
    }

    /**
     * Donation item constructor that takes a donation item builder to assign its instance variables
     * Also assigns a unique random donation id to the item
     * @param builder the donation item builder
     */
    public DonationItem(DonationItemBuilder builder) {
        name = builder.builderName;
        price = builder.builderPrice;
        category = builder.builderCategory;
        tags = builder.builderTags;
        description = builder.builderDescription;
        address = builder. builderAddress;
        id = UUID.randomUUID().toString();
    }

    /**
     * Empty constructor for testing
     */
    public DonationItem() {
        id = UUID.randomUUID().toString();
    }

    /**
     * Constructor for items without description, tags, or address
     * @param name      The name of the item
     * @param price     The price
     * @param category  The category it belongs to
     */
    public DonationItem(String name, double price, Category category) {
        this(name, price, category, "None");
    }

    /**
     * Constructor for items without tags or address
     * @param name          The name of the item
     * @param price         The price
     * @param category      The category it belongs to
     * @param description   Custom description of the item
     */
    public DonationItem(String name, double price, Category category,
                        String description) {
        this(name, price, category, description, null, null);
    }

    /**
     * Constructor for items without address
     * @param name          The name of the item
     * @param price         The price
     * @param category      The category it belongs to
     * @param description   Custom description of the item
     * @param tags          List of custom tags that can be searched for
     */
    public DonationItem(String name, double price, Category category,
                        String description, List<Tag> tags) {
        this(name, price, category, description, tags, null);
    }

    /**
     * Full constructor
     * @param name          The name of the item
     * @param price         The price
     * @param category      The category it belongs to
     * @param description   Custom description of the item
     * @param tags          List of custom tags that can be searched for
     * @param address       Address of the location where the item resides
     */
    public DonationItem(String name, double price, Category category,
                        String description, List<Tag> tags, String address) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.description = description;
        this.tags = tags;
        this.address = address;
        id = UUID.randomUUID().toString();
    }

    /**
     * Getter for unique ID
     * @return ID of the item
     */
    public String getId() {
        return id;
    }

    /**
     * Setter for item price
     * @param price The new price of the item to be set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return The price of the item
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * Change the name of an item
     * @param name The new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the name of an item
     * @return The item's name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Change the description of an item
     * @param description The new description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter for item description
     * @return The item's description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Change the current category of an item
     * @param category The new category
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * Getter for the category of an item
     * @return The item's category
     */
    public Category getCategory() {
        return this.category;
    }

    /**
     * Getter for an item's address
     * @return The item's address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Setter for an item's address
     * @param address The new address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Sets tags of an item
     * @param tagArr All tags that are set into the list of tags for an item
     */
    public void setTags(Tag... tagArr) {
        this.tags.addAll(Arrays.asList(tagArr));
    }

    /**
     * Adds a single tag to an item's existing list of tags
     * @param t The tag to be added
     */
    public void addTag(Tag t){ this.tags.add(t); }

    /**
     * Get all tags for an item
     * @return The list of tags
     */
    public List<Tag> getTags() {
        return this.tags;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof DonationItem)) {
            return false;
        }
        DonationItem d = (DonationItem) other;
        return (this.getPrice() == d.getPrice())
                && (this.getCategory() == d.getCategory());
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
