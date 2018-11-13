package spacepirates.breadbox.model;

import android.support.annotation.NonNull;

/**
 * Specifies types of categories available to classify donation items
 */
public enum Category{
    APPAREL("Apparel"), FURNITURE("Furniture"), KITCHENWARE("Kitchenware"),
     TOY("Toy"), BOOK("Book"), ELECTRONICS("Electronics");

    private final String name;

    Category(String name){
        this.name = name;
    }

    @NonNull
    @Override
    public String toString(){
        return this.name;
    }
}