package spacepirates.breadbox;

import java.util.List;
import java.util.ArrayList;

public enum Tag{
    TOPS("Tops", Category.APPAREL), BOTTOMS("Bottoms", Category.APPAREL),
    JEWELRY("Jewelry", Category.APPAREL), FOOTWEAR("Footwear", Category.APPAREL),
    ACCESSORIES("Accessories", Category.APPAREL), DRESSES("Dresses", Category.APPAREL),
    SWIMWEAR("Swimwear", Category.APPAREL), OUTERWEAR("Outerwear", Category.APPAREL),

    CUTLERY("Cutlery", Category.KITCHENWARE), MIXERS("Mixers", Category.KITCHENWARE),
    BAKEWARE("Bakeware", Category.KITCHENWARE), COOK_UTENSILS("Cooking Utensils", Category.KITCHENWARE),
    POTS_PANS("Pots and Pans", Category.KITCHENWARE),

    TABLE("Table", Category.FURNITURE), CHAIR("Chair", Category.FURNITURE),
    DECORATIVE("Decorative", Category.FURNITURE), SHELVING("Shelving", Category.FURNITURE),
    ELECTRONICS("Electronics", Category.KITCHENWARE, Category.FURNITURE, Category.ELECTRONICS)

    NOVEL("Novel", Category.BOOK), BIOGRAPHY("Biography", Category.BOOK),
    COOKBOOK("CookBook" Category.BOOK, Category.KITCHENWARE);

    private String name;
    private List<Category> categories;

    public Tag(String name, Category... categoryArr){
        this.name = name;

        for(Category c: categoryArr){
            this.categories.add(c);
        }
    }

    public String toString(){
        return this.name;
    }



}