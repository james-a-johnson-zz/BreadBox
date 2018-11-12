package spacepirates.breadbox.model;

import android.support.annotation.NonNull;

import java.util.Arrays;
import java.util.List;

/**
 * All possible tags that are subcategories of the category enum are listed below (as an enum :O)
 */
public enum Tag{
    TOPS("Tops", Category.APPAREL),
    BOTTOMS("Bottoms", Category.APPAREL),
    JEWELRY("Jewelry", Category.APPAREL),
    FOOTWEAR("Footwear", Category.APPAREL),
    ACCESSORIES("Accessories", Category.APPAREL),
    DRESSES("Dresses", Category.APPAREL),
    SWIMWEAR("Swimwear", Category.APPAREL),
    OUTERWEAR("Outerwear", Category.APPAREL),

    CUTLERY("Cutlery", Category.KITCHENWARE),
    MIXERS("Mixers", Category.KITCHENWARE),
    BAKEWARE("Bakeware", Category.KITCHENWARE),
    COOK_UTENSILS("Cooking Utensils", Category.KITCHENWARE),
    POTS_PANS("Pots and Pans", Category.KITCHENWARE),

    TABLE("Table", Category.FURNITURE),
    CHAIR("Chair", Category.FURNITURE),
    DECORATIVE("Decorative", Category.FURNITURE),
    SHELVING("Shelving", Category.FURNITURE),
    ELECTRONICS("Electronics", Category.KITCHENWARE, Category.FURNITURE, Category.ELECTRONICS),

    NOVEL("Novel", Category.BOOK),
    BIOGRAPHY("Biography", Category.BOOK),
    COOKBOOK("CookBook", Category.BOOK, Category.KITCHENWARE);

    private final String name;
    private List<Category> categories;

    Tag(String name, Category... categoryArr){
        this.name = name;
        this.categories.addAll(Arrays.asList(categoryArr));
    }

    @NonNull
    @Override
    public String toString(){
        return this.name;
    }

}
