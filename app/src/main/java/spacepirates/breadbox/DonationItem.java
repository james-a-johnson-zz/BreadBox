import java.util.ArrayList;
import java.util.List;

public class DonationItem {
    private String name;
    private double price;
    private Category category;
    private List<Tag> tags;
    private String description;

    public DonationItem(String name, double price, Category category) {
        this(name, price, category, "None");
    }

    public DonationItem(String name, double price, Category category
        String description) {
        this(name, price, category, description, null);
    }

    public DonationItem(String name, double price, Category category
        String description, List<Tag> tags) {
        this.name = name;
        this.price = price;
        this.Category = category;
        this.description = description;
        this.tags = tags;

    }





}