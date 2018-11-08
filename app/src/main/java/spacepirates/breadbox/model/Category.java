package spacepirates.breadbox.model;

/**
 * Specifies types of categories available to classify donation itemss
 */
public enum Category{
    APPAREL("Apparel"), FURNITURE("Furniture"), KITCHENWARE("Kitchenware"),
     TOY("Toy"), BOOK("Book"), ELECTRONICS("Electronics");

    private String name;

    Category(String name){
        this.name = name;
    }

    @Override
    public String toString(){
        return this.name;
    }
}