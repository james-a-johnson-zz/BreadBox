package spacepirates.breadbox;

public enum Category{
    APPAREL("Apparel"), FURNITURE("Furniture"), KITCHENWARE("Kitchenware"),
     TOY("Toy"), BOOK("Book"), ELECTRONICS("Electronics");

    private String name;

    public Category(String name){
        this.name = name;
    }

    public String toString(){
        return this.name;
    }
}