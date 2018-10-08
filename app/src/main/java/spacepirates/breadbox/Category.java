package spacepirates.breadbox;

<<<<<<< HEAD
public enum Category{
=======
public enum Category {
>>>>>>> 0720a98114055db3a77324947a57c7359c705b9d
    APPAREL("Apparel"), FURNITURE("Furniture"), KITCHENWARE("Kitchenware"),
     TOY("Toy"), BOOK("Book"), ELECTRONICS("Electronics");

    private String name;

    Category(String name){
        this.name = name;
    }

    public String toString(){
        return this.name;
    }
}